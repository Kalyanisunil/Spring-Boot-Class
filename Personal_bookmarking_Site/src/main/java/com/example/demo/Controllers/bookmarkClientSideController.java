package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.Book_mark;
import com.example.demo.Services.BookmarkService;

@Controller
@RequestMapping("/bookmarks")
public class bookmarkClientSideController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping()
    public String viewBookmarks(Model model) {
        model.addAttribute("bookmarks", bookmarkService.findAllForAll());
        return "bookmarks";
    }

  
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bookmark", new Book_mark());
        return "add-bookmarks";
    }

  
    @PostMapping("/add")
    public String addBookmark(@ModelAttribute("bookmark") Book_mark bookmark) {
        bookmarkService.save(bookmark);
        return "redirect:/bookmarks";
    }

   
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("bookmark", bookmarkService.findById(id));
        return "edit-bookmark";
    }


    @PostMapping("/edit/{id}")
    public String updateBookmark(@PathVariable int id, @ModelAttribute("bookmark") Book_mark bookmark) {
        bookmark.setId(id);
        bookmarkService.save(bookmark);
        return "redirect:/bookmarks";
    }

  
    @GetMapping("/delete/{id}")
    public String deleteBookmark(@PathVariable int id) {
        bookmarkService.deleteByid(id);
        return "redirect:/bookmarks";
    }
}
