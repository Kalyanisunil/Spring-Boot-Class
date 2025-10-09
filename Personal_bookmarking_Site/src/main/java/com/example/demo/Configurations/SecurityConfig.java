package com.example.demo.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.Services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    // Removed dependency on ApiAuthenticationFilter

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        // Public endpoints
                        .requestMatchers("/login", "/registration", "/css/**", "/js/**", "/bookmarks/public").permitAll() 
                        .anyRequest().authenticated()) // All other requests require authentication
                .formLogin(form -> form
                        .loginPage("/login").loginProcessingUrl("/login")
                        .defaultSuccessUrl("/bookmarks/my", true).permitAll()) // Redirect to private view after login
                .logout(logout -> logout
                        .invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout").permitAll());
        
        // CRITICAL: The line that added ApiAuthenticationFilter is removed.
        	
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
