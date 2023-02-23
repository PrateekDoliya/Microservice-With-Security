package com.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entities.User;
import com.user.service.payloads.ApiResponse;
import com.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	// Create 
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
	}
	
	// get all users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// get user by id
	
	int retryCount = 0;
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelRetry", fallbackMethod ="ratingHotelFallback" )
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		
		retryCount++;
		logger.info("Retry Count: {}", retryCount);
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	// creating fall back method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception) {
		logger.info("Fallback iss executed because service is down");
		System.out.println(exception.getMessage());
		
		User user = User.builder()
						.userId("123")
						.name("Dummy")
						.email("dummy@gmaail.com")
						.about("This user is dummy created because some service is down")
						.build();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// update
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId) {
		return ResponseEntity.ok(this.userService.updateUser(user, userId));
	}
	
	// delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
		this.userService.deleteUser(userId);
		return ResponseEntity.ok(new ApiResponse("User Deleted Successfully !!", true, HttpStatus.OK));
	}
}
