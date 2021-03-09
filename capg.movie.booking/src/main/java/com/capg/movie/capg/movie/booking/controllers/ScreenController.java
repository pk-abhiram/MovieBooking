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

import com.capg.movie.capg.movie.booking.entities.Screen;
import com.capg.movie.capg.movie.booking.entities.Theatre;
import com.capg.movie.capg.movie.booking.exceptions.ScreenAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.ScreenNotExistsException;
import com.capg.movie.capg.movie.booking.exceptions.TheatreNotExistsException;
import com.capg.movie.capg.movie.booking.repository.ScreenRepository;
import com.capg.movie.capg.movie.booking.repository.TheatreRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.ScreenServiceImplementation;

@RestController
@RequestMapping(value = "/screen")
public class ScreenController {

	@Autowired
	ScreenServiceImplementation screenServiceImplementation;
	
	@Autowired
	ScreenRepository screenRepository;
	
	@Autowired
	TheatreRepository theatreRepository; 
	
	@RequestMapping(value="/" , method = RequestMethod.POST)
	public ResponseEntity<Void> addScreen(@RequestBody Screen screen) {
		ResponseEntity<Void>  re;
		Optional<Screen> findScreen = screenRepository.findById(screen.getScreenId());
		if(findScreen.isEmpty()) {
			Optional<Theatre>findTheatre=theatreRepository.findById(screen.getTheatreId());
			if(findTheatre.isPresent()) {
			screenServiceImplementation.addScreen(screen);
			 re = new ResponseEntity<>(HttpStatus.CREATED);
			}
			else {
				throw new TheatreNotExistsException("Theatre not exist with Id: " +  screen.getTheatreId());
			}
		}
		else {
			throw new ScreenAlreadyExistsException("Screen Already Exist with ID : " +  screen.getScreenId());
		}
		return re;
	}

	@RequestMapping(value="/" , method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> updateScreen(@RequestBody Screen screen) {
		ResponseEntity<Void>  re;
		Optional<Screen> findScreen = screenRepository.findById(screen.getScreenId());
		if(findScreen.isPresent()) {
			screenServiceImplementation.updateScreen(screen);
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			throw new ScreenNotExistsException("Screen not exist with Id: " +  screen.getScreenId());
		}
		return re;
	}
	
	@RequestMapping(value="/{screen}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeScreen(@PathVariable("screen") Screen screen) {
		Screen removeScreen=screenServiceImplementation.removeScreen(screen);
		ResponseEntity<Void>  re;
		if(removeScreen==null) {
			throw new ScreenNotExistsException("Screen not exist with Id: " +  screen.getScreenId());
		}
		else {
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/id/{screenId}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeScreenById(@PathVariable("screenId") int screenId) {
		Screen removeScreen=screenServiceImplementation.removeScreen(screenId);
		ResponseEntity<Void>  re;
		if(removeScreen==null) {
			throw new ScreenNotExistsException("Screen not exist with Id: " +  screenId);
		}
		else {
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/{screenId}" , method = RequestMethod.GET)
	public ResponseEntity<Screen> viewScreenById(@PathVariable("screenId") int screenId){
		ResponseEntity<Screen>  re;
		Screen findScreen=screenServiceImplementation.viewScreenById(screenId);
		if(findScreen==null) {
			throw new ScreenNotExistsException("Screen not exist with Id: " + screenId);
		}
		else {
			re = new ResponseEntity<>(findScreen,HttpStatus.FOUND);
		}
		return re;
	}
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<List<Screen>> viewScreenListAll(){
		ResponseEntity<List<Screen>> re;
		List<Screen>screens=screenServiceImplementation.viewScreenListAll();
		if(screens.isEmpty()) {
			throw new ScreenNotExistsException("No Screens");
		}
		else {
			re = new ResponseEntity<>(screens,HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/theatre/{theatreId}" , method = RequestMethod.GET)
	public ResponseEntity<List<Screen>> viewScreenByTheatreId(@PathVariable("theatreId") int theatreId){
		ResponseEntity<List<Screen>>  re;
		List<Screen>screens=screenServiceImplementation.viewScreenList(theatreId);
		if(screens.isEmpty()) {
			throw new TheatreNotExistsException("Theatre not exist with Id: " + theatreId);
		}
		else {
			re = new ResponseEntity<>(screens,HttpStatus.OK);
		}
		return re;
	}
}
