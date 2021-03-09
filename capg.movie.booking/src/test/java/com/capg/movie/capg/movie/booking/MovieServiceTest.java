package com.capg.movie.capg.movie.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.movie.capg.movie.booking.entities.Movie;
import com.capg.movie.capg.movie.booking.entities.Screen;
import com.capg.movie.capg.movie.booking.entities.Show;
import com.capg.movie.capg.movie.booking.entities.Theatre;
import com.capg.movie.capg.movie.booking.repository.ShowRepository;
import com.capg.movie.capg.movie.booking.servicesImplementation.MovieServiceImplementation;
import com.capg.movie.capg.movie.booking.servicesImplementation.ShowServiceImplementation;
import com.capg.movie.capg.movie.booking.servicesImplementation.TheatreServiceImplementation;
@SpringBootTest
class MovieServiceTest {

	@Autowired
	MovieServiceImplementation movieServiceImplementation;
	
	@Autowired
	TheatreServiceImplementation theatreServiceImplementation ; 
	
	@Autowired
	ShowServiceImplementation showServiceImplementation;
	
//	@Test
	void testAddMovie() {
		Movie movie=new Movie("3 Idiots", "Comedy", "2:30", "Hindi", "College");
		Movie addMovie=movieServiceImplementation.addMovie(movie);
		System.out.println(addMovie);
	}
	
//	@Test
	void testUpdateMovie() {
		Movie movie=new Movie(1,"3 Idiots", null, "2:40", null, null);
		Movie updateMovie=movieServiceImplementation.updateMovie(movie);
		System.out.println(updateMovie);
	}
	
//	@Test
	void testRemoveMovie() {
		int movieId=3;
		Movie removeMovie=movieServiceImplementation.removeMovie(movieId);
		System.out.println(removeMovie);
	}
	
//	@Test
	void testViewMovie() {
		int movieId=1;
		Movie viewMovie=movieServiceImplementation.viewMovie(movieId);
		System.out.println(viewMovie);
	}
	
//	@Test
	void testViewMovieList() {
		List<Movie>movies=movieServiceImplementation.viewMovieList();
		System.out.println(movies);
		}
//	@Test
	void testViewMovieListByTheatreId() {
		int theatreId=1;
		List<Movie>movies1=movieServiceImplementation.viewMovieList(theatreId);
		System.out.println(movies1);
	}
	//@Test
	void testViewMovieListByDate() {
		LocalDate date=LocalDate.now();
		List<Movie>movies=movieServiceImplementation.viewMovieList(date);
		System.out.println(movies);
	}
}
