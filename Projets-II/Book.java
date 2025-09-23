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
    private Float price;
	
    public void setId(Integer id) {
        this.id = id;
    }

  
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
}