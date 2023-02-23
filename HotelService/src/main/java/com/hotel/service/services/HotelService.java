package com.hotel.service.services;

import java.util.List;

import com.hotel.service.entities.Hotel;

public interface HotelService {

	// CREATE
	Hotel createHotel(Hotel hotel);
	
	// UPDATE
	Hotel updateHotel(String hotelId, Hotel hotel);
	
	// GET ALL
	List<Hotel> getAllHotels();
	
	// GET BY Id
	Hotel getHotelById(String hotelId);
	
	// DELETE
	Hotel deleteById(String hotelId);
}
