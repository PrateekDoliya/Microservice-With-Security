package com.auth.service.services.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth.service.payloads.LoginRequest;
import com.auth.service.payloads.LoginResponse;
import com.auth.service.payloads.User;
import com.auth.service.repositories.LoginRepository;
import com.auth.service.services.LoginService;
import com.security.library.jwt.util.JwtTokenUtil;
import com.security.library.payloads.CommonResponse;

@Service
public class LoginServiceImpl<T> implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public T login(LoginRequest request) {
		if(request.getEmail() == null || request.getPassword() == null ) {
			logger.info("LoginService :  UserName and Password Can't be empty!!");
			return (T) new CommonResponse("UserName and Password Can't be empty!!", 400 );	 
		}
		
		User user = this.loginRepository.findByEmail(request.getEmail());
		logger.info("LoginService : Login User : {}", user);
		
		if(user == null) {
			logger.info("LoginService : User not found !!");
			return (T) new CommonResponse("User not found!!", 404);
		}
		
		if(!user.getPassword().equals(request.getPassword())) {
			logger.info("LoginService : Password mismatch !!");
			return (T) new CommonResponse("invalid Password", 404);
		}
		
		logger.info("LoginService : Password matched !!");
		UserDetails userDetails= new org.springframework.security.core.userdetails.User(request.getEmail(), request.getPassword(), false, false, false, false, new ArrayList<>());
		
		String token = this.jwtTokenUtil.generateToken(userDetails);
		logger.info("LoginService : login Success : {}", user);
		
		
		return (T) new LoginResponse(token, user);
		
	}

	
	
}
