package com.auth.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.service.payloads.LoginRequest;
import com.auth.service.services.LoginService;

@RestController
@RequestMapping("/api/v1")
public class LoginController<T> {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public T doLogin( @RequestBody LoginRequest request ) {
		
		Object login = this.loginService.login(request);
		return (T) login;
	}
	
}
