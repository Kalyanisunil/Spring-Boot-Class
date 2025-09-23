package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Models.Book;
import com.example.demo.Repository.BookRepository;

@Controller
public class BookController {
	
@GetMapping("/Book")
	public String  Book(Model model)
	{
	 Iterable<Book> BookList = bookrepository.findAll();
		model.addAttribute("books",BookList);
		return "books";
	}

	@GetMapping("/addbook")
	public String addBookForm(Model model) {
	    return "add-book";
	}

	@GetMapping("/bookName")
	public String Bookdetails(Model model) {
	    List<Object[]> namesAndPricesList = bookrepository.findNamesAndPrices();
	    model.addAttribute("namesAndPrices", namesAndPricesList);
	    return "bookName";
	}
	
	
	@Autowired
		private BookRepository bookrepository;
	@PostMapping("/saveBook")
	public String Book(Book book, Model model)
	{
		Book b= new Book();
		b.setAuthor_name(book.getAuthor_name());
		b.setTitle(book.getTitle());
		b.setPrice(book.getPrice());
		bookrepository.save(b);
		model.addAttribute("message", "The book " + b.getTitle() +" is saved successfully");
		return "redirect:/Book";
		
	}


	}

