package com.capg.movie.capg.movie.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.movie.capg.movie.booking.entities.Admin;

@Repository

public interface AdminRepository extends JpaRepository<Admin,Integer> {
	//Service methods declaration
//	public Admin findByadminName(String adminName);
//	public Optional<Admin> findByadminId(int adminId);
	public Admin findByAdminNameAndAdminContact(String adminName, String adminContact);
}