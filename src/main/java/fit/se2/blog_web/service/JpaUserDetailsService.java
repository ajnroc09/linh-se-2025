package fit.se2.blog_web.service;

import fit.se2.blog_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userRepo.findByUsername(username)
				.map(user -> (UserDetails) user)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}
}