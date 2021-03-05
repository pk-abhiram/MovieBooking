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
import com.capg.movie.capg.movie.booking.exceptions.ScreenAlreadyExistsException;
import com.capg.movie.capg.movie.booking.repository.ScreenRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.ScreenServiceImplementation;

@RestController
public class ScreenController {

	@Autowired
	ScreenServiceImplementation ScreenServiceImplementation;
	
	@Autowired
	ScreenRepository screenRepository;
	
	@RequestMapping(value="/screen" , method = RequestMethod.POST)
	public ResponseEntity<Void> addScreen(@RequestBody Screen screen) {
		ResponseEntity<Void>  re;
		
		Optional<Screen> findScreen = screenRepository.findById(screen.getScreenId());
		if(findScreen.isEmpty()) {
			ScreenServiceImplementation.addScreen(findScreen.get());
			 re = new ResponseEntity<>(HttpStatus.CREATED);
		}
		else {
			throw new ScreenAlreadyExistsException("Screen not found : " +  screen);
		}
		return re;
	}
	
	@RequestMapping(value="/screen" , method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> updateEmployee(@RequestBody Screen screen) {
		ResponseEntity<Void>  re;
		Optional<Screen> findScreen = screenRepository.findById(screen.getScreenId());
		if(findScreen.isPresent()) {
			ScreenServiceImplementation.updateScreen(screen);
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			 re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return re;
	}
	
	@RequestMapping(value="/screen/{screen}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeScreen(@PathVariable("screen") Screen screen) {
		Screen removeScreen=ScreenServiceImplementation.removeScreen(screen);
		ResponseEntity<Void>  re;
		if(removeScreen==null) {
			 re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/screen/{screenId}" , method = RequestMethod.DELETE)
	public ResponseEntity<Screen> viewScreenById(@PathVariable("screenId") int screenId){
		ResponseEntity<Screen>  re;
		Screen findScreen=ScreenServiceImplementation.viewScreenById(screenId);
		if(findScreen==null) {
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			re = new ResponseEntity<>(findScreen,HttpStatus.FOUND);
		}
		return re;
	}
	@RequestMapping(value="/screen" , method = RequestMethod.GET)
	public ResponseEntity<List<Screen>> viewScreenListAll(){
		ResponseEntity<List<Screen>> re;
		List<Screen>screens=ScreenServiceImplementation.viewScreenListAll();
		if(screens.isEmpty()) {
			re = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			re = new ResponseEntity<>(screens,HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/screenT/{theatreId}" , method = RequestMethod.GET)
	public ResponseEntity<List<Screen>> viewScreenByTheatreId(@PathVariable("theatreId") int theatreId){
		ResponseEntity<List<Screen>>  re;
		List<Screen>screens=ScreenServiceImplementation.viewScreenList(theatreId);
		if(screens.isEmpty()) {
			re = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			re = new ResponseEntity<>(screens,HttpStatus.OK);
		}
		return re;
	}
}
