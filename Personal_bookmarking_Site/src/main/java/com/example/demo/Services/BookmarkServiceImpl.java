//package com.example.demo.Services;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.DTO.BookmarkDTO;
//import com.example.demo.DTO.BookmarkDisplayDTO;
//import com.example.demo.Models.Book_mark;
//import com.example.demo.Models.User;
//import com.example.demo.Repository.BookmarkRepository;
//import com.example.demo.Repository.UserRepository;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class BookmarkServiceImpl implements BookmarkService {
// 
//	@Autowired
//	private BookmarkRepository repo;
//	@Autowired
//	private  UserRepository userRepository;
//	
//	
//	public BookmarkServiceImpl(BookmarkRepository bookmarkrepo ,UserRepository userRepo)
//	{
//		repo=bookmarkrepo;
//		userRepository=userRepo;
//		
//	}
//
//	@Override
//	public List<BookmarkDTO> findAll() {
//		// TODO Auto-generated method stub
//
//		CustomUserDetail userdetails=
//				(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		 User loggedInUser = userRepository.findByEmail(userdetails.getUsername());
//		 
//		  List<Book_mark> bookmarks = repo.findByUser(loggedInUser);
//		    List<BookmarkDTO> bookmarksDTOs = new ArrayList<>();
//
//		    for (Book_mark b : bookmarks) {
//		        BookmarkDTO dto = new BookmarkDTO(b.getId(), b.getTitle(), b.getUrl(),null, b.getAddedTime());
//		        bookmarksDTOs.add(dto);
//		    }
//
//		    return bookmarksDTOs;
//	}
//	
//	@Override
//	public List<BookmarkDisplayDTO> findAllForAll() {
//	    List<Book_mark> bookmarks = repo.findAll();
//	    List<BookmarkDisplayDTO> bookmarkDTOs = new ArrayList<>();
//
//	    for (Book_mark b : bookmarks) {
//	    	BookmarkDisplayDTO dto = new BookmarkDisplayDTO(b.getTitle(), b.getUrl());
//	        bookmarkDTOs.add(dto);
//	    }
//
//	    return bookmarkDTOs;
//	}
//
//	
//
//	@Override
//	@Transactional
//	public Book_mark save(Book_mark theBookmark) {
//		// TODO Auto-generated method stub
//		
//		CustomUserDetail userdetails=
//				(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		 User loggedInUser = userRepository.findByEmail(userdetails.getUsername());
//		 long bookmarkCount=repo.countByUser(loggedInUser);
//			
//			if (bookmarkCount >= 5) {
//			    throw new RuntimeException("You can add only  5 bookmarks");
//			}
//		 theBookmark.setUser(loggedInUser);
//		 
//		return repo.save(theBookmark);
//	}
//
//	@Override
//	@Transactional
//	public void deleteByid(int theId) {
//	    Book_mark bookmark = repo.findById(theId);
//	    if (bookmark == null) {
//	        throw new RuntimeException("Bookmark not found");
//	    }
//
//	    CustomUserDetail userdetails =
//	        (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	    User loggedInUser = userRepository.findByEmail(userdetails.getUsername());
//
//	    if (!(bookmark.getUser().getId()==loggedInUser.getId())) {
//	        throw new RuntimeException("Access denied!");
//	    }
//
//	    repo.delete(bookmark);
//	}
//
//	
//	
//	
//}



//
//
//



package com.example.demo.Services;

import com.example.demo.Models.Book_mark;
import com.example.demo.Models.User;
import com.example.demo.Repository.BookmarkRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkServiceImpl implements BookmarkService {
 
	private final BookmarkRepository repo;
    private static final int MAX_BOOKMARKS = 5;

	// Use constructor injection
	public BookmarkServiceImpl(BookmarkRepository bookmarkrepo) {
		this.repo = bookmarkrepo;
	}

    // --- 1. Viewing Logic ---

	@Override
	public List<Book_mark> findUserBookmarks(User user) {
		return repo.findByUser(user);
	}

    // Get all public bookmarks (excluding the current user's)
	@Override
	public List<Book_mark> findAllPublicBookmarks(User currentUser) {
		// Fetch all and filter out the current user's bookmarks
	    return repo.findAll().stream()
                .filter(b -> b.getUser() != null && b.getUser().getId() != currentUser.getId())
                .toList();
	}

    // Find a specific bookmark only if it belongs to the user
    @Override
    public Optional<Book_mark> findBookmarkIfOwner(int id, User user) {
        return repo.findByIdAndUser(id, user);
    }
    
    // --- 2. Saving/Adding New Bookmark (with Limit Check) ---
    
	@Override
    @Transactional
	public Book_mark saveNewBookmark(Book_mark bookmark, User user) {
        // Enforce max 5 limit
		long currentCount = repo.countByUser(user);
			
		if (currentCount >= MAX_BOOKMARKS) {
		    throw new IllegalStateException("Limit Reached: You can only add up to " + MAX_BOOKMARKS + " bookmarks.");
		}

        // Set the owner before saving
		bookmark.setUser(user);
		return repo.save(bookmark);
	}

    // --- 3. Update Existing Bookmark (with Ownership Check) ---

    @Override
    @Transactional
    public Book_mark updateBookmark(int id, Book_mark updatedData, User user) {
        // Use the secure method to find the bookmark and ensure ownership
        return repo.findByIdAndUser(id, user).map(existingBookmark -> {
            // Update only mutable fields
            existingBookmark.setTitle(updatedData.getTitle());
            existingBookmark.setUrl(updatedData.getUrl());
            
            return repo.save(existingBookmark);
        }).orElseThrow(() -> new SecurityException("Access Denied: Bookmark not found or you do not own it."));
    }
    
  

    @Override
    @Transactional
    public void deleteBookmark(int id, User user) {
        // Use the secure method to find the bookmark and ensure ownership
	    Optional<Book_mark> bookmarkToDelete = repo.findByIdAndUser(id, user);

	    if (bookmarkToDelete.isEmpty()) {
	        throw new SecurityException("Access denied: Bookmark not found or you do not own it.");
	    }
        
	    repo.deleteById(id);
    }

	@Override
	public List<Book_mark> searchByTitle(String title) {
		// TODO Auto-generated method stub
		return repo.findByTitle(title);
	}
}
