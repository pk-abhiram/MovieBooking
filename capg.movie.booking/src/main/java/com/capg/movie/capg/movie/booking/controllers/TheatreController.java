package com.capg.movie.capg.movie.booking.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capg.movie.capg.movie.booking.entities.Theatre;
import com.capg.movie.capg.movie.booking.exceptions.TheatreAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.TheatreNotExistsException;
import com.capg.movie.capg.movie.booking.repository.TheatreRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.TheatreServiceImplementation;

@RestController
@RequestMapping(value = "/theatre")
public class TheatreController {
	
	@Autowired
	TheatreServiceImplementation theatreServiceImplementation;
	
	@Autowired
	TheatreRepository theatreRepository;
	
	@RequestMapping(value="/" , method = RequestMethod.POST)
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
		ResponseEntity<Theatre>  re;
		Optional<Theatre> findTheatre = theatreRepository.findById(theatre.getTheatreId());
		if(findTheatre.isEmpty()) {
			theatreServiceImplementation.addTheatre(theatre);
			 re = new ResponseEntity<>(theatre,HttpStatus.CREATED);
		}
		else {
			throw new TheatreAlreadyExistsException("Theatre Already Exist with ID : " +  theatre.getTheatreId());
		}
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> updateTheatre(@RequestBody Theatre theatre) {
		ResponseEntity<Void>  re;
		Optional<Theatre> findTheatre = theatreRepository.findById(theatre.getTheatreId());
		if(findTheatre.isPresent()) {
			theatreServiceImplementation.updateTheatre(theatre);
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			throw new TheatreNotExistsException("Theatre not exist with Id: " +  theatre.getTheatreId());
		}
		return re;
	}
	
	@RequestMapping(value="/{theatre}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeTheatre(@PathVariable("theatre") Theatre theatre) {
		Theatre removeTheatre=theatreServiceImplementation.removeTheatre(theatre);
		ResponseEntity<Void>  re;
		if(removeTheatre==null) {
			throw new TheatreNotExistsException("Theatre not exist with Id: " +  theatre.getTheatreId());
		}
		else {
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/id/{theatreId}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeTheatreById(@PathVariable("theatreId") int theatreId) {
		Theatre removeTheatre=theatreServiceImplementation.removeTheatre(theatreId);
		ResponseEntity<Void>  re;
		if(removeTheatre==null) {
			throw new TheatreNotExistsException("Theatre not exist with Id: " +  theatreId);
		}
		else {
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/{theatreId}" , method = RequestMethod.GET)
	public ResponseEntity<Theatre> viewTheatreById(@PathVariable("theatreId") int theatreId){
		ResponseEntity<Theatre>  re;
		Theatre findTheatre=theatreServiceImplementation.viewTheatreById(theatreId);
		if(findTheatre==null) {
			throw new TheatreNotExistsException("Theatre not exist with Id: " + theatreId);
		}
		else {
			re = new ResponseEntity<>(findTheatre,HttpStatus.FOUND);
		}
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<List<Theatre>> viewTheatreListAll(){
		ResponseEntity<List<Theatre>> re;
		List<Theatre>theatres=theatreServiceImplementation.viewTheatreList();
		if(theatres.isEmpty()) {
			throw new TheatreNotExistsException("No Theatre currently Exists");
		}
		else {
			re = new ResponseEntity<>(theatres,HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/city/{city}" , method = RequestMethod.GET)
	public ResponseEntity<List<Theatre>> viewTheatreListByCity(@PathVariable("city") String city){
		ResponseEntity<List<Theatre>>  re;
		List<Theatre> findTheatre=theatreServiceImplementation.viewTheatreListByCity(city);
		if(findTheatre.isEmpty()) {
			throw new TheatreNotExistsException("Theatre not exist in City: " + city);
		}
		else {
			re = new ResponseEntity<>(findTheatre,HttpStatus.FOUND);
		}
		return re;
	}
	
}
