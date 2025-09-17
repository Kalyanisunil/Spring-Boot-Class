package com.example.demo.Controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Models.Phone;
@Controller
public class PhoneController {


	 public static List<Phone> phones = new ArrayList<>();
	
	 @GetMapping("/addPhone")
	public String  addPhone()
	{
	  
	 	return "addPhone";
	}

@GetMapping("/Phones")
 public String listPhones(Model model) 
{
	model.addAttribute("phones", phones);
   return "phones"; 
}


@PostMapping("/submit")
public String  savePhones(@RequestParam String Model_name  , @RequestParam String Brand ,@RequestParam String description ,
		@RequestParam int price , Model model) {
	
	
	Phone phone= new Phone(Model_name, Brand, description, price);
		 phones.add(phone);
		model.addAttribute("phones", phones );
		  return "redirect:/Phones";
		  }
}
		KRISHNA 	




