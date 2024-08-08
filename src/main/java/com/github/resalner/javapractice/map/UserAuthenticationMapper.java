package com.github.resalner.javapractice.map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.github.resalner.javapractice.dto.JwtAuthorisationData;
import com.github.resalner.javapractice.dto.JwtResponse;
import com.github.resalner.javapractice.dto.UserCredentials;
import com.github.resalner.javapractice.request.LoginRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserAuthenticationMapper {

	@Mapping(source = "username", target = "login")
	@Mapping(source = "password", target = "password")
	UserCredentials toUserCredentials(LoginRequest loginRequest);

	@Mapping(source = "accessToken", target = "accessToken")
	@Mapping(source = "refreshToken", target = "refreshToken")
	@Mapping(source = "username", target = "username")
	@Mapping(source = "roles", target = "roles")
	JwtResponse toJwtResponse(JwtAuthorisationData authorisationData);
}
