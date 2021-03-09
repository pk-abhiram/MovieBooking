package com.capg.movie.capg.movie.booking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capg.movie.capg.movie.booking.entities.Customer;
import com.capg.movie.capg.movie.booking.entities.TicketBooking;

@Component
public interface CustomerService {

	@Transactional
	public List<Customer> viewAllCustomer();
	public Customer viewCustomerById(int id);
	public Customer deleteCustomer(int id);
	public Customer updateCustomer(Customer c);
	public Customer addCustandt(int id,List<TicketBooking> ticket);
	public void deleteCustomerandTicket(int id,int ticketId);
	public Customer addCustomer(Customer c);

}
