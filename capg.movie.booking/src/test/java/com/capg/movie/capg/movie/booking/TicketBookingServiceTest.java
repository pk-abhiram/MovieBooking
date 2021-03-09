package com.capg.movie.capg.movie.booking;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capg.movie.capg.movie.booking.entities.Ticket;
import com.capg.movie.capg.movie.booking.entities.TicketBooking;
import com.capg.movie.capg.movie.booking.servicesImplementation.TicketBookingServiceImplementation;

class TicketBookingServiceTest {

	@Autowired
	TicketBookingServiceImplementation bookingServiceImplementation;
	
	LocalDate bookingDate;
	
//	@Test
	void testAddBooking() {
//		Seat seat = new Seat("L10, L11, L12", "Luxary", 900.00);
		Ticket ticket = new Ticket(3, null, null, false);
		TicketBooking booking = new TicketBooking(2, LocalDate.now(), 202101, "Yono Pay", 
				"Success", 900.0, ticket);
		bookingServiceImplementation.addBooking(booking);
	}

//	@Test
	void testShowAllBooking() {
		LocalDate date = LocalDate.parse("2021-01-01");
		List<TicketBooking> ticketBooking = bookingServiceImplementation.showAllBooking(date);
		System.out.println(ticketBooking);
	}
	
//	@Test
	void testUpdateBooking() {
		TicketBooking booking = new TicketBooking(7, 7, LocalDate.now(), 321002, "UPI", "Completed", 440.04, null);
		bookingServiceImplementation.updateBooking(booking);
		System.out.println("Check Postgre");
	}
	
//	@Test 
	void testCancelBooking() {
		TicketBooking booking = new TicketBooking(8, 7, LocalDate.now(), 321002, "UPI", "Completed", 440.04, null);
		TicketBooking ticketBooking = bookingServiceImplementation.cancelBooking(booking);
		System.out.println("Check Postgre");
	}

//	@Test
	void testShowAllBookingByMovieId() {
		int movieId=3;
		List<TicketBooking> ticketBooking = bookingServiceImplementation.showAllBooking(movieId);
		System.out.println(ticketBooking);
	}
	
//	@Test
	void testShowAllBookingByShowId() {
		int showId=5;
		List<TicketBooking> ticketBooking = bookingServiceImplementation.showAllBooking(showId);
		System.out.println(ticketBooking);
	}
	
//	@Test
	void testCalculateTotalCost() {
		int bookingId=1;
		double cost = bookingServiceImplementation.calculateTotalCost(bookingId);
		System.out.println(cost);
	}
}
