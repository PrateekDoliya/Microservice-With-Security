package com.hotel.service.controller;

import java.util.List;

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

import com.hotel.service.entities.Hotel;
import com.hotel.service.payloads.ApiResponse;
import com.hotel.service.services.HotelService;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	// CREATE
	@PostMapping("/create-hotel/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.createHotel(hotel));
	}

	// UPDATE
	@PutMapping("/update-hotel/id/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId) {
		return ResponseEntity.ok(this.hotelService.updateHotel(hotelId, hotel));
	}

	// GET ALL
	@GetMapping("/get-all/")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		return ResponseEntity.ok(this.hotelService.getAllHotels());
	}

	// GET BY ID
	@GetMapping("/get/id/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
		return ResponseEntity.ok(this.hotelService.getHotelById(hotelId));
	}

	// DELETE
	@DeleteMapping("/delete/id/{hotelId}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelId) {
		this.hotelService.deleteById(hotelId);
		return ResponseEntity.ok(new ApiResponse("Hotel Deleted Successfully !!", true, HttpStatus.OK));
	}

}
