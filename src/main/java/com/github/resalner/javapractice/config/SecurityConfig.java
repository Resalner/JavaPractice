/*
 * package com.github.resalner.javapractice.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationProvider; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configurers.
 * AbstractHttpConfigurer; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter;
 * 
 * import com.github.resalner.javapractice.security.JwtAuthenticationFilter;
 * import
 * com.github.resalner.javapractice.security.details.UserDetailsServiceImpl;
 * 
 * import lombok.NoArgsConstructor;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @NoArgsConstructor public class SecurityConfig {
 * 
 * @Bean BCryptPasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean SecurityFilterChain securityFilterChain(HttpSecurity http,
 * JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception { return
 * http.csrf(AbstractHttpConfigurer::disable) .authorizeHttpRequests(auth ->
 * auth.requestMatchers("/api/v1/register").permitAll()
 * .requestMatchers("/api/v1/**").authenticated()) .sessionManagement(session ->
 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 * .formLogin(formLogin -> formLogin.disable())
 * .addFilterBefore(jwtAuthenticationFilter,
 * UsernamePasswordAuthenticationFilter.class).build(); }
 * 
 * @Bean AuthenticationProvider authenticationProvider(UserDetailsServiceImpl
 * userDetailsService) { DaoAuthenticationProvider provider = new
 * DaoAuthenticationProvider();
 * provider.setUserDetailsService(userDetailsService);
 * provider.setPasswordEncoder(passwordEncoder()); return provider; } }
 */