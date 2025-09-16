package com.example.demo.Controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class StudentViewController 
{

	@GetMapping("/Students")
	  public String listStudents(Model model) 
	{
		model.addAttribute("students", AddStudentController.students);
        return "students"; 
}

}