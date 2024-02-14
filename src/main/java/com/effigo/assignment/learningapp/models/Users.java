package com.effigo.assignment.learningapp.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok is creating getter setter and toString functions
@Entity //JPA is creating and mapping this model to table in database
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id //Primary Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String userName;
	private String userPassword;
	private String userRole;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "enrolled_courses", joinColumns = @JoinColumn(name = "enrolled_users_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "courses_id_details", referencedColumnName = "courseId"))
	private List<Courses> courses = new ArrayList<Courses>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "fav_courses", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"))
	private List<Courses> favCourses = new ArrayList<Courses>();
}
