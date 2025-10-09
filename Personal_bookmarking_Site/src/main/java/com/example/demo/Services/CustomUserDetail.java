package com.example.demo.Services;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Models.User;


public class CustomUserDetail implements UserDetails {


    private final User user; // Made final for best practice


    public CustomUserDetail(User user) {
        this.user = user;
    }

    // CRITICAL: This is the missing piece that allows the controller to access the User object
    public User getUser() {
        return this.user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming no specific roles for simplicity
        return List.of(); 
    }


    // Changed getFullname() to getUsername() of the User model for clarity
    public String getFullname() { 
        return user.getUsername();
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public String getUsername() {
       
        return user.getEmail(); // Email used as the login username
    }


    @Override
    public boolean isAccountNonExpired() {
   
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
   
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
   
        return true;
    }


   @Override
   public boolean isEnabled() {
	   return true;
   }
}
