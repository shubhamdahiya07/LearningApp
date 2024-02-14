package com.effigo.assignment.learningapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.effigo.assignment.learningapp.models.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
	public Users findByUserName(String userName);

	@Query(value = "SELECT user_name FROM users ORDER BY user_name", nativeQuery = true)
	public List<String> findAllSortedByNameUsingNative();
}
