package com.capg.movie.capg.movie.booking.servicesImplementation;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.movie.capg.movie.booking.entities.Seat;
import com.capg.movie.capg.movie.booking.repository.SeatRepository;
import com.capg.movie.capg.movie.booking.service.SeatService;


@Service
public class SeatServiceImplementation implements SeatService{

	@Autowired
	SeatRepository seatRepository;

	public Seat addSeat(Seat seat) {
		Optional<Seat> findSeat=seatRepository.findById(seat.getSeatId());
		if(findSeat.isEmpty()) {
			seatRepository.save(seat);
			return seat;
		}
		return null;
		
	}

	@Transactional
	public Seat updateSeat(Seat seat) {
		Optional<Seat> getUpdateSeat = seatRepository.findById(seat.getSeatId());
		Seat updateSeat = null;
		if(getUpdateSeat.isPresent()) {
			updateSeat = getUpdateSeat.get();
			if(seat.getSeatNumber()!=null) {
				updateSeat.setSeatNumber(seat.getSeatNumber());
			}
			if(seat.getType()!=null) {
				updateSeat.setType(seat.getType());
			}
			if(seat.getPrice()!=0) {
				updateSeat.setPrice(seat.getPrice());
			}
		}
		return updateSeat;
	}

	public Seat removeSeat(Seat seat) {
		Optional<Seat> findSeat = seatRepository.findById(seat.getSeatId());
		Seat removeSeat = null;
		if(findSeat.isPresent()) {
			removeSeat = findSeat.get();
			seatRepository.delete(removeSeat);
		}
		return removeSeat;

	}

	public Seat viewSeat(int seatId) {
		Optional<Seat> findSeat = seatRepository.findById(seatId);
		if(findSeat.isPresent()) {
			return findSeat.get();
		}
		return null;
	}

	public List<Seat> viewSeatList(){
		List<Seat> seats = seatRepository.findAll();
		return seats;
	}

	public Seat removeSeat(int seatId) {
		Optional<Seat> findSeat = seatRepository.findById(seatId);
		if(findSeat.isEmpty()) {
			seatRepository.deleteById(seatId);
		}
		return findSeat.get();
	}

		public List<Seat> viewSeatByType(String type){
		List<Seat> seats = seatRepository.findByType(type);
		if(seats.isEmpty()) {
			return seats;
		}
		return null;
		}
}