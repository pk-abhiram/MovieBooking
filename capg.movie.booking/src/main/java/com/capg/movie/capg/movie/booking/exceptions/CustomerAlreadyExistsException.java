package com.capg.movie.capg.movie.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerAlreadyExistsException extends RuntimeException{

	public CustomerAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
