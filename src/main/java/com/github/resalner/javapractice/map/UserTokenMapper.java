package com.github.resalner.javapractice.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.github.resalner.javapractice.dto.RefreshTokenData;
import com.github.resalner.javapractice.request.RefreshTokenRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserTokenMapper {

	@Mapping(source = "token", target = "refreshToken")
	RefreshTokenData toRefreshTokenData(RefreshTokenRequest request);
}
