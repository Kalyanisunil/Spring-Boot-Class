package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Models.Book_mark;
import com.example.demo.Models.User;
import com.example.demo.Services.BookmarkService;
import com.example.demo.Services.CustomUserDetail;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookmarks")
public class bookmarkClientSideController {

    @Autowired
    private BookmarkService bookmarkService;
    
   
    private User getCurrentUser(CustomUserDetail userDetails) {
        // This is safe because Spring Security guarantees userDetails is present here.
        return userDetails.getUser();
    }

    // Redirect root to private view
    @GetMapping()
    public String viewRootBookmarks() {
        return "redirect:/bookmarks/my"; // Renamed from /allbookmarks for clarity
    }

 
    @GetMapping("/my")
    public String viewMyBookmarks(@AuthenticationPrincipal CustomUserDetail userDetails, Model model) {
        User currentUser = getCurrentUser(userDetails);

        model.addAttribute("Bookmarks", bookmarkService.findUserBookmarks(currentUser));
        model.addAttribute("currentUser", currentUser);
        
        // This line removes the need for a separate showAddForm mapping
        model.addAttribute("newBookmark", new Book_mark()); 
        return "bookmarks"; // Note: Ensure your template is named 'my-bookmarks.html'
    }
    
  
    @GetMapping("/public")
    public String viewPublicBookmarks(@AuthenticationPrincipal CustomUserDetail userDetails, Model model) {
        User currentUser = getCurrentUser(userDetails);
        
        model.addAttribute("Bookmarks", bookmarkService.findAllPublicBookmarks(currentUser));
        model.addAttribute("currentUser", currentUser); 
        return "bookmarks";
    }
    
    @GetMapping("/search")
    public String searchBookmarks(@RequestParam("keyword") String keyword, Model model) {
        List<Book_mark> results = bookmarkService.searchByTitle(keyword);
        model.addAttribute("bookmarks", results);
        model.addAttribute("newBookmark", new Book_mark());
        model.addAttribute("keyword", keyword);
        return "search-bookmark";
    }

   
    @PostMapping("/add")
    public String addBookmark(@ModelAttribute("newBookmark") Book_mark newBookmark, 
                              @AuthenticationPrincipal CustomUserDetail userDetails,
                              RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser(userDetails);
        
        try {
            // Service checks the max limit (Max 5) and sets the owner securely
            bookmarkService.saveNewBookmark(newBookmark, currentUser);
            redirectAttributes.addFlashAttribute("successMessage", "Bookmark added successfully!");
        } catch (IllegalStateException e) {
            // Handle the "Max 5 Limit Reached" error
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/bookmarks/my";
    }

   
 
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, 
                               @AuthenticationPrincipal CustomUserDetail userDetails, 
                               Model model,
                               RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser(userDetails);
        
        // Find the bookmark ONLY IF it's owned by the current user
        Optional<Book_mark> bookmarkOpt = bookmarkService.findBookmarkIfOwner(id, currentUser);

        if (bookmarkOpt.isPresent()) {
            model.addAttribute("bookmark", bookmarkOpt.get());
            return "edit-bookmarks";
        } else {
            // If the bookmark is not found OR not owned, redirect and show error
            redirectAttributes.addFlashAttribute("errorMessage", "Access Denied: You cannot edit this bookmark.");
            return "redirect:/bookmarks/my";
        }
    }


    @PostMapping("/edit/{id}")
    public String updateBookmark(@PathVariable int id, 
                                 @ModelAttribute("bookmark") Book_mark updatedBookmark,
                                 @AuthenticationPrincipal CustomUserDetail userDetails,
                                 RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser(userDetails);
        
        try {
            // Service checks ownership before updating
            bookmarkService.updateBookmark(id, updatedBookmark, currentUser);
            redirectAttributes.addFlashAttribute("successMessage", "Bookmark updated successfully!");
        } catch (SecurityException e) {
             // Handle the "Access Denied" if ownership fails
             redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/bookmarks/my";
    }

    @PostMapping("/delete/{id}")
    public String deleteBookmark(@PathVariable int id,
                                 @AuthenticationPrincipal CustomUserDetail userDetails,
                                 RedirectAttributes redirectAttributes) {
        User currentUser = getCurrentUser(userDetails);
        
        try {
            // Service checks ownership before deleting
            bookmarkService.deleteBookmark(id, currentUser);
            redirectAttributes.addFlashAttribute("successMessage", "Bookmark deleted successfully!");
        } catch (SecurityException e) {
             // Handle the "Access Denied" if ownership fails
             redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/bookmarks/my";
    }
}
