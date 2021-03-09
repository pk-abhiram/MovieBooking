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
import com.capg.movie.capg.movie.booking.servicesImplementation.ShowServiceImplementation;
@SpringBootTest
class ShowServiceTest {

	@Autowired
	ShowServiceImplementation showServiceImplementation; 
	
//	@Test
	void testAddShow() {
		
		Movie movie2=new Movie("movie2", "as", "as", "sa", "as");

		Show show=new Show(null, null, "show1", movie2, 6, 4);
		System.out.println(showServiceImplementation.addShow(show));
	}
	
//	@Test
	void testUpdateShow() {
		Movie movie1=new Movie("movie1", "asda", "asdsa", "sadas", "asdas");
		
		Show show=new Show(14,null, LocalDateTime.now(), "show2_updated", movie1, 6, 4);
		System.out.println(showServiceImplementation.updateShow(show));
	}

//	@Test
	void testRemoveShow() {
		Movie movie2=new Movie("movie2", "as", "as", "sa", "as");
		Show show=new Show(null, null, "show1", movie2, 6, 4);
		System.out.println(showServiceImplementation.removeShow(show));
	}
	
//	@Test
	void testViewShow() {
		Show show=new Show(14,null, LocalDateTime.now(), "show2_updated", null, 6, 4);
		System.out.println(showServiceImplementation.viewShow(show));
	}

//	@Test
	void testViewShowListByTheatreId() {
		int theatreid=4;
		System.out.println(showServiceImplementation.viewShowList(theatreid));
	}
	
//	@Test
	void testViewShowListByLocalDate() {
		LocalDate date=LocalDate.now();
		System.out.println(showServiceImplementation.viewShowList(date));
	}
//  @Test
	void testViewAllShows() {
		System.out.println(showServiceImplementation.viewAllShows());
	}
}
