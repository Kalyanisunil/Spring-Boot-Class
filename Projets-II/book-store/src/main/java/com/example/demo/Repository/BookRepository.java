package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{
	
@Query("Select b.title,b.price from Book b")
	List<Object[]> findNamesAndPrices();
	// TODO Auto-generated method stub



}
