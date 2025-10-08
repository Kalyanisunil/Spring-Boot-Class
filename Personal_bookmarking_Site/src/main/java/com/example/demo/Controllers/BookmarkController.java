package com.example.demo.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Book_mark;
import com.example.demo.Repository.BookmarkRepository;
import com.example.demo.Repository.UserRepository;

@RestController
@RequestMapping("/api")
public class BookmarkController {
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	BookmarkRepository bookmarkrepo;
	
	
	@GetMapping("/bookmarks")
	public List<Book_mark> getAllBookmarks()
	{
		return bookmarkrepo.findAll();
	}
	
	
	@PostMapping("/bookmarks")
	public Book_mark addBookmark(@RequestBody Book_mark bkm)
	{
		bkm.setId(0);
		Book_mark dbbookmark=bookmarkrepo.save(bkm);
		return dbbookmark;
	}
	
}
