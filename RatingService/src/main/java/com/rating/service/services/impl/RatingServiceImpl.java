package com.rating.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.entities.Rating;
import com.rating.service.exception.ResourceNotFoundException;
import com.rating.service.repositories.RatingRepository;
import com.rating.service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating createRating(Rating rating) {
		rating.setRatingId(UUID.randomUUID().toString());
		return this.ratingRepository.save(rating);
	}

	@Override
	public Rating updateRating(Rating rating, String ratingId) {
		Rating rating2 = this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating", "rating_id", ratingId));
		rating2.setRating(rating.getRating());
		rating2.setFeedback(rating.getFeedback());
		rating2.setHotelId(rating.getHotelId());
		rating2.setUserId(rating.getUserId());
		return this.ratingRepository.save(rating2);
	}

	@Override
	public List<Rating> getAllRatings() {
		return this.ratingRepository.findAll();
	}

	@Override
	public Rating getRatingById(String ratingId) {
		return this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating", "rating_id", ratingId));
	}

	@Override
	public List<Rating> getAllRatingsByUserId(String userId) {
		return this.ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingsByHotelId(String hotelId) {
		return this.ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public Rating deleteRating(String ratingId) {
		Rating rating = this.ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating", "rating_id", ratingId));
		this.ratingRepository.delete(rating);
		return rating;
	}

}
