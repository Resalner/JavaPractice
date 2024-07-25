package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.dto.UserInfoResponse;
import com.github.resalner.javapractice.request.UserInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserInfoMapper {

    UserInfoResponse toResponse(UserInfo userInfo);

    List<UserInfoResponse> toDomain(List<UserInfo> userInfos);

    UserInfo toUserInfo(UserInfoRequest userInfoRequest);
}