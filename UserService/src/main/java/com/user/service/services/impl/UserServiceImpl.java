package com.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.security.library.payloads.SharedData;
import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;;

	@Override
	public User createUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		return this.userRepository.save(user);
	}

	@Override
	public User updateUser(User user, String userId) {
		User user2 = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setAbout(user.getAbout());
		user2.setPassword(user.getPassword());
		return this.userRepository.save(user2);
	}

	@Override
	public User deleteUser(String userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		this.userRepository.delete(user);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User getUserById(String userId) {
		
		
		String token = SharedData.getSharedDataMap().get("jwtToken");
		System.out.println("TOKEN : " + token);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + token.substring(7));
	    HttpEntity entity = new HttpEntity(headers);
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

//		ResponseEntity<Rating[]> userWithRating = this.restTemplate
//				.getForEntity("lb://RATING-SERVICE/api/v1/ratings/user-id/" + userId, Rating[].class);
//		Rating[] userRatingArray = userWithRating.getBody();
		
		ResponseEntity<Rating[]> userWithRating = this.restTemplate.exchange("lb://RATING-SERVICE/api/v1/ratings/user-id/"+userId, HttpMethod.GET, entity, Rating[].class);
		Rating[] userRatingArray = userWithRating.getBody();
		
		List<Rating> userRatingList = Arrays.asList(userRatingArray);
		List<Rating> userRatingHotelList = userRatingList.stream().map((rating) -> {
			try {
				
				ResponseEntity<Hotel> responseEntity = this.restTemplate.exchange("lb://HOTEL-SERVICE/api/v1/hotels/get/id/"+rating.getHotelId(),HttpMethod.GET, entity, Hotel.class);
				rating.setHotel(responseEntity.getBody());
//				ResponseEntity<Hotel> hotelEntity = this.restTemplate.getForEntity("lb://HOTEL-SERVICE/api/v1/hotels/get/id/"+rating.getHotelId(), Hotel.class);
//				rating.setHotel(hotelEntity.getBody());
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new ResourceNotFoundException(e.getMessage());
			}
			
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(userRatingHotelList);
		return user;
	}

}
