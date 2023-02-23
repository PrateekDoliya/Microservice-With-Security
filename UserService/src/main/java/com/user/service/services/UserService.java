package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;

public interface UserService {

	// CREATE
	User createUser(User user);
	
	// UPDATE
	User updateUser(User user, String userId);
	
	// DELETE
	User deleteUser(String userId);
	
	// GET ALL
	List<User> getAllUsers();
	
	// GET BY ID
	User getUserById(String userId);
	
}
