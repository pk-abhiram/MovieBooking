package com.capg.movie.capg.movie.booking;

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
import com.capg.movie.capg.movie.booking.servicesImplementation.TheatreServiceImplementation;
@SpringBootTest
class TheatreServiceTest {

	@Autowired
	TheatreServiceImplementation theatreServiceImplementation; 
	
	//@Test
	void testAddTheatre() {
		Movie movie1=new Movie("movie1", "asda", "asdsa", "sadas", "asdas");
		Movie movie2=new Movie("movie2", "as", "as", "sa", "as");
		List<Movie>movies=new ArrayList<Movie>();
		movies.add(movie1);
		movies.add(movie2);
		Screen screen=new Screen(4, null, null, 0, 0);
		List<Screen> screens=new ArrayList<>();
		screens.add(screen);
		
		Theatre theatre=new Theatre("theatre1", "bangalore", movies, screens, "manager1", "4984665");
		System.out.println(theatreServiceImplementation.addTheatre(theatre));
	}
	
//	@Test
	void testUpdateTheatre() {
		Movie movie1=new Movie("movie1", "asda", "asdsa", "sadas", "asdas");
		List<Movie>movies=new ArrayList<Movie>();
		movies.add(movie1);
		//movies.add(movie2);
		Screen screen=new Screen(4, null, null, 0, 0);
		List<Screen> screens=new ArrayList<>();
		screens.add(screen);
		Theatre theatre=new Theatre(5,"theatre1_updated", "bangalore", movies, screens, "manager1", "4984665");
		System.out.println(theatreServiceImplementation.updateTheatre(theatre));
	}

//	@Test
	void testRemoveTheatre() {
		Theatre theatre=new Theatre(1,"theatre1_updated", "bangalore", null, null, "manager1", "4984665");
		theatreServiceImplementation.removeTheatre(theatre);
	}
	
//	@Test
	void testViewTheatreById() {
		int theatreId=5;
		System.out.println(theatreServiceImplementation.viewTheatreById(theatreId));
	}
	
//	@Test
	void testViewTheatreList() {
		
		System.out.println(theatreServiceImplementation.viewTheatreList());
	}
	
//	@Test
	void testViewTheatreListByCity() {
		System.out.println(theatreServiceImplementation.viewTheatreListByCity("banga"));
	}
	
//	@Test
	void testRemoveTheatreById() {
		int theatreId=10;
		System.out.println(theatreServiceImplementation.removeTheatre(theatreId));
	}
	
//	@Test
	public void testTheatreAddMovie() {
		Movie movie2=new Movie("movie5", "as", "as", "sa", "as");
		int theatreId=5;
		System.out.println(theatreServiceImplementation.theatreAddMovie(theatreId, movie2));
	}
	
//	@Test
	public void testTheatreRemoveMovie() {
		int theatreId=5;
		int movieId=200;
		System.out.println(theatreServiceImplementation.theatreRemoveMovie(theatreId, movieId));
	}
	
//	@Test
	public void testTheatreAddScreen() {
		Screen screen=new Screen(5, null, null, 0, 0);
		int theatreId=5;
		System.out.println(theatreServiceImplementation.theatreAddScreen(theatreId, screen));
	}
	
//	@Test
	public void testTheatreRemoveScreen() {
		int theatreId=5;
		int screenId=14;
		System.out.println(theatreServiceImplementation.theatreRemoveScreen(theatreId, screenId));
	}
}

