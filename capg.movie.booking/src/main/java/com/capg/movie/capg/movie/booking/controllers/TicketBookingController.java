package com.capg.movie.capg.movie.booking.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capg.movie.capg.movie.booking.entities.TicketBooking;
import com.capg.movie.capg.movie.booking.exceptions.BookingAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.BookingNotExistsException;
import com.capg.movie.capg.movie.booking.repository.TicketBookingRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.TicketBookingServiceImplementation;

@RestController
@RequestMapping(value = "Booking")
public class TicketBookingController {


	@Autowired
	TicketBookingRepository ticketBookingRepository;
	
	@Autowired
	TicketBookingServiceImplementation ticketBookingServiceImplementation; 
	
	@PostMapping("/")
	public ResponseEntity<TicketBooking> addBooking(@RequestBody TicketBooking booking){
		ResponseEntity<TicketBooking> re;
				
		Optional<TicketBooking> ticket = ticketBookingRepository.findById(booking.getTicketId());
		if(ticket.isEmpty()) {
		ticketBookingServiceImplementation.addBooking(booking);
		re = new ResponseEntity<>(booking, HttpStatus.CREATED);
		}
		else {
			throw new BookingAlreadyExistsException("Booking Already Exist with ID : " + booking.getTicketId());
		}
		return re;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> updateTicketBooking(@RequestBody TicketBooking booking){
		ResponseEntity<Void> re;
		Optional<TicketBooking> ticket = ticketBookingRepository.findById(booking.getTicketId());
		if(ticket.isPresent()) {
			ticketBookingServiceImplementation.updateBooking(booking);
			re = new ResponseEntity<>( HttpStatus.CREATED);
			}
			else {
				throw new BookingNotExistsException("Booking Not Exist with ID : " + booking.getTicketId());
			}
			return re;
	}
	
	@DeleteMapping("/")
	public ResponseEntity<Void> cancelBooking(@RequestBody TicketBooking ticketBooking) {
		ResponseEntity<Void> re;
		Optional<TicketBooking> findBooking = ticketBookingRepository.findById(ticketBooking.getTicketId());
		
		if(findBooking.isPresent()) {
			TicketBooking ticketBookingRemove = null;
			ticketBookingRemove = findBooking.get();
			ticketBookingServiceImplementation.cancelBooking(ticketBooking);
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		else {
			throw new BookingNotExistsException("Booking Not Exist with ID : " + ticketBooking.getTicketId());
		}
		return re;
	}
	
	@GetMapping("/D/{date}")
//	LocalDate date = LocalDate.parse("2021-03-10");
	public ResponseEntity<List<TicketBooking>> findByLocalDate(@PathVariable ("date") LocalDate date) {
		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking>ticketBookings=ticketBookingServiceImplementation.showAllBooking(date);
		if(ticketBookings.isEmpty()) {
			throw new BookingNotExistsException("Bookings Not Exist "+date);
		}
		else {
			re=new ResponseEntity<List<TicketBooking>>(ticketBookings,HttpStatus.OK);
		}
		return re;
	}
	
	@GetMapping("/M/{movieId}")
//	LocalDate date = LocalDate.parse("2021-03-10");
	public ResponseEntity<List<TicketBooking>> findByMovieId(@PathVariable ("movieId") int movieId) {
		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking>ticketBookings=ticketBookingServiceImplementation.showAllBooking(movieId);
		if(ticketBookings.isEmpty()) {
			throw new BookingNotExistsException("Bookings Not Exist "+movieId);
		}
		else {
			re=new ResponseEntity<List<TicketBooking>>(ticketBookings,HttpStatus.OK);
		}
		return re;
	}
	
	@GetMapping("/S/{showId}")
//	LocalDate date = LocalDate.parse("2021-03-10");
	public ResponseEntity<List<TicketBooking>> findByShowId(@PathVariable ("showId") int showId) {
		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking>ticketBookings=ticketBookingServiceImplementation.showBookingList(showId);
		if(ticketBookings.isEmpty()) {
			throw new BookingNotExistsException("Bookings Not Exist");
		}
		else {
			re=new ResponseEntity<List<TicketBooking>>(ticketBookings,HttpStatus.OK);
		}
		return re;
	}
	
	
	
	
	@GetMapping("/total/{id}")
	public ResponseEntity<Double> findByticketId(@PathVariable("id") int bookingId) {
		ResponseEntity<Double> re = null;
		
		Optional<TicketBooking> ticketBooking = ticketBookingRepository.findById(bookingId);
		
		if(ticketBooking.isPresent()) {
			Double cost=ticketBookingServiceImplementation.calculateTotalCost(bookingId);
			re = new ResponseEntity<>(cost,HttpStatus.OK);
		}
		else {
			throw new BookingNotExistsException("Booking Not Exist with ID : " + bookingId);
		}
		return re;
	}
	
}
	
	


