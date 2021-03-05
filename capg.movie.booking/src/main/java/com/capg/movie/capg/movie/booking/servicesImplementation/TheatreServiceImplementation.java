package com.capg.movie.capg.movie.booking.servicesImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capg.movie.capg.movie.booking.entities.Theatre;
import com.capg.movie.capg.movie.booking.repository.ShowRepository;
import com.capg.movie.capg.movie.booking.repository.TheatreRepository;
import com.capg.movie.capg.movie.booking.service.TheatreService;

@Service
public class TheatreServiceImplementation implements TheatreService{
	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	ShowRepository showRepository;

	public Theatre addTheatre(Theatre theatre) {
		theatreRepository.save(theatre);
		return theatre;
	}
	
	@Transactional
	public Theatre updateTheatre(Theatre theatre) {
		Optional<Theatre> getUpdateTheatre=theatreRepository.findById(theatre.getTheatreId());
		Theatre updateTheatre=null;
		if(getUpdateTheatre.isPresent()) {
		updateTheatre=getUpdateTheatre.get();
		if(!theatre.getListOfMovies().isEmpty())
		{updateTheatre.setListOfMovies(theatre.getListOfMovies());}
		if(!theatre.getListOfScreens().isEmpty())
		{updateTheatre.setListOfScreens(theatre.getListOfScreens());}
		if(theatre.getManagerContact()!=null)
		{updateTheatre.setManagerContact(theatre.getManagerContact());}
		if(theatre.getManagerName()!=null)
		{updateTheatre.setManagerName(theatre.getManagerName());}
		if(theatre.getTheatreCity()!=null)
		{updateTheatre.setTheatreCity(theatre.getTheatreCity());}
		if(theatre.getTheatreCity()!=null)
		{updateTheatre.setTheatreName(theatre.getTheatreName());}
		}
		return updateTheatre;
	}
	
	public Theatre removeTheatre(Theatre theatre) {
		Optional<Theatre> findRemoveTheatre=theatreRepository.findById(theatre.getTheatreId());
		Theatre removeTheatre=null;
		if(findRemoveTheatre.isPresent()) {
		removeTheatre=findRemoveTheatre.get();
		theatreRepository.delete(removeTheatre);
		}
		return removeTheatre;
	}
	
	public Theatre viewTheatreById(int theatreId) {
		Optional<Theatre> findRemoveTheatre=theatreRepository.findById(theatreId);
		return findRemoveTheatre.get();
	}
	public List<Theatre>viewTheatreList(){
		List<Theatre>theatres=theatreRepository.findAll();
		return theatres;
	}
	
	public List<Theatre>viewTheatreList(int theatreId){
		Optional<Theatre>theatresByID=theatreRepository.findById(theatreId);
		List<Theatre>theatres=new ArrayList<Theatre>();
		theatres=theatresByID.stream().collect(Collectors.toList());	
		return theatres;
	}

}
