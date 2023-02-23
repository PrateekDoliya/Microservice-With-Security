package com.user.service.entities;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

	
	private String ratingId;
	private Integer rating;
	private String feedback;
	private String userId;
	private String hotelId;
	
	@Transient
	private Hotel hotel;
	
}
