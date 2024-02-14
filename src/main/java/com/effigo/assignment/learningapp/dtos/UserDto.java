package com.effigo.assignment.learningapp.dtos;

import java.util.ArrayList;
import java.util.List;

import com.effigo.assignment.learningapp.models.Courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private int userId;
	private String userName;
	private String userRole;
	private List<Courses> courses = new ArrayList<Courses>();
	private List<Courses> favCourses = new ArrayList<Courses>();
}
