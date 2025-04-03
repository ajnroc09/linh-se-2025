package fit.se2.blog_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/member")
	public String Customer() {
		return "member";
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}
}
