package com.example.demo.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	private String username;
	private String password;
	private String email;
	 private String token;
	 
	 @OneToMany(mappedBy = "user")
	    private List<Book_mark> bookmarks;
	
	
	public User(int id, String username, String password, String email, String token) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.token = token;
	}
	
	public User()
	{
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username+ ", email=" + email + "]";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	
	

}
