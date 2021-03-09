package com.capg.movie.capg.movie.booking.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.capg.movie.capg.movie.booking.entities.Theatre;
@Component
public interface TheatreService {

	public Theatre addTheatre(Theatre theatre);
	@Transactional
	public Theatre updateTheatre(Theatre theatre);
	public Theatre removeTheatre(Theatre theatre);
	public Theatre viewTheatreById(int theatreId);
	public List<Theatre>viewTheatreListByCity(String City);
}
