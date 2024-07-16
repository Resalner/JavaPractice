package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;
import com.github.resalner.javapractice.request.UserInfoRequest;
import com.github.resalner.javapractice.service.UserInfoService;
import com.github.resalner.javapractice.dto.UserInfoResponse;
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

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user-info" )
public class UserInfoController{
  @Autowired
  private UserInfoService userInfoService;

  @GetMapping
  public List<UserInfoResponse> getUsers(){
    return userInfoService.getUsers();
  } 
  
  @PostMapping
  public UserInfoResponse saveUser(@RequestBody @Valid UserInfoRequest userInfoRequest){
    userInfoService.addUserInfo(userInfoRequest);
  }
  
  @GetMapping("/{id}")
  public UserInfoResponse getUser(@PathVariable("id") long userId){
    return userInfoService.getUserInfo(userId);
  }
  
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") long userId){
    userInfoService.deleteUserInfo(userId);
  }
  
  @PutMapping("/{id}")
  public UserInfoResponse updateUser(@PathVariable("id") long userId, @RequestBody @Valid UserInfoRequest userInfoRequest){
    return userInfoService.updateUserInfo(userId, userInfoRequest);
  }
}