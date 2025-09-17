package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{

}
