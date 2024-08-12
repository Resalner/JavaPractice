package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.request.UserInfoRequest;
import com.github.resalner.javapractice.service.UserInfoService;
import com.github.resalner.javapractice.dto.UserInfoResponse;
import com.github.resalner.javapractice.map.UserInfoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user-info")
public class UserInfoController {

    private final UserInfoMapper mapper;
    private final UserInfoService userInfoService;

    @GetMapping
    public List<UserInfoResponse> getUserInfos() {
        List<UserInfo> users = userInfoService.getUsers();
        return mapper.toDomain(users);
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUserInfo(@PathVariable("id") long userId) {
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        return mapper.toResponse(userInfo);
    }

    @PutMapping("/{id}")
    public UserInfoResponse updateUserInfo(@PathVariable("id") long userId, @RequestBody @Valid UserInfoRequest userInfoRequest) {
        UserInfo userInfo = mapper.toUserInfo(userInfoRequest);
        userInfo = userInfoService.updateUserInfo(userId, userInfo);
        return mapper.toResponse(userInfo);
    }
}