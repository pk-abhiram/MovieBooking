package com.capg.movie.capg.movie.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.movie.capg.movie.booking.entities.Seat;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{
	
	public Seat findBySeatNumber(String seatNumber);
	
	public Seat findBySeatId(int seatId);
	public List<Seat>findByType(String type);

}