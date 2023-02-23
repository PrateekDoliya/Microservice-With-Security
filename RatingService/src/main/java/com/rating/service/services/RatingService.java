package com.rating.service.services;

import java.util.List;

import com.rating.service.entities.Rating;

public interface RatingService {

	// CREATE
	Rating createRating(Rating rating);

	// UPDATE
	Rating updateRating(Rating rating, String ratingId);
	
	//GET ALL
	List<Rating> getAllRatings();
	
	// GET RATING  BY RATING ID
	Rating getRatingById(String ratingId);
	
	// GET RATING BY USER
	List<Rating> getAllRatingsByUserId(String userId);
	
	// GET RATING BY HOTEL
	List<Rating> getAllRatingsByHotelId(String hotelId);
	
	// DELETE
	Rating deleteRating(String ratingId);
}
