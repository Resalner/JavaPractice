package com.github.resalner.javapractice.service.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.config.SecUserDetails;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class SecUserDetailsService implements UserDetailsService{

	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByUsername(username);
		return user.map(SecUserDetails :: new)
				.orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + username));
	}

}
