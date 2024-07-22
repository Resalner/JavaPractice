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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    @Override
    public User updateUser(long id, User userForUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
        if (Objects.nonNull(userForUpdate.username())
                && !"".equals(userForUpdate.username())) {

            user.setUsername(userForUpdate.username());
        }
        if (Objects.nonNull(userForUpdate.password())
                && !"".equals(userForUpdate.password())) {

            user.setPassword(userForUpdate.password());
        }
        if (Objects.nonNull(userForUpdate.role())
                && !"".equals(userForUpdate.role())) {

            user.setRole(userForUpdate.role());
        }
        user = userRepository.save(user);
        return user;
    }
}