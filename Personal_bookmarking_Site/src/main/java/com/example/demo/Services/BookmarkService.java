package com.example.demo.Services;

import java.util.List;

import com.example.demo.DTO.BookmarkDTO;
import com.example.demo.Models.Book_mark;


public interface BookmarkService {

	  List<BookmarkDTO> findAll();
	  Book_mark  findById(int theId);
	  Book_mark save(Book_mark theBookmark);
	   void deleteByid(int theId);
	
}
