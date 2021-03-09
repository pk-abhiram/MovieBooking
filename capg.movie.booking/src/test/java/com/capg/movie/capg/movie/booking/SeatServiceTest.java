package com.capg.movie.capg.movie.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.movie.capg.movie.booking.entities.Seat;
import com.capg.movie.capg.movie.booking.servicesImplementation.SeatServiceImplementation;
@SpringBootTest
class SeatServiceTest {
	@Autowired
	SeatServiceImplementation seatServiceImplementaion;

	//	@Test
	void testAddSeat() {
		Seat seat = new Seat("J50", "Standard", 220.03);
		seatServiceImplementaion.addSeat(seat);
		System.out.println("Done");
	}

//	@Test
	void testUpdateSeat() {
		Seat seat = new Seat(2,"S50",null , 0);
		seatServiceImplementaion.updateSeat(seat);
		
	}

	//	@Test
	void testRemoveSeat() {
		Seat seat=new Seat(5,null, null, 0);
		seatServiceImplementaion.removeSeat(seat);
		
	}
//	@Test
	void testRemoveSeatById() {
		int seatId=3;
		seatServiceImplementaion.removeSeat(seatId);
		
	}

	//	@Test
	void testViewSeat() {
		int id = 1;
		Seat seat = seatServiceImplementaion.viewSeat(id);
		System.out.println(seat);
	}
	
	//	@Test
	void testViewSeatList() {
		seatServiceImplementaion.viewSeatList();
	}
	
//	@Test
	void testViewSeatByType() {
		String type="Standard";
		seatServiceImplementaion.viewSeatByType(type);
	}
}