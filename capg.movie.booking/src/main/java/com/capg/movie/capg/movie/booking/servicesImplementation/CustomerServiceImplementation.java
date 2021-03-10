package com.capg.movie.capg.movie.booking.servicesImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.movie.capg.movie.booking.entities.Customer;
import com.capg.movie.capg.movie.booking.entities.TicketBooking;
import com.capg.movie.capg.movie.booking.repository.CustomerRepostitory;
import com.capg.movie.capg.movie.booking.service.CustomerService;

@Service
public class CustomerServiceImplementation implements CustomerService{
	@Autowired
	CustomerRepostitory customerRepostitory;

	@Autowired
	TicketBookingServiceImplementation ticketBookingServiceImplementation;

	Optional<Customer> customers = null;

	//Adding a customer
		public Customer addCustomer(Customer customer) {
			Optional<Customer> findCustomer=customerRepostitory.findById(customer.getCustomerId());
			if(findCustomer.isEmpty()) {
				customerRepostitory.save(customer );
				return customer; 
			}
			return null;

		}
	
		//Update
		@Transactional
		public Customer updateCustomer(Customer customer) {
			Optional<Customer> getCustomer = customerRepostitory.findById(customer.getCustomerId());
			Customer updatecust = null;
			if(getCustomer.isPresent()) {
				updatecust = getCustomer.get();
				if(customer.getCustomerName()!=null) {
					updatecust.setCustomerName(customer.getCustomerName());
				}
				if(customer.getAddress()!=null) {
					updatecust.setAddress(customer.getAddress());
				}
				if(customer.getEmail()!=null) {
					updatecust.setEmail(customer.getEmail());
				}
				if(customer.getMobileNo()!=null) {
					updatecust.setMobileNo(customer.getMobileNo());
				}
				if(customer.getPassword()!=null) {
					updatecust.setPassword(customer.getPassword());
				}
			}
			return updatecust;
		}
		
		//Deleting a customer
		public Customer deleteCustomer(int id){
			Optional<Customer> customer = customerRepostitory.findById(id);
			Customer c = null;
			if(customer.isPresent()) {
				c = customer.get();
				customerRepostitory.delete(c);
				return c;
			}
			return null;
		}	
		
		//Viewing customer by id
		public Customer viewCustomerById(int id) {
		
			Optional<Customer> customer = customerRepostitory.findById(id);
			Customer c = null;
			if(customer.isPresent()) {
				c = customer.get();
				return c;
			}
			return c;
		}
		
		//Viewing all customers
		public List<Customer> viewAllCustomer() {
			List<Customer> customers = customerRepostitory.findAll();
			return customers;
		}

		
	public Customer addCustomerAndTicket(int id,List<TicketBooking> ticket) {
		Optional<Customer> customer = customerRepostitory.findById(id);
		Customer cust = customer.get(); 
		if(customer.isPresent()) {
			for(TicketBooking t:ticket) {
				cust.getTicketBooking().add(t);

			}
			customerRepostitory.save(cust);
		}
		return cust;
	}


	public void deleteCustomerandTicket(int id,int ticketId) {
		Optional<Customer> customer = customerRepostitory.findById(id);
		if(customer.isPresent()) {
			Customer cust = customer.get();
			if(ticketBookingServiceImplementation.showAllBookingList(ticketId)!=null && cust.getTicketBooking().get(cust.getTicketBooking().indexOf(ticketBookingServiceImplementation.showAllBookingList(ticketId)))!=null) {
				cust.getTicketBooking().remove(ticketBookingServiceImplementation.showAllBookingList(ticketId));
				customerRepostitory.save(cust);
			}
		}

	}

	
	

	

	







}