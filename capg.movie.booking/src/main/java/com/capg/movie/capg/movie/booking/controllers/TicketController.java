package com.capg.movie.capg.movie.booking.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capg.movie.capg.movie.booking.entities.Ticket;
import com.capg.movie.capg.movie.booking.exceptions.TicketAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.TicketNotExistsException;
import com.capg.movie.capg.movie.booking.repository.TicketRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.TicketServiceImplementation;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketServiceImplementation ticketServiceImplementation; 
	
	@PostMapping("/")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
		ResponseEntity<Ticket> re;
		Optional<Ticket> findTicket = ticketRepository.findById(ticket.getTicketId());
		if(findTicket.isEmpty()) {
			ticketServiceImplementation.addTicket(ticket);
		re = new ResponseEntity<>(ticket,HttpStatus.CREATED);
		}
		else {
			throw new TicketAlreadyExistsException("Ticket Already Exist with ID : " + ticket.getTicketId());
		}
		return re;
	}
	
	@RequestMapping(value = "/tickets", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateTicket(@RequestBody Ticket ticket){
		ResponseEntity<Void> re;
		Optional<Ticket> findTicket = ticketRepository.findById(ticket.getTicketId());
		if(findTicket.isPresent()) {
			ticketServiceImplementation.updateTicket(ticket);
			re =  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			throw new TicketNotExistsException("Ticket Not Exist with ID : " + ticket.getTicketId());
		}
		return re;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<Void> cancelTicket(@RequestBody Ticket ticket){
		ResponseEntity<Void> re;
		Optional<Ticket> findTicket = ticketRepository.findById(ticket.getTicketId());
		if(findTicket.isPresent()) {
			ticketServiceImplementation.cancelTicket(ticket);
			re =  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			throw new TicketNotExistsException("Ticket Not Exist with ID : " + ticket.getTicketId());
		}
		return re;
	}
	
	@GetMapping("/{ticketId}")
	public ResponseEntity<Ticket>  findByticketId(@PathVariable("ticketId") int ticketId){
		ResponseEntity<Ticket> re;
		Optional<Ticket> findTicket = ticketRepository.findById(ticketId);
		if(findTicket.isPresent()) {
			Ticket ticket=ticketServiceImplementation.viewTicket(ticketId);
			re =  new ResponseEntity<Ticket>(ticket,HttpStatus.NO_CONTENT);
		}
		else {
			throw new TicketNotExistsException("Ticket Not Exist with ID : " + ticketId);
		}
		return re;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Ticket>> findAllTickets(){
		ResponseEntity<List<Ticket>> re;
		List<Ticket> tickets = ticketServiceImplementation.viewTicketList();
		if(tickets.isEmpty()) {
			throw new TicketNotExistsException("No tickets in DB");
			
		}
		else {
			re =  new ResponseEntity<List<Ticket>>(tickets,HttpStatus.NO_CONTENT);
		}
		return re;
	}

}
