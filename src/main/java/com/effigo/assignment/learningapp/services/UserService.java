package com.effigo.assignment.learningapp.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.assignment.learningapp.dtos.UserDto;
import com.effigo.assignment.learningapp.models.Address;
import com.effigo.assignment.learningapp.models.Courses;
import com.effigo.assignment.learningapp.models.Users;
import com.effigo.assignment.learningapp.repositories.AddressRepo;
import com.effigo.assignment.learningapp.repositories.CoursesRepo;
import com.effigo.assignment.learningapp.repositories.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CoursesRepo courseRepo;

	@Autowired
	private ModelMapper modelMapper;

	public UserDto createUser(Users user) {
		log.info("Received request to create user: {}", user.getUserName());
		Users savedUser = this.userRepo.save(user);
		log.info("User created successfully: {}", savedUser);
		return this.userToDto(savedUser);
	}

	public UserDto updateUser(UserDto userDto, Integer userId) {
		log.info("Received request to update user with ID: {}", userId);
		Users user = this.userRepo.findById(userId).get();
		log.info("User found: {}", user);
		user.setUserName(userDto.getUserName());
		user.setUserRole(userDto.getUserRole());
		Users updatedUser = this.userRepo.save(user);
		UserDto userDto1 = userToDto(updatedUser);
		log.info("User updated successfully: {}", updatedUser);
		return userDto1;
	}

	public UserDto getUserById(Integer userId) {
		log.info("Received request to get user with ID: {}", userId);
		Users user = this.userRepo.findById(userId).get();
		log.info("User found: {}", user);
		return this.userToDto(user);
	}

	public List<UserDto> getAllUsers() {
		log.info("Received request to get all users");
		List<Users> users = this.userRepo.findAll();
		log.info("Found {} users", users.size());
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	public List<String> findAllSortedByName() {
		List<String> users = userRepo.findAllSortedByNameUsingNative();
		return users;
	}

	public void deleteUser(Integer userId) {
		log.info("Received request to delete user with ID: {}", userId);
		Users user = this.userRepo.findById(userId).get();
		log.info("User found: {}", user);
		this.userRepo.delete(user);
		log.info("User deleted successfully");
	}

	public UserDto enrollCourse(Integer userId, Integer courseId) {
		log.info("Received request to enroll user {} in course {}", userId, courseId);
		Users user = userRepo.findById(userId).get();
		Courses course = courseRepo.findById(courseId).get();
		log.info("User found: {}", user);
		log.info("Course found: {}", course);
		// Add the course to the user's courses list
		user.getCourses().add(course);

		// Save the updated user entity, which will automatically persist the change to the enrolled_courses table
		userRepo.save(user);
		log.info("User enrolled in course successfully");
		return this.userToDto(user);
	}

	public UserDto makeCourseFav(Integer userId, Integer courseId) {
		log.info("Received request to mark course {} as favorite for user {}", courseId, userId);
		Users user = userRepo.findById(userId).get();
		Courses course = courseRepo.findById(courseId).get();
		log.info("User found: {}", user);
		log.info("Course found: {}", course);

		// Add the course to the user's courses list
		user.getFavCourses().add(course);

		// Save the updated user entity, which will automatically persist the change to the enrolled_courses table
		userRepo.save(user);
		log.info("Course marked as favorite successfully");
		return this.userToDto(user);
	}

	public UserDto saveAddress(Integer userId, Integer addId) {
		log.info("Request recieved by UserService");
		Users user = userRepo.findById(userId).get();
		Address address = addressRepo.findById(addId).get();
		user.getAddressList().add(address);
		userRepo.save(user);
		return this.userToDto(user);
	}

	public List<Map<String, Object>> getUsersWithNoOfCourses() {
		List<Map<String, Object>> details = userRepo.getUsersWithEnrolledCourses();
		return details;
	}

	private UserDto userToDto(Users user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		//		UserDto userDto = new UserDto();
		//		userDto.setUserId(user.getUserId());
		//		userDto.setUserName(user.getUserName());
		//		userDto.setUserRole(user.getUserRole());
		//		userDto.setCourses(user.getCourses());
		//		userDto.setFavCourses(user.getFavCourses());
		return userDto;
	}

	//	private Users DtoToUser(UserDto userDto) {
	//		Users user = new Users();
	//		user.setUserId(userDto.getUserId());
	//		user.setUserName(userDto.getUserName());
	//		user.setUserRole(userDto.getUserRole());
	//		return user;
	//	}
}
