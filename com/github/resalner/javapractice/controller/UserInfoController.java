package com.github.resalner.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;
import com.github.resalner.javapractice.request.UserInfoRequest;
import com.github.resalner.javapractice.service.UserInfoService;
import java.util.List;

@RestController
@RequestMapping(path = "/api/userinfo" )
public class UserInfoController{
  private final UserInfoService userInfoService;
  public UserInfoController(UserInfoService userInfoService){
    this.userInfoService = userInfoService;
  }
// получение списка пользователей
  @GetMapping
  public List<UserInfo> getUsers(){
    return userInfoService.getUsers();
  } 
// добавление нового пользователя
  @PostMapping("/addUserInfo")
  public void saveUser(@RequestBody @Valid UserInfoRequest userInfoRequest){
    userInfoService.addUserInfo(userInfoRequest);
  }
  @GetMapping("/getUserInfo/{id}")
  public UserInfo getUser(@PathVariable("id") long userId){
    return userInfoService.getUserInfo(userId);
  }
  @DeleteMapping("/deleteUserInfo/{id}")
  public void deleteUser(@PathVariable("id") long userId){
    userInfoService.deleteUserInfo(userId);
  }
  @PostMapping("/updateUserInfo/{id}")
  public UserInfo updateUser(@PathVariable("id") long userId, @RequestBody @Valid UserInfoRequest userInfoRequest){
    return userInfoService.updateUserInfo(userId, userInfoRequest);
  }
}