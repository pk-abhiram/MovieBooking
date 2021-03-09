package com.capg.movie.capg.movie.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.movie.capg.movie.booking.entities.Ticket;
import com.capg.movie.capg.movie.booking.servicesImplementation.TicketServiceImplementation;


@SpringBootTest
class TicketServiceTest {

	@Autowired
	TicketServiceImplementation ticketServiceImplementaion;

	//	@Test
	void testAddTicket() {
		Ticket ticket = new Ticket(3, null, null, false);
		ticketServiceImplementaion.addTicket(ticket);
		
	}

	//	@Test
	void testUpdateTicket() {
		Ticket ticket = new Ticket(10,null,null,false);
		ticketServiceImplementaion.updateTicket(ticket);
		
	}

	//	@Test
	void testViewTicket() {
		int id = 3;
		Ticket ticket = ticketServiceImplementaion.viewTicket(id);
		System.out.println(ticket);
	}

	//	@Test 
	void testCancelTicketById(){
		int id = 4;
		ticketServiceImplementaion.cancelTicket(id);
		
	}
//	@Test 
	void testCancelTicket(){
		Ticket ticket = new Ticket(2,10,null,null,false);
		ticketServiceImplementaion.cancelTicket(ticket);
		
	}
	
//	@Test
	void testViewAllTickets() {
		ticketServiceImplementaion.viewTicketList();
	}

}
