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

import com.effigo.assignment.learningapp.mappers.CoursesMapper;
import com.effigo.assignment.learningapp.models.Courses;
import com.effigo.assignment.learningapp.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	private CoursesMapper courseMapper;

	@Autowired
	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Courses>> getAllCourses() {
		logger.info("Received request to get all courses");
		List<Courses> courses = this.courseService.getAllCourses();
		logger.info("Retrieved {} courses", courses.size());
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<Courses> getCoursesById(@PathVariable Integer courseId) {
		logger.info("Received request to get course by ID: {}", courseId);
		Courses course = this.courseService.getCoursesById(courseId);
		logger.info("Retrieved course: {}", course);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PostMapping("/{userId}")
	public ResponseEntity<Courses> createCourse(@RequestBody Courses course, @PathVariable Integer userId) {
		logger.info("Received request to create course: {} by user {}", course.getCourseTitle(), userId);
		Courses newCourse = this.courseService.createCourse(course, userId);
		logger.info("Course created successfully: {}", newCourse);
		return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	}

	@DeleteMapping("/{courseId}/{userId}")
	public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId, @PathVariable Integer userId) {
		logger.info("Received request to delete course ID: {} by user {}", courseId, userId);
		courseService.deleteCourse(courseId, userId);
		logger.info("Course deleted successfully");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{courseId}/{userId}")
	public ResponseEntity<Courses> updateCourse(@RequestBody Courses course, @PathVariable Integer courseId,
			@PathVariable Integer userId) {
		logger.info("Received request to update course ID: {} by user {}", courseId, userId);
		Courses updatedCourse = this.courseService.updateCourse(course, courseId, userId);
		logger.info("Course updated successfully: {}", updatedCourse);
		return new ResponseEntity<>(updatedCourse, HttpStatus.CREATED);
	}
}
