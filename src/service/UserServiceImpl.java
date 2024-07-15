package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.request.UserRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void saveUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());
        user.setRole(userRequest.role());
        userRepository.save(user);
    }

    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    public User updateUser(long id, UserRequest userRequest) {
        User us = userRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
        if (Objects.nonNull(userRequest.username())
                && !"".equals(userRequest.username())) {

            us.setUsername(userRequest.username());

        }
        if (Objects.nonNull(userRequest.password())
                && !"".equals(userRequest.password())) {

            us.setPassword(userRequest.password());

        }
        if (Objects.nonNull(userRequest.role())
                && !"".equals(userRequest.role())) {

            us.setRole(userRequest.role());

        }

        userRepository.save(us);
    }
}