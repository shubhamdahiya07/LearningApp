//package com.effigo.assignment.learningapp.security;
//
//import java.util.List;
//
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.http.HttpServletRequest;
//
//@EnableWebSecurity
//public class SpringSecurityConfiguration implements SecurityFilterChain {
//
//	public void configure(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable()) // Disable CSRF (consider enabling later for production)
//				.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults()); // Use withDefaults() for default configuration
//	}
//
//	@Override
//	public boolean matches(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public List<Filter> getFilters() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
