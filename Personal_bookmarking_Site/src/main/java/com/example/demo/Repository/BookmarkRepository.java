package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Book_mark;
import com.example.demo.Models.User;


public interface BookmarkRepository extends JpaRepository<Book_mark,Integer> {
	 Book_mark findByUserId(Book_mark userId);
	 Book_mark findById(int id);
	 List<Book_mark> findByUser(User loggedInUser);
	 Long countByUser(User user);
	
}
