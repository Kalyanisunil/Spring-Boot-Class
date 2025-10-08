package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	   User findByEmail(String email);
	    User findByToken(String token);
	    boolean existsByToken(String token);
}
