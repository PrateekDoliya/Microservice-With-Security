package com.security.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.library.jwt.JwtAuthenticationEntryPoint;
import com.security.library.jwt.JwtRequestFilter;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public SecurityFilterChain filterChain( HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
			.cors().disable()
			.csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/v1/login").permitAll()
			.anyRequest()
			.authenticated().and()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint).and()
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		return httpSecurity.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(this.passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
}
