package com.effigo.assignment.learningapp.mappers;

import org.mapstruct.Mapper;

import com.effigo.assignment.learningapp.dtos.CoursesDto;
import com.effigo.assignment.learningapp.models.Courses;

@Mapper
public interface CoursesMapper {
	CoursesDto coursesToDto(Courses course);

	Courses dtoToCourses(CoursesDto coursesDto);
}
