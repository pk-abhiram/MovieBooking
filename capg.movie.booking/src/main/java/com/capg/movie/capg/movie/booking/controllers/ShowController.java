package com.capg.movie.capg.movie.booking.controllers;

import java.time.LocalDate;
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
import com.capg.movie.capg.movie.booking.entities.Show;
import com.capg.movie.capg.movie.booking.exceptions.ScreenNotExistsException;
import com.capg.movie.capg.movie.booking.exceptions.ShowAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.ShowNotExistsException;
import com.capg.movie.capg.movie.booking.repository.ScreenRepository;
import com.capg.movie.capg.movie.booking.repository.ShowRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.ShowServiceImplementation;

@RestController
@RequestMapping(value = "/show")
public class ShowController {

	@Autowired
	ShowServiceImplementation showServiceImplementation;
	
	@Autowired
	ShowRepository showRepository;
	
	@Autowired
	ScreenRepository screenRepository;
	
	@RequestMapping(value="/" , method = RequestMethod.POST)
	public ResponseEntity<Void> addShow(@RequestBody Show show) {
		ResponseEntity<Void>  re;
		Optional<Show> findShow = showRepository.findById(show.getShowId());
		if(findShow.isEmpty()) {
			Optional<Screen> findScreen = screenRepository.findById(show.getScreenid());
			if(findScreen.isPresent() && (findScreen.get().getTheatreId()==show.getTheatreId() )) {
				showServiceImplementation.addShow(show);
				re=new ResponseEntity<>(HttpStatus.CREATED);
			}
			else {
				throw new ScreenNotExistsException("Screen not exist with screeId: " + show.getShowId()+"AND theatreId"+show.getTheatreId());
			}
		}	
		else {
			throw new ShowAlreadyExistsException("Show Already Exist with ID: " +  show.getShowId());
		}
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> updateShow(@RequestBody Show show) {
		ResponseEntity<Void>  re;
		Optional<Show> findShow = showRepository.findById(show.getShowId());
		if(findShow.isPresent()) {
			showServiceImplementation.updateShow(show);
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			throw new ShowNotExistsException("Show not exist with Id: " +  show.getShowId());
		}
		return re;
	}
	
	@RequestMapping(value="/{show}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeShow(@PathVariable("show") Show show) {
		Show removeShow=showServiceImplementation.removeShow(show);
		ResponseEntity<Void>  re;
		if(removeShow==null) {
			throw new ShowNotExistsException("Show not exist with Id: " +  show.getShowId());
		}
		else {
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		return re;
	}
	
	@RequestMapping(value="/{show}" , method = RequestMethod.GET)
	public ResponseEntity<Show> viewShow(@PathVariable("show") Show show){
		ResponseEntity<Show>  re;
		Show findShow=showServiceImplementation.viewShow(show);
		if(findShow==null) {
			throw new ShowNotExistsException("Show not exist with Id: " + show.getShowId());
		}
		else {
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		}
		return re;
	}
	
	@RequestMapping(value="/{showId}" , method = RequestMethod.GET)
	public ResponseEntity<Show> viewScreenById(@PathVariable("showId") int showId){
		ResponseEntity<Show>  re;
		Show findShow=showServiceImplementation.viewShowById(showId);
		if(findShow==null) {
			throw new ShowNotExistsException("Show not exist with Id: " + showId);
		}
		else {
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		}
		return re;
	}
	
	@RequestMapping(value="/theatre/{theatreId}" , method = RequestMethod.GET)
	public ResponseEntity<List<Show>> viewScreenBytheatreId(@PathVariable("theatreId") int theatreId){
		ResponseEntity<List<Show>>  re;
		List<Show> findShow=showServiceImplementation.viewShowList(theatreId);
		if(findShow.isEmpty()) {
			throw new ShowNotExistsException("No shows in theatre : " + theatreId);
		}
		else {
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		}
		return re;
	}
	
	@RequestMapping(value="/LocalDate/{date}" , method = RequestMethod.GET)
	public ResponseEntity<List<Show>> viewScreenByDate(@PathVariable("date") LocalDate date){
		ResponseEntity<List<Show>>  re;
		List<Show> findShow=showServiceImplementation.viewShowList(date);
		if(findShow.isEmpty()) {
			throw new ShowNotExistsException("No shows on date : " + date);
		}
		else {
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		}
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<List<Show>> viewScreensAll(){
		ResponseEntity<List<Show>>  re;
		List<Show> findShow=showServiceImplementation.viewAllShows();
		if(findShow.isEmpty()) {
			throw new ShowNotExistsException("No shows currently : ");
		}
		else {
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		}
		return re;
	}
	
	
}
