package com.github.resalner.javapractice.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private static final String TOKEN_PREFIX = "Bearer ";
	private final UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    String token = getJWTFromRequest(request);

	    if (token != null) {
	        String username = jwtService.extractUsername(token);

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            User user = userRepository.findByUsername(username)
	                    .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + username));

	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            if (jwtService.validateAccessToken(token, username, user)) {
	                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
	                        null, userDetails.getAuthorities());
	                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	            }
	        }
	    }

	    filterChain.doFilter(request, response);
	}


	private String getJWTFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		return (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX))
				? bearerToken.substring(TOKEN_PREFIX.length())
				: null;
	}
}
