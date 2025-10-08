package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.BookmarkDTO;
import com.example.demo.Models.Book_mark;
import com.example.demo.Models.User;
import com.example.demo.Repository.BookmarkRepository;
import com.example.demo.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookmarkServiceImpl implements BookmarkService {
 
	@Autowired
	private BookmarkRepository repo;
	@Autowired
	private  UserRepository userRepository;
	
	
	public BookmarkServiceImpl(BookmarkRepository bookmarkrepo ,UserRepository userRepo)
	{
		repo=bookmarkrepo;
		userRepository=userRepo;
		
	}

	@Override
	public List<BookmarkDTO> findAll() {
		// TODO Auto-generated method stub

		CustomUserDetail userdetails=
				(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 User loggedInUser = userRepository.findByEmail(userdetails.getUsername());
		 
		  List<Book_mark> bookmarks = repo.findByUser(loggedInUser);
		    List<BookmarkDTO> bookmarkDTOs = new ArrayList<>();

		    for (Book_mark b : bookmarks) {
		        BookmarkDTO dto = new BookmarkDTO(b.getId(), b.getTitle(), b.getUrl(), null, b.getAddedTime());
		        bookmarkDTOs.add(dto);
		    }

		    return bookmarkDTOs;
	}

	@Override
	public Book_mark findById(int theId) {
		// TODO Auto-generated method stub
		return repo.findById(theId);
	}

	@Override
	@Transactional
	public Book_mark save(Book_mark theBookmark) {
		// TODO Auto-generated method stub
		
		CustomUserDetail userdetails=
				(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 User loggedInUser = userRepository.findByEmail(userdetails.getUsername());
		 long bookmarkCount=repo.countByUser(loggedInUser);
			
			if (bookmarkCount >= 5) {
			    throw new RuntimeException("You can add only  5 bookmarks");
			}
		 theBookmark.setUser(loggedInUser);
		 
		return repo.save(theBookmark);
	}

	@Override
	@Transactional
	public void deleteByid(int theId) {
		// TODO Auto-generated method stub
		repo.deleteById(theId);
	}
	
	
}



