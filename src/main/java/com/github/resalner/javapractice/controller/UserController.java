package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.request.UserRegistrationDataRequest;
import com.github.resalner.javapractice.request.UserRequest;
import com.github.resalner.javapractice.service.AuthService;
import com.github.resalner.javapractice.service.UserService;
import com.github.resalner.javapractice.dto.UserInfoResponse;
import com.github.resalner.javapractice.dto.UserRegistrationDataResponse;
import com.github.resalner.javapractice.dto.UserResponse;
import com.github.resalner.javapractice.map.UserRegistrationMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users" )
@RequiredArgsConstructor
public class UserController{

  private final UserService userService;
  private final AuthService authService;
  private final UserRegistrationMapper mapper = UserRegistrationMapper.INSTANCE;
  
  @GetMapping
  public List<UserResponse> getUsers(){
    return userService.getUsers();
  } 

  @PostMapping
  public UserResponse saveUser(@RequestBody @Valid UserRequest userRequest){
    userService.addUser(userRequest);
  }
  
  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable("id") long userId){
    return userService.getUser(userId);
  }
  
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") long userId){
    userService.deleteUser(userId);
  }
  
  @PutMapping("/{id}")
  public UserResponse updateUser(@PathVariable("id") long userId, @RequestBody @Valid UserRequest userRequest){
    return userService.updateUser(userId, userRequest);
  }
  
  @PostMapping("/user/registration")
  public UserRegistrationDataResponse registerNewUser(@RequestBody @Valid UserRegistrationDataRequest registrationData ) {
	  User user = mapper.toUser(registrationData);
	  UserInfo userInfo = mapper.toUserInfo(registrationData);
	  userInfo.setUser(user);
	  user.setInfo(userInfo);
	  
	  authService.registerNewUserAccount(user, userInfo);

	  return mapper.toUserRegistrationResponse(user, userInfo);
  }
}