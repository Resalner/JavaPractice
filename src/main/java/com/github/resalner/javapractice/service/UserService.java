package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.NotFoundException;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.request.UserRequest;
import java.util.List;

@Service
public class UserService{
  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public List<User> getUsers(){
    return userRepository.findAll();
  }

  public void addUser(UserRequest userRequest){
    User user = new User();
    user.setUsername(userRequest.username());
    user.setPassword(userRequest.password());
    user.setRole(userRequest.role());
    userRepository.save(user);
  }

  public User getUser(long id){
    return userRepository.findById(id).orElseThrow(()->NotFoundException("не найден пользователь с id = "+id));
  }

  public void deleteUser(long id){
    userRepository.deleteById(id).orElseThrow(()->NotFoundException("не найден пользователь с id = "+id));
  }

  public User updateUser(long id, UserRequest userRequest){
    User us = userRepository.findById(id).orElseThrow(()->NotFoundException("не найден пользователь с id = "+id));
    if(Objects.nonNull(userRequest.username()) && !"".equals(userRequest.username())){
      us.setUsername(userRequest.username());
    }
    if(Objects.nonNull(userRequest.password()) && !"".equals(userRequest.password())){
      us.setPassword(userRequest.password());
    }
    if(Objects.nonNull(userRequest.role()) && !"".equals(userRequest.role())){
      us.setRole(userRequest.role());
    }
    userRepository.save(us);
  }
}