package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.request.UserInfoRequest;

import java.util.List;

public interface UserInfoServiceInterface {
    List<UserInfo> getUsers();

    UserInfo getUserInfo(long id);

    void addUserInfo(UserInfoRequest userInfoRequest);

    UserInfo updateUserInfo(long id, UserInfoRequest userInfoRequest);

    void deleteUserInfo(long id);
}