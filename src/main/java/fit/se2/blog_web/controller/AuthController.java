package fit.se2.blog_web.controller;

import fit.se2.blog_web.model.UserDto;
import fit.se2.blog_web.model.User;
import fit.se2.blog_web.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("userDto", new UserDto()); // Pass a new UserDto to the view
		return "register"; // Thymeleaf template name
	}

	@PostMapping("/register")
	public String registerUser(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
		// Validate UserDto object
		if (bindingResult.hasErrors()) {
			return "register"; // Return to registration form with validation errors
		}

		// Check if the username already exists
		if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
			model.addAttribute("usernameError", "Username already exists");
			return "register"; // Return to registration form with error message
		}

		// Create User from UserDto
		User user = new User(userDto,passwordEncoder);

		// Save the new user to the database
		userRepository.save(user);

		// Redirect to login page or show a success page
		return "redirect:/login"; // Alternatively, you can render a "registration successful" page
	}
}
