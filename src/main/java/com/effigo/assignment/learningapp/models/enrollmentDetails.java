package com.effigo.assignment.learningapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class enrollmentDetails {
	@Id
	private int id;
	private String userId;
	private String courseId;
}
