package com.example.demo.Controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.BookmarkDTO;
import com.example.demo.DTO.BookmarkDisplayDTO;
import com.example.demo.DTO.BookmarkeditDTO;
import com.example.demo.Models.Book_mark;
import com.example.demo.Repository.BookmarkRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.BookmarkServiceImpl;

@RestController
@RequestMapping("/api")
public class BookmarkController {
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	BookmarkServiceImpl service;
	
	
	@GetMapping("/bookmarks")
	public List<BookmarkDTO> getAllBookmarksofUser()
	{
		return service.findAll();
	}
	
	@GetMapping("/allbookmarks")
	public List<BookmarkDisplayDTO> getAllBookmarks()
	{
		return service.findAllForAll();
	}
	
	
	@PostMapping("/bookmarks")
	
	public ResponseEntity<String> addBookmark(@RequestBody Book_mark bookmark) {
	    try {
	        service.save(bookmark);
	        return ResponseEntity.ok("Bookmark added successfully!");
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	@DeleteMapping("/bookmarks/{id}")
	 public ResponseEntity<?> deleteBookmark(@PathVariable int id) {
        try 
        {
            service.deleteByid(id);
            return ResponseEntity.ok("Deleted successfully!");
        } 
        catch (RuntimeException e) 
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
	
	@PatchMapping("bookmarks/{id}")
	public ResponseEntity<?> updateBookmarkPartially(
	        @PathVariable int id,
	        @RequestBody BookmarkeditDTO dto) {

	    Book_mark existing = service.findById(id);
	    if (existing == null) 
	    {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Bookmark not found"));
	    }

	    if (dto.getTitle() != null)
	    {
	        existing.setTitle(dto.getTitle());
	    }
	    if (dto.getUrl() != null)
	    {
	        existing.setUrl(dto.getUrl());
	        
	    }
	    service.save(existing);

	    return ResponseEntity.ok( "Bookmark updated successfully");
	}

	
}
