package com.capg.movie.capg.movie.booking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.capg.movie.capg.movie.booking.entities.Seat;

@Component
public interface SeatService {

	public Seat addSeat(Seat seat);
	@Transactional
	public Seat updateSeat(Seat seat);
	public Seat removeSeat(Seat seat);
	public Seat viewSeat(int SeatId);
	public List<Seat> viewSeatList();
	public Seat removeSeat(int seatID);
	public List<Seat> viewSeatByType(String type);
}