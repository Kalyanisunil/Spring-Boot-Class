package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Book_mark;
import com.example.demo.Models.User;


public interface BookmarkRepository extends JpaRepository<Book_mark,Integer> {
	// Find all bookmarks belonging to a specific user
		List<Book_mark> findByUser(User user);
		
		// CRITICAL: Count bookmarks for the max limit check
		Long countByUser(User user);
		
		// CRITICAL: Find a specific bookmark by its ID AND its owner (for ownership check on edit/delete)
		Optional<Book_mark> findByIdAndUser(int id, User user);
	
}
