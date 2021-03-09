package com.capg.movie.capg.movie.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.movie.capg.movie.booking.entities.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Integer>{

	public Ticket findByTicketId(int ticketId);
}