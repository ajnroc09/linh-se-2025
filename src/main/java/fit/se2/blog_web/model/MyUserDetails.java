package fit.se2.blog_web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails{
	private final String username;
	private final String password;
	private final Set<GrantedAuthority> authorities;
	private User user;

	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = user.getRoles().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toSet());
	}

	//................
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
