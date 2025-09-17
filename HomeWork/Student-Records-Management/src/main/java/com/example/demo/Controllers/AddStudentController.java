package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Models.Student;

@Controller
public class AddStudentController {
	 public static List<Student> students = new ArrayList<>();
 	
@GetMapping("/addStudents")
	public String  addStudent()
	{
	  
	 	return "addStudent";
	}

@GetMapping("/Students")
  public String listStudents(Model model) 
{
	model.addAttribute("students", students);
    return "students"; 
}


@PostMapping("/submit")
public String  saveStudent(@RequestParam int id  , @RequestParam String name ,@RequestParam String class_Name ,
		@RequestParam int age,Model model) {
	
	 Student student = new Student(id, name, class_Name, age);
	
		  students.add(student);
		  model.addAttribute("student", student);

		  return "redirect:/Students";
}
}
