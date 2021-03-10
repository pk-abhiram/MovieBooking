package com.capg.movie.capg.movie.booking;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.movie.capg.movie.booking.entities.Movie;
import com.capg.movie.capg.movie.booking.exceptions.MovieAlreadyExistsException;
import com.capg.movie.capg.movie.booking.exceptions.MovieNotExistsException;
import com.capg.movie.capg.movie.booking.servicesImplementation.MovieServiceImplementation;
import com.capg.movie.capg.movie.booking.servicesImplementation.ScreenServiceImplementation;
import com.capg.movie.capg.movie.booking.servicesImplementation.ShowServiceImplementation;
import com.capg.movie.capg.movie.booking.servicesImplementation.TheatreServiceImplementation;
@SpringBootTest
class MovieServiceTest {

		@Autowired
		MovieServiceImplementation movieServiceImplementation;
		
		@Autowired
		ScreenServiceImplementation screenServiceImplementation;
		
		@Autowired
		TheatreServiceImplementation theatreServiceImplementation;
		
		@Autowired
		ShowServiceImplementation showServiceImplementaion;
		
//		@Test
		void testAddMovie() {
			
			try {
				
				Movie m=new Movie("Baahubali: The Beginning","comedy","2:30:37","Telugu","In the kingdom of Mahishmati, Shivudu falls in love with a young warrior woman. While trying to woo her, he learns about the conflict-ridden past of his family and his true legacy.");
				movieServiceImplementation.addMovie(m);
				}
				catch(MovieAlreadyExistsException e) {
					e.printStackTrace();
				}
		}
//		@Test
		void testUpdateMovie() {
			
			try {
				
				Movie m=new Movie(20,"3 idiots",null,null,null,null);
				movieServiceImplementation.updateMovie(m);
				}
				catch(MovieNotExistsException e) {
					e.printStackTrace();
				}
		}
		
//		@Test
		void testViewMovie() {
			
			int id =1;
			System.out.println(movieServiceImplementation.viewMovie(id));
			
		}
//		@Test	
		void testViewAllMovie() {
			
			
			System.out.println(movieServiceImplementation.viewMovieList());
			
		}
		
//		@Test
		void testremoveMovie() {
			
			int id =3;
			movieServiceImplementation.removeMovie(id);
			
		}
		
//		@Test
		void testViewMovieByTheatreId() {
			
			int id =1;
//			Movie m=new Movie("3 idiot","Comedy/drama","2:45:23","Hindi","In college, Farhan and Raju form a great bond with Rancho due to his refreshing outlook. Years later, a bet gives them a chance to look for their long-lost friend whose existence seems rather elusive.");
//			Movie m1=new Movie("Chhichhore","Comedy-drama/Romance","2:30:13","Hindi","A tragic incident forces Anirudh, a middle-aged man, to take a trip down memory lane and reminisce his college days along with his friends, who were labelled as losers.");
//			
//			List<Movie> movie=new ArrayList<Movie>(  ) ;
//			movie.add(m);
//			movie.add(m1);
////			movieServiceImplementation.addMovie(m);
//			
//		Screen s=new Screen(20,"Golden Screen", null,5, 15);
//		List<Screen> screen=new ArrayList<>();
//			screen.add(s);		
////			screenServiceImplementation.addScreen(s);
////			
//			Theatre t=new Theatre( "PVR", "Bangalore", movie,screen, "Shama", "99999 00000");
//			List<Theatre> theatre=new ArrayList<>();
//////			theatre.add(t);
//			theatreServiceImplementation.addTheatre(t);
			
			
			
			System.out.println(movieServiceImplementation.viewMovieList(id));
			
		}
		
//		@Test
		void testViewMovieByShow() {
//			Movie m=new Movie("3 idiot","Comedy/drama","2:45:23","Hindi","In college, Farhan and Raju form a great bond with Rancho due to his refreshing outlook. Years later, a bet gives them a chance to look for their long-lost friend whose existence seems rather elusive.");
//			List<Movie> movie=new ArrayList<>();
//			Show s=new Show( LocalDateTime.of(2021, 03, 10, 02, 30), LocalDateTime.of(2021, 03, 10, 05, 30),"Noon Show", m,1, 20);
//		   showServiceImplementaion.addShow(s);
			
			
			System.out.println(movieServiceImplementation.viewMovieList(LocalDate.of(2021, 3, 10)));
			
		}
		
		
		
}
