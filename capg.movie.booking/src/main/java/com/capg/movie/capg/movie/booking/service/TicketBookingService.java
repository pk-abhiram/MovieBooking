package com.capg.movie.capg.movie.booking.service;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.capg.movie.capg.movie.booking.entities.TicketBooking;


@Component
public interface TicketBookingService {
	public TicketBooking addBooking (TicketBooking booking);
	@Transactional
	public TicketBooking updateBooking(TicketBooking booking);
	public TicketBooking cancelBooking(TicketBooking booking);
	public List<TicketBooking> showAllBooking(int movieId);
	public List<TicketBooking> showAllBooking(LocalDate date);
	public List<TicketBooking> showBookingList(int showId);
	public double calculateTotalCost(int bookingid);
}
