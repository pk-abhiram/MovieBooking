package com.capg.movie.capg.movie.booking.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capg.movie.capg.movie.booking.entities.Seat;
import com.capg.movie.capg.movie.booking.exceptions.SeatAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.SeatNotExistsException;
import com.capg.movie.capg.movie.booking.repository.SeatRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.SeatServiceImplementation;

@RestController
@RequestMapping(value = "/seat")
public class SeatController {
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	SeatServiceImplementation seatServiceImplementation;
	
	@PostMapping("/")
	public ResponseEntity<Seat> addSeat(@RequestBody Seat seat){
		ResponseEntity<Seat> re;
		Optional<Seat> findSeat = seatRepository.findById(seat.getSeatId());
		if(findSeat.isEmpty()) {
		seatServiceImplementation.addSeat(seat);
		re = new ResponseEntity<>(seat,HttpStatus.CREATED);
		}
		else {
			throw new SeatAlreadyExistsException("Seat Already Exist with ID : " +  seat.getSeatId());
		}
		return re;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSeat(@RequestBody Seat seat){
		ResponseEntity<Void> re;
		Optional<Seat> findSeat = seatRepository.findById(seat.getSeatId());
		if(findSeat.isPresent()) {
			seatServiceImplementation.updateSeat(seat);
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			throw new SeatNotExistsException("Seat Don't Exist with ID : " +  seat.getSeatId());
		}
		return re;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSeat(@PathVariable("id") int seatId) {
		ResponseEntity<Void> re;
		Optional<Seat> seat = seatRepository.findById(seatId);

		if(seat.isPresent()) {
			seatServiceImplementation.removeSeat(seatId);
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			throw new SeatNotExistsException("Seat Don't Exist with ID : " +  seatId);
		}
		return re;
	}
	
	@GetMapping("/{seatId}")
	public ResponseEntity<Seat> findByticketId(@PathVariable int seatId){
		ResponseEntity<Seat> re;
		Optional<Seat> findSeat = seatRepository.findById(seatId);
		if(findSeat.isPresent()) {
			Seat seat=seatServiceImplementation.viewSeat(seatId);
			re = new ResponseEntity<Seat>(seat,HttpStatus.OK);
		}
		else {
			throw new SeatNotExistsException("Seat Don't Exist with ID : " +  seatId);
		}
		return re;
	}
	
	@GetMapping("/allSeats")
	public ResponseEntity<List<Seat>> viewAllSeats(){
		ResponseEntity<List<Seat>> re;
		List<Seat>seats=seatServiceImplementation.viewSeatList();
		if(seats.isEmpty()) {
			throw new SeatNotExistsException("No Seat Exist currently in DB : ");
		}
		else {
			re = new ResponseEntity<>(seats,HttpStatus.OK);
		}
		return re;
	}	
	
	
	
	@GetMapping("/")
	public List<Seat> findByseatNumber(){
		System.out.println("Method mapped to Http....");
		return seatRepository.findAll();
	}
	
	@DeleteMapping("/Seat/{seat}")
	public ResponseEntity<Void> deleteSeat(@PathVariable("seat") Seat seat) {
		ResponseEntity<Void> re;
		Optional<Seat> findSeat = seatRepository.findById(seat.getSeatId());

		if(findSeat.isPresent()) {
			seatServiceImplementation.removeSeat(seat.getSeatId());
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			throw new SeatNotExistsException("Seat Don't Exist with ID : " +  seat.getSeatId());
		}
		return re;
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Seat>> viewAllSeatsByType(@PathVariable("type") String type){
		ResponseEntity<List<Seat>> re;
		List<Seat>seats=seatServiceImplementation.viewSeatByType(type);
		if(seats.isEmpty()) {
			throw new SeatNotExistsException("No Seat Exist currently with seat type: "+type);
		}
		else {
			re = new ResponseEntity<>(seats,HttpStatus.OK);
		}
		return re;
	}	
	
	
	
}