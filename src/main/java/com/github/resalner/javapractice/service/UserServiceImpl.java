package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.resalner.javapractice.exception.EntityAlreadyExistsException;
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
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(long id) {
		return getUserIfExists(id);
	}

	@Override
	public void deleteUser(long id) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("Не найден продукт с id = " + id);
		}
		
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(long id, User userForUpdate) {
		User user = getUserIfExists(id);

		if (!user.getUsername().equals(userForUpdate.getUsername()) && 
				usernameExists(userForUpdate.getUsername())) {
			throw new EntityAlreadyExistsException("Аккаунт с таким логином уже есть: " + userForUpdate.getUsername());
		}
		
		String username = userForUpdate.getUsername();
		String password = userForUpdate.getPassword();
		Role role = userForUpdate.getRole();

		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(role);
		
		user = userRepository.save(user);
		return user;
	}

	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	private User getUserIfExists(long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден пользователь с id = " + id));

		return user;
	}
}