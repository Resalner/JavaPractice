package com.github.resalner.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.request.UserRequest;

import com.github.resalner.javapractice.service.UserService;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users" )
public class UserController{
  private final UserService userService;
  public UserController(UserService userService){
    this.userService = userService;
  }
// получение списка пользователей
  @GetMapping
  public List<User> getUsers(){
    return userService.getUsers();
  } 
// добавление нового пользователя
  @PostMapping("/addUser")
  public void saveUser(@RequestBody @Valid UserRequest userRequest){
    userService.addUser(userRequest);
  }
  @GetMapping("/getUser/{id}")
  public User getUser(@PathVariable("id") long userId){
    return userService.getUser(userId);
  }
  @DeleteMapping("/deleteUser/{id}")
  public void deleteUser(@PathVariable("id") long userId){
    userService.deleteUser(userId);
  }
  @PostMapping("/updateUser/{id}")
  public User updateUser(@PathVariable("id") long userId, @RequestBody @Valid UserRequest userRequest){
    return userService.updateUser(userId, userRequest);
  }
}