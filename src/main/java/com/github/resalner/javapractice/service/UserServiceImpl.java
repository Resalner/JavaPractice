package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Role;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
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
				.orElseThrow(() -> new EntityNotFoundException("не найден пользователь с id = " + id));
	}

	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(long id, User userForUpdate) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден пользователь с id = " + id));

		String username = userForUpdate.getUsername();
		String password = userForUpdate.getPassword();
		Role role = userForUpdate.getRole();

		if (Objects.nonNull(username) && !"".equals(username)) {

			user.setUsername(username);
		}
		if (Objects.nonNull(password) && !"".equals(password)) {

			user.setPassword(password);
		}
		if (Objects.nonNull(role)) {

			user.setRole(role);
		}
		user = userRepository.save(user);
		return user;
	}

}