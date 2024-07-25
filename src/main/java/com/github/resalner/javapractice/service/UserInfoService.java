package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.UserInfo;


import java.util.List;

public interface UserInfoService {
    List<UserInfo> getUsers();

    UserInfo saveUserInfo(UserInfo userInfo);

    UserInfo updateUserInfo(long id, UserInfo userInfo);
  
    UserInfo getUserInfo(long id);

    void deleteUserInfo(long id);
}