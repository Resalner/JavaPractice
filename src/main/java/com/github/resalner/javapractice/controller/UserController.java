package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;
import com.github.resalner.javapractice.request.RegistrationDataRequest;
import com.github.resalner.javapractice.request.UserRequest;
import com.github.resalner.javapractice.service.AuthService;
import com.github.resalner.javapractice.service.UserService;
import com.github.resalner.javapractice.dto.RegistrationData;
import com.github.resalner.javapractice.dto.RegistrationDataResponse;
import com.github.resalner.javapractice.dto.UserResponse;
import com.github.resalner.javapractice.map.UserMapper;
import com.github.resalner.javapractice.map.UserRegistrationMapper;

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
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserMapper mapper;
    private final UserService userService;
    private final UserRegistrationMapper registrationMapper;
    private final AuthService authService;

    @GetMapping
    public List<UserResponse> getUsers() {
        List<User> users = userService.getAllUsers();
        return mapper.toDomain(users);
    }

    @PostMapping
    public UserResponse saveUser(@RequestBody @Valid UserRequest userRequest) {
        User user = mapper.toUser(userRequest);
        userService.saveUser(user);
        return mapper.toResponse(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") long userId) {
        User user = userService.getUser(userId);
        return mapper.toResponse(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable("id") long userId, @RequestBody @Valid UserRequest userRequest) {
        User user = mapper.toUser(userRequest);
        user = userService.updateUser(userId, user);
        return mapper.toResponse(user);
    }
    
    @PostMapping("/user/registration")
	public RegistrationDataResponse registerNewUser(@RequestBody @Valid RegistrationDataRequest registrationData) {
		RegistrationData data = registrationMapper.toRegistrationData(registrationData);
		return registrationMapper.toUserRegistrationResponse(data, authService.registerNewUserAccount(data).getId());
	}
}