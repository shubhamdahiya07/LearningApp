package com.effigo.assignment.learningapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class FavCoursesDetails {
	@Id
	private int id;
	private int userId;
	private int courseId;
}
