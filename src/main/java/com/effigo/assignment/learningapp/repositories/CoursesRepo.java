package com.effigo.assignment.learningapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.effigo.assignment.learningapp.models.Courses;

public interface CoursesRepo extends JpaRepository<Courses, Integer> {

}
