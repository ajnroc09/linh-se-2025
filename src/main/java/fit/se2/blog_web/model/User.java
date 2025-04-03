package fit.se2.blog_web.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Entity
@Table(name= "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
	private String address;
	private String avatar;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	private Set<String> roles;
	public Set<String> getRoles() {
		return roles;
	}
	public User(UserDto userDto, PasswordEncoder passwordEncoder) {
		this.username = userDto.getUsername();
		this.password = passwordEncoder.encode(userDto.getPassword());
		this.address = userDto.getAddress();
		this.avatar = userDto.getAvatar();
		this.roles = userDto.getRoles();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}


}
