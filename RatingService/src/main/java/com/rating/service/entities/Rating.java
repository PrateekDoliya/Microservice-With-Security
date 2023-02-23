package com.rating.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rating")
@Data
public class Rating {

	@Id
	private String ratingId;
	private Integer rating;
	private String feedback;
	private String userId;
	private String hotelId;
}
