package com.example.demo.Models;



public class Student {
	private int id;
	private String name;
	private String class_Name;
	private int age;
	
	public Student( int id,
	 String name,
	 String class_Name,
	 int age)
	{
		this.setId(id);
		this.setName(name);
		this.setClass_Name(class_Name);
		this.setAge(age);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClass_Name() {
		return class_Name;
	}

	public void setClass_Name(String class_Name) {
		this.class_Name = class_Name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
