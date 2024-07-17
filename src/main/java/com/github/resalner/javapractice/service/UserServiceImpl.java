package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implement UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
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

    public User updateUser(long id, User user) {
        User us = userRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
        if (Objects.nonNull(user.username())
                && !"".equals(user.username())) {

            us.setUsername(user.username());
        }
        if (Objects.nonNull(user.password())
                && !"".equals(user.password())) {

            us.setPassword(user.password());
        }
        if (Objects.nonNull(user.role())
                && !"".equals(user.role())) {

            us.setRole(user.role());
        }
        userRepository.save(us);
        return us;
    }
}