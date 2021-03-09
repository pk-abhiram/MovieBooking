package com.capg.movie.capg.movie.booking.servicesImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.movie.capg.movie.booking.entities.Show;
import com.capg.movie.capg.movie.booking.entities.TicketBooking;
import com.capg.movie.capg.movie.booking.repository.SeatRepository;
import com.capg.movie.capg.movie.booking.repository.TicketBookingRepository;
import com.capg.movie.capg.movie.booking.repository.TicketRepository;
import com.capg.movie.capg.movie.booking.service.TicketBookingService;

@Service
public class TicketBookingServiceImplementation implements TicketBookingService{
	@Autowired
	TicketBookingRepository ticketBookingRepository;

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	ShowServiceImplementation showServiceImplementation;
	
	
public TicketBooking addBooking(TicketBooking ticketBooking) {
		
		Optional<TicketBooking> getTicketBooking = ticketBookingRepository.findById(ticketBooking.getTicketId());
		if(getTicketBooking.isEmpty()) {
			ticketBookingRepository.save(ticketBooking);
			return ticketBooking;
		}
		return null;
	}



@Transactional
public TicketBooking updateBooking(TicketBooking booking) {
	Optional<TicketBooking> getUpdateBooking = ticketBookingRepository.findById(booking.getTicketId());
	TicketBooking updateBooking = null;
	if(getUpdateBooking.isPresent()) {
		updateBooking = getUpdateBooking.get();
		if(booking.getTicket()!=null) {
			updateBooking.setTicket(booking.getTicket());
		}
		if(booking.getBookingDate()!=null) {
			updateBooking.setBookingDate(booking.getBookingDate());
		}
		if(booking.getShowId()!= 0) {
			updateBooking.setShowId(booking.getShowId());
		}
		if(booking.getTotalCost()!=0) {
			updateBooking.setTotalCost(booking.getTotalCost());
		}
		if(booking.getTransactionId()!=0) {
			updateBooking.setTransactionId(booking.getTransactionId());
		}
		if(booking.getTransactionMode()!=null) {
			updateBooking.setTransactionMode(booking.getTransactionMode());
		}
		if(booking.getTransactionStatus()!=null) {
			updateBooking.setTransactionStatus(booking.getTransactionStatus());
		}
	}
	return updateBooking;
}


@Override
public TicketBooking cancelBooking(TicketBooking ticketBooking) {
	Optional<TicketBooking> findCancelBooking = ticketBookingRepository.findById(ticketBooking.getTicketId());
	TicketBooking cancelBooking = null;
	if(findCancelBooking.isPresent()) {
		cancelBooking = findCancelBooking.get();
		ticketBookingRepository.delete(cancelBooking);
	}
	return cancelBooking;
}


@Override
public List<TicketBooking> showAllBooking(int movieId) {
	List<TicketBooking> allBookings=new ArrayList<TicketBooking>();
	List<Show>shows=showServiceImplementation.viewAllShows();
	for(Show s:shows) {
		if(s.getMovie().getMovieId()==movieId) {
			allBookings.addAll(ticketBookingRepository.findByShowId(s.getShowId()));
		}
	}
	return allBookings;
}


@Override
public List<TicketBooking> showAllBooking(LocalDate date) {
	List<TicketBooking> allBookings = ticketBookingRepository.findByBookingDate(date);
	return allBookings;
}


@Override
public List<TicketBooking> showBookingList(int showId) {
	List<TicketBooking> allBookings = ticketBookingRepository.findByShowId(showId);
	if(allBookings.isEmpty()) {
		return null;
	}
	return allBookings;
}


@Override
public double calculateTotalCost(int bookingid) {
	Optional<TicketBooking> findTicketBooking = ticketBookingRepository.findById(bookingid);
	double sum=0;
	double priceOfOne=150;
	if(findTicketBooking.isPresent()) {
	sum=findTicketBooking.get().getTicket().getNoOfSeats()*priceOfOne;
	return sum;
	}
	return sum;
}


public TicketBooking showAllBookingList(int ticketId) {
	TicketBooking allBookings = ticketBookingRepository.findByTicketId(ticketId);
	if(allBookings==null) {
		return null;
	}
	return allBookings;
}


}