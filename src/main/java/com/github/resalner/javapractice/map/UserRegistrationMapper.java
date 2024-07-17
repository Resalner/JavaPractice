package com.github.resalner.javapractice.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.github.resalner.javapractice.dto.UserInfoResponse;
import com.github.resalner.javapractice.dto.UserRegistrationDataResponse;
import com.github.resalner.javapractice.dto.UserResponse;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.request.UserRegistrationDataRequest;

@Mapper
public interface UserRegistrationMapper {
	UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);
	
	@Mapping(source = "username", target = "username")
	@Mapping(source = "password", target = "password")
	@Mapping(source = "role", target = "role")
	User toUser(UserRegistrationDataRequest request);
	
	@Mapping(source = "name", target = "name")
	@Mapping(source = "surname", target = "surname")
	@Mapping(source = "phoneNumber", target = "phoneNumber")
	@Mapping(source = "birthDate", target = "birthDate")
	@Mapping(source = "gender", target = "gender")
	@Mapping(source = "email", target = "email")
	UserInfo toUserInfo(UserRegistrationDataRequest request);
	
	@Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.role", target = "role")
    @Mapping(source = "userInfo.name", target = "name")
    @Mapping(source = "userInfo.surname", target = "surname")
    @Mapping(source = "userInfo.phoneNumber", target = "phoneNumber")
    @Mapping(source = "userInfo.birthDate", target = "birthDate")
    @Mapping(source = "userInfo.gender", target = "gender")
    @Mapping(source = "userInfo.email", target = "email")
    UserRegistrationDataResponse toUserRegistrationResponse(User user, UserInfo userInfo);
}
