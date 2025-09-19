package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepository;

@Controller
public class UserController {

	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/register")
		public String registerUser(Model model)
		{
		 	model.addAttribute("message", "Enter the user details");
		 	return "register";
		}
	
	
	@PostMapping("/userdetails")
		public String addUser(User user, Model model)
		{
			userRepository.save(user);
			model.addAttribute("message", "Welcome "+ user.getName()+ " Your library membership has been created.");
		 	return "home";
		
		}
}
