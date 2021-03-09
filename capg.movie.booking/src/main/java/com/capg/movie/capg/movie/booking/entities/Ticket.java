package com.capg.movie.capg.movie.booking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ticketId;
	int noOfSeats;

	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Seat> Seats;

	@OneToOne(targetEntity = TicketBooking.class)
	TicketBooking bookingRef;
	boolean ticketStatus;

	public Ticket() {

	}
	
	public Ticket(int noOfSeats, TicketBooking bookingRef, List<Seat> Seats,
			boolean tickeStatus) {

		this.noOfSeats = noOfSeats;
		this.Seats = Seats;
		this.bookingRef = bookingRef;		
	}
	
	

	public Ticket(int ticketId, int noOfSeats, List<Seat> seats, TicketBooking bookingRef, boolean ticketStatus) {
		super();
		this.ticketId = ticketId;
		this.noOfSeats = noOfSeats;
		Seats = seats;
		this.bookingRef = bookingRef;
		this.ticketStatus = ticketStatus;
	}

	public int getTicketId() {
		return ticketId;
	}

	

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public List<Seat> getSeats() {
		return Seats;
	}

	public void setSeats(List<Seat> seats) {
		Seats = seats;
	}

	public TicketBooking getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(TicketBooking bookingRef) {
		this.bookingRef = bookingRef;
	}

	public boolean isTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", noOfSeats=" + noOfSeats 
				+ ", bookingRef=" + bookingRef + ", ticketStatus=" + ticketStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Seats == null) ? 0 : Seats.hashCode());
		result = prime * result + ((bookingRef == null) ? 0 : bookingRef.hashCode());
		result = prime * result + noOfSeats;
		result = prime * result + (ticketStatus ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (Seats == null) {
			if (other.Seats != null)
				return false;
		} else if (!Seats.equals(other.Seats))
			return false;
		if (bookingRef == null) {
			if (other.bookingRef != null)
				return false;
		} else if (!bookingRef.equals(other.bookingRef))
			return false;
		if (noOfSeats != other.noOfSeats)
			return false;
		if (ticketStatus != other.ticketStatus)
			return false;
		return true;
	}


}