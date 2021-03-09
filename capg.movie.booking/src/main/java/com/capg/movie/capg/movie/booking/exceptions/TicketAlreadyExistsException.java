package com.capg.movie.capg.movie.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class TicketAlreadyExistsException extends RuntimeException{

	public TicketAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
