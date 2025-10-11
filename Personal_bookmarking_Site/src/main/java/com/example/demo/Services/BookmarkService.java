package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.BookmarkDTO;
import com.example.demo.DTO.BookmarkDisplayDTO;
import com.example.demo.Models.Book_mark;
import com.example.demo.Models.User;


public interface BookmarkService {

	List<Book_mark> findUserBookmarks(User user);

	List<Book_mark> findAllPublicBookmarks(User currentUser);

	Optional<Book_mark> findBookmarkIfOwner(int id, User user);

	Book_mark saveNewBookmark(Book_mark bookmark, User user);

	Book_mark updateBookmark(int id, Book_mark updatedData, User user);

	void deleteBookmark(int id, User user);
	List<Book_mark> searchByTitle(String title);

	
	
}
