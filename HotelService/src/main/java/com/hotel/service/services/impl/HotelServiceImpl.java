package com.hotel.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.service.entities.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repositories.HotelRepository;
import com.hotel.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel createHotel(Hotel hotel) {
		hotel.setHotelId(UUID.randomUUID().toString());
		return this.hotelRepository.save(hotel);
	}

	@Override
	public Hotel updateHotel(String hotelId, Hotel hotel) {
		Hotel hotel2 = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel", "hotel_id", hotelId));
		hotel2.setAbout(hotel.getAbout());
		hotel2.setHotelLocation(hotel.getHotelLocation());
		hotel2.setHotelName(hotel.getHotelName());
		return this.hotelRepository.save(hotel2);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return this.hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel", "hotel_id", hotelId));
		return hotel;
	}

	@Override
	public Hotel deleteById(String hotelId) {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel", "hotel_id", hotelId));
		this.hotelRepository.delete(hotel);
		return hotel;
	}
	
	
	
}
