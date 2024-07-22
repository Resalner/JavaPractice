package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.request.UserRequest;
import com.github.resalner.javapractice.service.UserService;
import com.github.resalner.javapractice.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users" )
@RequiredArgsConstructor
public class UserController{

  private final UserService userService;
  private final AuthenticationManager authenticationManager;

 
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
  
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserRequest request) {
      try {
          Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
          );

          return ResponseEntity.ok("Login successful");
      } catch (AuthenticationException e) {
          return ResponseEntity.status(401).body("Login failed");
      }
  }
}
