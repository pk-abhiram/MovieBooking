package com.capg.movie.capg.movie.booking.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capg.movie.capg.movie.booking.entities.Movie;
import com.capg.movie.capg.movie.booking.exceptions.MovieAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.MovieNotExistsException;
import com.capg.movie.capg.movie.booking.repository.MovieRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.MovieServiceImplementation;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

	@Autowired
	MovieServiceImplementation movieServiceImplementation;
	
	@Autowired
	MovieRepository movieRepository;
	
//	Adding a Movie
	@RequestMapping(value="/" , method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)  {
		ResponseEntity<Movie>  re;
		Optional<Movie> findMovie = movieRepository.findById(movie.getMovieId());
		if(findMovie.isEmpty()) {
			movieServiceImplementation.addMovie(movie);
			 re = new ResponseEntity<>(movie,HttpStatus.CREATED);
		}
		else {
			throw new MovieAlreadyExistsException("Movie Already Exist with ID : " +  movie.getMovieId());
		}
		return re;	
	}
	
//	Updating a Movie using movie_id	
	@RequestMapping(value="/" , method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMovie(@RequestBody Movie movie)  {
		ResponseEntity<Void>  re;
		Optional<Movie> findMovie = movieRepository.findById(movie.getMovieId());
		if(findMovie.isPresent()) {
		movieServiceImplementation.updateMovie(movie);
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			throw new MovieNotExistsException("Movie not exist with Id: " +  movie.getMovieId());
		}
		return re;
	}
	
//  Removing a Movie using movie_id 
	@RequestMapping(value="/{movieId}" , method = RequestMethod.DELETE)	
	public ResponseEntity<Void> removeMovie(@PathVariable("movieId") int movieId) {
		ResponseEntity<Void>  re;
		Optional<Movie> findMovie = movieRepository.findById(movieId);
		if(findMovie.isPresent()) {
		movieServiceImplementation.removeMovie(movieId);
			re=new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			throw new MovieNotExistsException("Movie not exist with Id: " + movieId);
		}
		return re;
	}
	
//  Finding Movie using movie_id
	@RequestMapping(value="/{movieId}" , method = RequestMethod.GET)
	public ResponseEntity<Movie> findMovieById(@PathVariable("movieId") int movieId) {
		ResponseEntity<Movie>  re;
		Optional<Movie> findMovie = movieRepository.findById(movieId);
		if(findMovie.isPresent()) {
			re=new ResponseEntity<>(findMovie.get(),HttpStatus.OK);
		}
		else {
			throw new MovieNotExistsException("Movie not exist with Id: " +  movieId);
		}
		return re;
	}
//	Displaying all Movies present in the database
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> viewMovieList(){
		ResponseEntity<List<Movie>>  re;
		if(movieServiceImplementation.viewMovieList().isEmpty()) {
			throw new MovieNotExistsException("Movies not exist in Database currently ");
		}
		re=new ResponseEntity<>(movieServiceImplementation.viewMovieList(),HttpStatus.OK);
		return re;
	}
	
//  finding list of Movies using theatre_id
	@RequestMapping(value="/theatre/{theatreid}" , method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> viewMovieList(@PathVariable("theatreid") int theatreid) {
		ResponseEntity<List<Movie>>  re;
		List<Movie>movies=movieServiceImplementation.viewMovieList(theatreid);
		if(movies.isEmpty()) {
			throw new MovieNotExistsException("Movies not running in theatre ID:"+theatreid);
		}
		re=new ResponseEntity<>(movies,HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(value="/date/{date}" , method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> viewMovieList(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
		ResponseEntity<List<Movie>>  re;
		List<Movie>movies=movieServiceImplementation.viewMovieList(date);
		if(movies.isEmpty()) {
			throw new MovieNotExistsException("Movies not running on  "+date);
		}
		re=new ResponseEntity<>(movies,HttpStatus.OK);
		return re;
	}
	
	
}
