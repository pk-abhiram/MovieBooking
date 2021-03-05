package com.capg.movie.capg.movie.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.movie.capg.movie.booking.entities.Screen;
import com.capg.movie.capg.movie.booking.servicesImplementation.ScreenServiceImplementation;
@SpringBootTest
class ScreenServiceTest {

@Autowired
ScreenServiceImplementation screenServiceImplementation;
	
//	@Test
	void testAddScreen() {
		Screen screen=new Screen(2, "screen 1", null, 4, 5);
		screenServiceImplementation.addScreen(screen);
	}
	
//	@Test
	void testUpdateScreen() {
		Screen screen=new Screen(5,2, "screen update", null, 0, 0);
		screenServiceImplementation.updateScreen(screen);
	}

//	@Test
	void testRemoveScreen() {
		Screen screen=new Screen(5,2, "screen update", null, 0, 0);
		screenServiceImplementation.removeScreen(screen);
	}
	
//	@Test
	void testViewScreenById() {
		int screenId=2;
		screenServiceImplementation.viewScreenById(screenId);
	}
	
//	@Test
	void testViewScreenListAll() {
		screenServiceImplementation.viewScreenListAll();
	}
	
//	@Test
	void testViewScreenListByTheatreId() {
		int theatreId=1;
		screenServiceImplementation.viewScreenList(theatreId);
	}
	
}