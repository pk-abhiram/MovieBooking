package com.capg.movie.capg.movie.booking;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.movie.capg.movie.booking.entities.Movie;
import com.capg.movie.capg.movie.booking.servicesImplementation.MovieServiceImplementation;
@SpringBootTest
class MovieServiceTest {

	@Autowired
	MovieServiceImplementation movieServiceImplementation;
	
	
//	@Test
	void testAddMovie() {
		Movie movie=new Movie("2 Idiots", "Comedy", "2:30", "Hindi", "College");
		Movie addMovie=movieServiceImplementation.addMovie(movie);
		System.out.println(addMovie);
	}
	
//	@Test
	void testUpdateMovie() {
		Movie movie=new Movie(1,"3 Idiots", null, "2:30", null, null);
		Movie updateMovie=movieServiceImplementation.updateMovie(movie);
		System.out.println(updateMovie);
	}
	
//	@Test
	void testRemoveMovie() {
		Movie movie=new Movie(2,"2 Idiots", "Comedy", "2:30", "Hindi", "College");
		Movie removeMovie=movieServiceImplementation.removeMovie(movie);
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
		List<Movie>movies=movieServiceImplementation.viewMovieList(theatreId);
		System.out.println(movies);
	}
//	@Test
	void testViewMovieListByDate() {
		LocalDate date=LocalDate.now();
		List<Movie>movies=movieServiceImplementation.viewMovieList(date);
		System.out.println(movies);
	}
}
