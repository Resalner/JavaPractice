package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    User toUser(UserResponse userResponse);
}