package com.effigo.assignment.learningapp.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.assignment.learningapp.models.Courses;
import com.effigo.assignment.learningapp.models.Users;
import com.effigo.assignment.learningapp.repositories.CoursesRepo;
import com.effigo.assignment.learningapp.repositories.UserRepo;

@Service
public class CourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

	@Autowired
	private CoursesRepo courseRepo;

	@Autowired
	private UserRepo userRepo;

	public CourseService(CoursesRepo courseRepo) {
		super();
		this.courseRepo = courseRepo;
	}

	//	public Courses addCourse(Courses course) {
	//		return courseRepo.save(course);
	//	}

	public Courses createCourse(Courses course, Integer userId) {

		logger.info("Received request to create course: {} by user {}", course.getCourseTitle(), userId);
		Optional<Users> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			throw new IllegalArgumentException("User with ID " + userId + " doesn't exist!");
		}

		if (!user.get().getUserRole().equals("Admin") && !user.get().getUserRole().equals("Author")) {
			logger.error("User with ID {} doesn't have permission to create courses", userId);
			throw new SecurityException("Only users with roles 'Admin' or 'Author' can create courses.");
		}
		course.setAuthorId(userId);
		logger.info("Course created successfully: {}", course);
		return this.courseRepo.save(course);
	}

	public Courses updateCourse(Courses course, Integer courseId, Integer userId) {

		logger.info("Received request to update course ID: {} by user {}", courseId, userId);
		Courses updatedCourse = this.courseRepo.findById(courseId).get();

		// Check if user exists and has Admin role
		Optional<Users> user = this.userRepo.findById(userId);
		if (!user.isPresent()) {
			logger.error("Course with ID {} not found", courseId);
			throw new IllegalArgumentException("User with ID " + userId + " doesn't exist!");
		} else if (!user.get().getUserRole().equals("Admin")) {
			logger.error("User with ID {} doesn't have permission to update this course", userId);
			throw new SecurityException("Only users with role 'Admin' can update courses.");
		}
		updatedCourse.setAuthorId(course.getAuthorId());
		updatedCourse.setCourseDescription(course.getCourseDescription());
		updatedCourse.setCourseCategory(course.getCourseCategory());
		updatedCourse.setCourseTitle(course.getCourseTitle());
		Courses newCourseDetails = this.courseRepo.save(updatedCourse);
		logger.info("Course updated successfully: {}", updatedCourse);
		return newCourseDetails;
	}

	public void deleteCourse(Integer courseId, Integer userId) {
		logger.info("Received request to delete course ID: {} by user {}", courseId, userId);
		Optional<Users> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			throw new IllegalArgumentException("User with ID " + userId + " doesn't exist!");
		} else if (!user.get().getUserRole().equals("Admin")) {
			throw new SecurityException("Only users with role 'Admin' can delete courses.");
		}

		Courses course = courseRepo.findById(courseId)
				.orElseThrow(() -> new NoSuchElementException("Course with ID " + courseId + " not found"));
		logger.error("Course with ID {} not found", courseId);
		this.courseRepo.delete(course);
	}

	public Courses getCoursesById(Integer courseId) {
		logger.error("Course with ID {} is retrieved", courseId);
		return this.courseRepo.findById(courseId).get();
	}

	public List<Courses> getAllCourses() {
		logger.error("All courses are retrieved");
		return this.courseRepo.findAll();
	}
}
