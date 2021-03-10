package com.capg.movie.capg.movie.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.capg.movie.capg.movie.booking.entities.Customer;
import com.capg.movie.capg.movie.booking.entities.TicketBooking;
import com.capg.movie.capg.movie.booking.service.CustomerService;
import com.capg.movie.capg.movie.booking.servicesImplementation.TicketBookingServiceImplementation;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;


	@Autowired
	TicketBookingServiceImplementation ticketBookingServiceImplementation;

//	@Test
	void testAddCustomer() {

		try {
			Customer cust = new Customer("Sam", "Delhi", "9884736323", "Sam@gmail.com", "hsgkdjddn");
			customerService.addCustomer(cust);
			TicketBooking booking = new TicketBooking(1, LocalDate.now(), 202101, "Yono Pay", "Success", 0, null);
			List<TicketBooking> t = new ArrayList<TicketBooking>();
			t.add(booking);
			customerService.addCustandt(2, t);

		}
		catch(NoResultException e) {
			e.printStackTrace();
		}
	}

	//	@Test
	void testDeleteCustomer() {

		try {
			customerService.deleteCustomerandTicket(1, 1);
		}
		catch(NoResultException e) {
			e.printStackTrace();
		}
	}

//	@Test
	void testViewCustomers() {
		int id=1;
		Customer cust = customerService.viewCustomerById(id);
		System.out.println(cust);
	}

	//	@Test
	void testViewAllCustomers() {
		List<Customer> customers = customerService.viewAllCustomer();
		System.out.println(customers);
	}

	//	@Test
	void testRemoveCustomer() {
		int id=1;
		customerService.deleteCustomer(id);
	}

	//	@Test
	void testUpdateCustomer() {
		int id=1;
		Customer cust = new Customer(id,null,null,null,null,"dontknowpassword");
		customerService.updateCustomer(cust);
	}
}
