package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Dto.userDto;
import com.example.demo.services.UserService;




@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") userDto userDto) {
        return "register";
    }
    
    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") userDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfuly!");
        String userName= userDto.getFullname();
        model.addAttribute("username", userName);
        
        return "welcome";
    }
}