package com.example.demo.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity 
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;
    private String title;
    private String author_name;
    private String description;
    private Integer pub_date;
    
    private Float price;
	
   

  
    public Float getPrice() {
        return price;
    }	
    public void setPrice(Float price) {
        this.price = price;
    }


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor_name() {
		return author_name;
	}


	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPub_date() {
		return pub_date;
	}
	public void setPub_date(Integer pub_date) {
		this.pub_date = pub_date;
	}
}