package com.auth.service.payloads;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	private String userId;
	private String name;
	private String email;
	private String password;
	private String about;
	
//	private List<Rating> ratings = new ArrayList<>();
	
}
