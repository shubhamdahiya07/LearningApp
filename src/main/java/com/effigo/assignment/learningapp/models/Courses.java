package com.effigo.assignment.learningapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Courses {
	@Id //Primary Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;

	private String courseTitle;

	private String courseDescription;
	private String courseCategory;
	private int authorId;
}
