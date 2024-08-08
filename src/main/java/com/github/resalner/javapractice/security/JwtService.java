package com.github.resalner.javapractice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.resalner.javapractice.dto.JwtAuthorisationData;
import com.github.resalner.javapractice.exception.InvalidRefreshTokenException;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.model.UserToken;
import com.github.resalner.javapractice.repository.UserTokenRepository;
import com.github.resalner.javapractice.security.details.UserDetailsImpl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final UserDetailsService userDetailsService;
	private final UserTokenService userTokenService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final UserTokenRepository userTokenRepository;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token-expiration}")
	private long accessExpiration;

	@Value("${jwt.refresh-token-expiration}")
	private long refreshExpiration;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateAccessToken(String token, String usernameFromDB, User user) {
		UserToken userToken = userTokenRepository.findByUserId(user.getId());
		String usernameFromToken = extractUsername(token);
		return (usernameFromToken.equals(usernameFromDB) && !isTokenExpired(token)
				&& token.equals(userToken.getAccessToken()));
	}

	public Boolean validateRefreshToken(String token, UserToken existingUserToken, User user) {
		try {
			String usernameFromToken = extractUsername(token);
			return (usernameFromToken.equals(user.getUsername()) && !isTokenExpired(token));
		} catch (ExpiredJwtException ex) {
			throw new InvalidRefreshTokenException("Токен обновления истек "
					+ existingUserToken.getRefreshTokenExpiryDate() + ". Пожалуйста перезайдите в аккаунт");

		}
	}

	public String generateAccessToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles",
				userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return createToken(claims, userDetails.getUsername(), accessExpiration);
	}

	public String generateRefreshToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername(), refreshExpiration);
	}

	private String createToken(Map<String, Object> claims, String username, long expirationTime) {
		System.out.println(new Date(System.currentTimeMillis() - expirationTime));
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public JwtAuthorisationData generateJwtAuthData(UserDetails userDetails) {
		String accessToken = generateAccessToken(userDetails);
		String refreshToken = generateRefreshToken(userDetails);

		UserToken userToken = userTokenService.createUserToken(userDetails.getUsername(), accessToken, refreshToken);

		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return new JwtAuthorisationData(accessToken, userToken.getRefreshToken(), userDetails.getUsername(), roles);
	}
}
