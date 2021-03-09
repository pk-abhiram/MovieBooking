package com.capg.movie.capg.movie.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class TheatreAlreadyExistsException extends RuntimeException{

	public TheatreAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TheatreAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
