package com.example.demo.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book_mark {

	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		
		private int id;
		
		private String title;
		private String url;
		private LocalDate date;
		
		
		
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
		
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public String toString() {
			return "Book_mark [id=" + id + ", title=" + title + ", url=" + url + ", date=" + date + "]";
		}
		
}
