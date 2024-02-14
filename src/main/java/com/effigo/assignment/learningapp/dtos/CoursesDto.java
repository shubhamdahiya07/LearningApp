package com.effigo.assignment.learningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDto {
	private Long courseId;

	private String courseTitle;

	private String courseDescription;
	private String courseCategory;
	private int authorId;
}
