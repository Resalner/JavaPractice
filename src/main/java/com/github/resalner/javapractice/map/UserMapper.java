package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.dto.UserResponse;
import com.github.resalner.javapractice.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toResponse(User user);

    List<UserResponse> toDomain(List<User> users);

    User toUser(UserRequest userRequest);
}