package com.ecommerce.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.admin.dto.LoginDto;

@Controller
@RequestMapping("admin")
public class LoginController {
	
	@GetMapping("/login")
	public String index(@RequestParam(required = false) String error, Model model) {
		String message = "";
		if(error != null && !error.isEmpty()) {
			message = "Email or Password is wrong!";
		}
		model.addAttribute("message", message);
		return "login/login";
	}
	@GetMapping("")
	public String home() {
		return "home/home";
	}
}
