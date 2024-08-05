
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

import com.github.resalner.javapractice.model.UserToken;
import com.github.resalner.javapractice.repository.UserTokenRepository;

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
	private final static String TOKEN_PREFIX = "Bearer ";
	private final UserTokenRepository userTokenRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    String token = getJWTFromRequest(request);

	    if (StringUtils.hasText(token)) {
	        String username = jwtService.extractUsername(token);

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            UserToken userToken = userTokenRepository.findByUsername(username);

	            if (userToken != null && token.equals(userToken.getAccessToken())) {
	                if (jwtService.isTokenExpired(token)) {

	                    String newToken = jwtService.generateToken(userDetails);
	                    userToken.setAccessToken(newToken);
	                    userTokenRepository.save(userToken);

	                    response.setHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + newToken);
	                } else {
	                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	                            userDetails, null, userDetails.getAuthorities());
	                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	                }
	            }
	        }
	    }

	    filterChain.doFilter(request, response);
	}


	private String getJWTFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(TOKEN_PREFIX.length(), bearerToken.length());
		}

		return null;
	}
}
