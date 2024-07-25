package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;
import com.github.resalner.javapractice.request.UserInfoRequest;
import com.github.resalner.javapractice.service.UserInfoService;
import com.github.resalner.javapractice.dto.UserInfoResponse;
import com.github.resalner.javapractice.map.UserInfoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user-info")
public class UserInfoController {

    @Autowired
    private final UserInfoMapper mapper;
    private final UserInfoService userInfoService;

    @GetMapping
    public List<UserInfoResponse> getUsers() {
        List<UserInfo> users = userInfoService.getUsers();
        return mapper.toDomain(users);
    }

    @PostMapping
    public UserInfoResponse saveUser(@RequestBody @Valid UserInfoRequest userInfoRequest) {
        UserInfo userInfo = mapper.toUserInfo(userInfoRequest);
        userInfoService.saveUserInfo(userInfo);
        return mapper.toResponse(userInfo);
    }

    @GetMapping("/{id}")
    public UserInfoResponse getUser(@PathVariable("id") long userId) {
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        return mapper.toResponse(userInfo);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long userId) {
        userInfoService.deleteUserInfo(userId);
    }

    @PutMapping("/{id}")
    public UserInfoResponse updateUser(@PathVariable("id") long userId, @RequestBody @Valid UserInfoRequest userInfoRequest) {
        UserInfo userInfo = mapper.toUserInfo(userInfoRequest);
        userInfo = userInfoService.updateUserInfo(userId, userInfo);
        return mapper.toResponse(userInfo);
    }
}