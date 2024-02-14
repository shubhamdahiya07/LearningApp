package com.effigo.assignment.learningapp.mappers;

import org.mapstruct.Mapper;

import com.effigo.assignment.learningapp.dtos.UserDto;
import com.effigo.assignment.learningapp.models.Users;

@Mapper
public interface UserMapper {
	UserDto userToDto(Users user);

	Users dtoToUser(UserDto userDto);
}
