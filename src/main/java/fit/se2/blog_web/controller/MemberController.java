package fit.se2.blog_web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	// Handle the GET request for /member/home
	@GetMapping("/home")
	public String showHomePage(@AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticatedUser, Model model) {
		// You can access the authenticated user details using 'principal'
		model.addAttribute("username", authenticatedUser.getUsername()); // Display the logged-in user's username
		return "memberHome"; // Render the 'memberHome.html' Thymeleaf template
	}
}
