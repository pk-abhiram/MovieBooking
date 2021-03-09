package com.capg.movie.capg.movie.booking.servicesImplementation;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.movie.capg.movie.booking.entities.Ticket;
import com.capg.movie.capg.movie.booking.repository.TicketRepository;
import com.capg.movie.capg.movie.booking.service.TicketService;

@Service
public class TicketServiceImplementation implements TicketService{

	@Autowired
	TicketRepository ticketRepository;

	public Ticket addTicket(Ticket ticket) {
		Optional<Ticket> findTicket=ticketRepository.findById(ticket.getTicketId());
		if(findTicket.isEmpty()) {
			ticketRepository.save(ticket);
			return ticket;
		}
		return null;
		
	}

	@Transactional
	public Ticket updateTicket(Ticket ticket) {
		Optional<Ticket> getUpdateTicket = ticketRepository.findById(ticket.getTicketId());
		Ticket updateTicket = null;
		if(getUpdateTicket.isPresent()) {
			updateTicket = getUpdateTicket.get();
			if(ticket.getBookingRef()!=null) {
				updateTicket.setBookingRef(ticket.getBookingRef());
			}
			if(ticket.getSeats()!=null) {
				updateTicket.setSeats(ticket.getSeats());
			}
			if(ticket.getNoOfSeats()!=0) {
				updateTicket.setNoOfSeats(ticket.getNoOfSeats());
			}
		}
		return ticket;
	}

	public Ticket cancelTicket(int id) {
		Optional<Ticket> findTicket = ticketRepository.findById(id);
		Ticket ticket=null;
		if(findTicket.isPresent()) {
			ticket=findTicket.get();
			ticketRepository.delete(ticket);
		}
		return ticket;

	}

	public Ticket viewTicket(int ticketId) {
		Optional<Ticket> findTicket = ticketRepository.findById(ticketId);
		if(findTicket.isPresent()) {
			return findTicket.get();
		}
		return null;

	}

	public List<Ticket> viewTicketList(){
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets;
	}

	@Override
	public Ticket cancelTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		Optional<Ticket> findTicket = ticketRepository.findById(ticket.getTicketId());
		Ticket cancelTicket=null;
		if(findTicket.isPresent()) {
			cancelTicket=findTicket.get();
			ticketRepository.delete(cancelTicket);
		}
		return cancelTicket;
	}

}