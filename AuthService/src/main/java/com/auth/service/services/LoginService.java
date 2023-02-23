package com.auth.service.services;

import com.auth.service.payloads.LoginRequest;

public interface LoginService<T> {

	T login(LoginRequest request);
	
}
