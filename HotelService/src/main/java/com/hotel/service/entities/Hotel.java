package com.hotel.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "hotel")
@Entity
public class Hotel {

	@Id
	private String hotelId;
	private String hotelName;
	private String hotelLocation;
	private String about ;
	
}
