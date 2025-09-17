package com.example.demo.Models;

public class Phone {

	String Model_name;
	String Brand;
	String description;
	int price;
	
	public Phone(String Model_name,
	String Brand,
	String description,
	int price)
	{
		
	}
	public String getModel()
	{
		return Model_name;
		
	}
	public String getBrand()
	{
		return Brand;
		
	}
	public String getdescription()
	{
		return description;
		
	}
	public int getPrice()
	{
		return price;
		
	}
	public void setModel(String Model_name)
	{
		 this.Model_name=Model_name;
		
	}
	public void setBrand(String Brand)
	{
		 this.Brand=Brand;
		
	}
	public void setdescription(String description)
	{
		 this.description=description;
		
	}
	public void  setPrice(int price)
	{
		 this.price=price;
		
	}

}
