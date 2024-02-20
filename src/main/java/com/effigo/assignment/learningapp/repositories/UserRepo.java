package com.effigo.assignment.learningapp.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.effigo.assignment.learningapp.models.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
	public Users findByUserName(String userName);

	@Query(value = "SELECT user_name FROM users ORDER BY user_name", nativeQuery = true)
	public List<String> findAllSortedByNameUsingNative();

	@Query(value = "SELECT user_name,enrolled_courses from (SELECT u.user_id, u.user_name, COUNT(*) AS enrolled_courses FROM users u INNER JOIN enrolled_courses uc ON u.user_id = uc.enrolled_users_id GROUP BY u.user_id, u.user_name)AS subquery", nativeQuery = true)
	public List<Map<String, Object>> getUsersWithEnrolledCourses();
}
