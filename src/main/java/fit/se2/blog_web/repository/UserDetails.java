package fit.se2.blog_web.repository;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public interface UserDetails extends Serializable {
	Collection<? extends GrantedAuthority> getAuthorities();
	String getPassword();
	String getUsername();


}
