package com.github.resalner.javapractice.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.github.resalner.javapractice.dto.RegistrationData;
import com.github.resalner.javapractice.dto.RegistrationDataResponse;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.request.RegistrationDataRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRegistrationMapper {
	
	@Mapping(source = "username", target = "username")
	@Mapping(source = "password", target = "password")
	@Mapping(source = "role", target = "role")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "surname", target = "surname")
	@Mapping(source = "phoneNumber", target = "phoneNumber")
	@Mapping(source = "birthDate", target = "birthDate")
	@Mapping(source = "gender", target = "gender")
	@Mapping(source = "email", target = "email")
	RegistrationData toRegistrationData(RegistrationDataRequest request);

	@Mapping(source = "username", target = "username")
	@Mapping(source = "password", target = "password")
	@Mapping(source = "role", target = "role")
	User toUser(RegistrationData data);

	@Mapping(source = "name", target = "name")
	@Mapping(source = "surname", target = "surname")
	@Mapping(source = "phoneNumber", target = "phoneNumber")
	@Mapping(source = "birthDate", target = "birthDate")
	@Mapping(source = "gender", target = "gender")
	@Mapping(source = "email", target = "email")
	UserInfo toUserInfo(RegistrationData data);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "data.username", target = "username")
	@Mapping(source = "data.role", target = "role")
	@Mapping(source = "data.name", target = "name")
	@Mapping(source = "data.surname", target = "surname")
	@Mapping(source = "data.phoneNumber", target = "phoneNumber")
	@Mapping(source = "data.birthDate", target = "birthDate")
	@Mapping(source = "data.gender", target = "gender")
	@Mapping(source = "data.email", target = "email")
	RegistrationDataResponse toUserRegistrationResponse(RegistrationData data, Long id);
}