package com.auth.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auth.service.payloads.User;

public interface LoginRepository extends JpaRepository<User, String> {

	@Query(nativeQuery = true, value = "select * from user u where u.email = :email")
	User findByEmail(String email);
	
}
