package com.example.demo.Models;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book_mark {

	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		
		private int id;
		
		private String title;
		private String url;
		
		 @CreationTimestamp
		private LocalDateTime addedTime;
		 
		 @ManyToOne
		 @JoinColumn(name = "user_id") 
		    private User user;
		
		
		
		public User getUser() {
			return user;
		}

		 public void setUser(User user) {
			 this.user = user;
		 }

		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
	
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

		
		
		public LocalDateTime getAddedTime()
		{
			return addedTime;
		}
	
		

		@Override
		public String toString() {
			return "Book_mark [id=" + id + ", title=" + title + ", url=" + url + ", addedTime=" + addedTime + "]";
		}

	
		
		
		
}
