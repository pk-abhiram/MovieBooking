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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capg.movie.capg.movie.booking.entities.Customer;
import com.capg.movie.capg.movie.booking.exceptions.CustomerAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.CustomerNotExistsException;
import com.capg.movie.capg.movie.booking.repository.CustomerRepostitory;
import com.capg.movie.capg.movie.booking.service.CustomerService;
import com.capg.movie.capg.movie.booking.servicesImplementation.CustomerServiceImplementation;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {

	@Autowired
	CustomerRepostitory customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerServiceImplementation customerServiceImplemntation;

	Optional<Customer> customers = null;


	//Adding a customer
	@PostMapping("/")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer>re;
		Optional<Customer>findCustomer=customerRepository.findById(customer.getCustomerId());
		if(findCustomer.isEmpty()) {
			customerServiceImplemntation.addCustomer(customer);
			re=new ResponseEntity<>(customer, HttpStatus.CREATED);
			
		}
		else {
			throw new CustomerAlreadyExistsException("Customer with id:"+customer.getCustomerId()+" already exists");
		}
		return re;
	}

	//Update customer 
		@PutMapping("/")
		public ResponseEntity<Void> updateCustomer(@RequestBody Customer customer) {
			ResponseEntity<Void>re;
			Optional<Customer>findCustomer=customerRepository.findById(customer.getCustomerId());
			if(findCustomer.isEmpty()) {
				throw new CustomerNotExistsException("Customer with id:"+customer.getCustomerId()+" does not exists");
			}
			else {
				customerServiceImplemntation.updateCustomer(customer);
				re=new ResponseEntity<>(HttpStatus.OK);
			}
			return re;
		}
		
		
		//Deleting a customer by id
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
			ResponseEntity<Void>re;
			Optional<Customer>findCustomer=customerRepository.findById(id);
			if(findCustomer.isEmpty()) {
				throw new CustomerNotExistsException("Customer with id:"+id+" does not exists");
			}
			else {
				customerServiceImplemntation.deleteCustomer(id);
				re=new ResponseEntity<>(HttpStatus.OK);
			}
			return re;
		}
	
		//View customer by id
		@GetMapping("/{id}")
		public ResponseEntity<Customer> viewCustomerById(@PathVariable("id") int id) {
			ResponseEntity<Customer>  re;
			Optional<Customer>findCustomer=customerRepository.findById(id);
			if(findCustomer.isEmpty()) {
				throw new CustomerNotExistsException("Customer with id:"+id+" does not exists");
			}
			else {
				Customer customer = customerServiceImplemntation.viewCustomerById(id);
				re=new ResponseEntity<>(customer,HttpStatus.OK);
			}
			return re;
		}	
		
		//View all Customers
		@GetMapping("/")

		public ResponseEntity<List<Customer>> viewAllCustomer() {
			ResponseEntity<List<Customer>> re;
			List<Customer>customers=customerServiceImplemntation.viewAllCustomer();
			if(customers.isEmpty()) {
				throw new CustomerNotExistsException("No Customers in DB");
			}
			else {
				re=new ResponseEntity<>(customers,HttpStatus.OK);
			}
		return re;
		}	
		
		
		
	//Adding a customer with ticket booking
	@PostMapping("/customers/addCustomerAndTicket")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Customer> addCustomerAndTicket(@RequestBody Customer c) {
		ResponseEntity<Customer>  re;
		Optional<Customer>findCustomer=customerRepository.findById(c.getCustomerId());
		if(findCustomer.isPresent()) {
			Customer customer=customerServiceImplemntation.addCustandt(c.getCustomerId(), c.getTicketBooking());
			re=new ResponseEntity<>(customer,HttpStatus.OK);
		}
		else {
			throw new CustomerNotExistsException("Customer with id:"+c.getCustomerId()+" not exists");
		}
		return re;
	}



	

	

	


	
	
}
