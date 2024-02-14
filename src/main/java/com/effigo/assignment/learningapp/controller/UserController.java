package com.effigo.assignment.learningapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.assignment.learningapp.dtos.UserDto;
import com.effigo.assignment.learningapp.models.Users;
import com.effigo.assignment.learningapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		logger.info("Received request to get all users");
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody Users user) {
		logger.info("Received request to create user: {}", user.getUserName());
		UserDto createUserDto = userService.createUser(user);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
		logger.info("Received request to update user: {}", userDto.getUserName());
		UserDto updatedUser = userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
		logger.info("Received request to delete user: {}", userId);
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		logger.info("Received request to get user by id: {}", userId);
		UserDto userDto = userService.getUserById(userId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PostMapping("/{userId}/enroll/{courseId}")
	public ResponseEntity<UserDto> enrollCourse(@PathVariable Integer userId, @PathVariable Integer courseId) {
		logger.info("Received request to enroll user {} in course {}", userId, courseId);
		UserDto userDto = userService.enrollCourse(userId, courseId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PostMapping("/{userId}/makefav/{courseId}")
	public ResponseEntity<UserDto> makeCourseFav(@PathVariable Integer userId, @PathVariable Integer courseId) {
		logger.info("Received request to make course {} favorite for user {}", courseId, userId);
		UserDto userDto = userService.makeCourseFav(userId, courseId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@GetMapping("/sorted-by-name")
	public ResponseEntity<List<String>> getEmployeesSortedByName() {
		List<String> users = userService.findAllSortedByName();
		return ResponseEntity.ok(users);
	}

	@PostMapping("/{userId}/address/{addId}")
	public ResponseEntity<UserDto> saveAddress(@PathVariable Integer userId, @PathVariable Integer addId) {
		logger.info("Received request to save address {} in user {}", userId, addId);
		UserDto userDto = userService.saveAddress(userId, addId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
}
