/*
 * package com.github.resalner.javapractice.security.details;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.github.resalner.javapractice.model.User; import
 * com.github.resalner.javapractice.repository.UserRepository;
 * 
 * import jakarta.persistence.EntityNotFoundException; import
 * lombok.RequiredArgsConstructor;
 * 
 * @Service
 * 
 * @RequiredArgsConstructor public class UserDetailsServiceImpl implements
 * UserDetailsService {
 * 
 * private final UserRepository repository;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { Optional<User> user =
 * repository.findByUsername(username); return user.map(UserDetailsImpl::new)
 * .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " +
 * username)); } }
 */