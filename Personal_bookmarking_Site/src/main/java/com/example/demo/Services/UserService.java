package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDto;
import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepository;


@Service
public class UserService{
   
    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @Autowired
    private UserRepository userRepository;

    public User save(UserDto userDto) {
        User user = new User(0, userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getUsername(), null);
        return userRepository.save(user);
    }
}