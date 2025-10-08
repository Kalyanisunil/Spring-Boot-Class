package com.example.demo.DTO;

import com.example.demo.Models.Book_mark;

import java.time.LocalDateTime;

public class BookmarkDTO {

    private int id;
    private String title;
    private String url;
    private String userEmail;
    private LocalDateTime addedTime;

    public BookmarkDTO(Book_mark bookmark) {
        this.id = bookmark.getId();
        this.title = bookmark.getTitle();
        this.url = bookmark.getUrl();
        this.addedTime = bookmark.getAddedTime();
        this.userEmail = bookmark.getUser().getEmail(); 
    }

 


	public BookmarkDTO(int id, String title, String url, String userEmail, LocalDateTime addedTime) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.userEmail = userEmail;
		this.addedTime = addedTime;
	}




	public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public LocalDateTime getAddedTime() { return addedTime; }
    public void setAddedTime(LocalDateTime addedTime) { this.addedTime = addedTime; }
}
