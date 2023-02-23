package com.rating.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.service.entities.Rating;
import com.rating.service.payloads.ApiResponse;
import com.rating.service.services.RatingService;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	// CREATE
	@PostMapping("/create")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.ratingService.createRating(rating));
	}

	// GET ALL
	@GetMapping("/get-all")
	public ResponseEntity<List<Rating>> getAllRatings() {
		return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getAllRatings());
	}

	// GET BY ID
	@GetMapping("/id/{ratingId}")
	public ResponseEntity<Rating> getRatingById(@PathVariable String ratingId) {
		return ResponseEntity.ok(this.ratingService.getRatingById(ratingId));
	}

	// GET RATING BY USERID
	@GetMapping("/user-id/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(this.ratingService.getAllRatingsByUserId(userId));
	}

	// GET RATING BY HOTEL ID
	@GetMapping("/hotel-id/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
		return ResponseEntity.ok(this.ratingService.getAllRatingsByHotelId(hotelId));
	}
	
	// UPDATE RATING
	@PutMapping("/update/id/{ratingId}")
	public ResponseEntity<Rating> updateRating(@RequestBody Rating rating, @PathVariable String ratingId) {
		return ResponseEntity.ok(this.ratingService.updateRating(rating, ratingId));
	}

	// DELETE RATING
	@DeleteMapping("/delete/id/{ratingId}")
	public ResponseEntity<ApiResponse> deleteRating(@PathVariable String ratingId) {
		this.ratingService.deleteRating(ratingId);
		return ResponseEntity.ok(new ApiResponse("Rating Deleted Successfully !!", true, HttpStatus.OK));
	}
}