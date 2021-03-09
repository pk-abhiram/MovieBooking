package com.capg.movie.capg.movie.booking.servicesImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.movie.capg.movie.booking.entities.Movie;
import com.capg.movie.capg.movie.booking.entities.Screen;
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
	
	@Autowired
	MovieServiceImplementation movieServiceImplementation;

	@Autowired
	ScreenServiceImplementation screenServiceImplementation;
	
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
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			return findTheatre.get();
		}
		return null;
	}
	public List<Theatre>viewTheatreList(){
		List<Theatre>theatres=theatreRepository.findAll();
		return theatres;
	}
	
	public List<Theatre>viewTheatreListByCity(String City){
		List<Theatre>theatres=theatreRepository.findTheatreWithCity(City);
			
		return theatres;
	}
	
	public Theatre removeTheatre(int theatreId) {
		Optional<Theatre> findRemoveTheatre=theatreRepository.findById(theatreId);
		Theatre removeTheatre=null;
		if(findRemoveTheatre.isPresent()) {
		removeTheatre=findRemoveTheatre.get();
		theatreRepository.delete(removeTheatre);
		}
		return removeTheatre;
	}

	public Theatre theatreAddMovie(int theatreId,Movie movie) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			Theatre theatre=findTheatre.get();
			theatre.getListOfMovies().add(movie);
			theatreRepository.save(theatre);
			return theatre;
		}
		return null;
	}
	
	public Theatre theatreRemoveMovie(int theatreId,int movieid) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			Theatre theatre=findTheatre.get();
			if(movieServiceImplementation.viewMovie(movieid)!=null && theatre.getListOfMovies().get(theatre.getListOfMovies().indexOf(movieServiceImplementation.viewMovie(movieid)))!=null)
			{
			theatre.getListOfMovies().remove(movieServiceImplementation.viewMovie(movieid));
			theatreRepository.save(theatre);
			return theatre;
			}
		}
		return null;
	}
	
	public Theatre theatreAddScreen(int theatreId,Screen screen) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent() && findTheatre.get().getTheatreId()==screen.getTheatreId()) {
			Theatre theatre=findTheatre.get();
			theatre.getListOfScreens().add(screen);
			theatreRepository.save(theatre);
			return theatre;
		}
		return null;
	}
	
	public Theatre theatreRemoveScreen(int theatreId,int screenId) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			Theatre theatre=findTheatre.get();
			
			for(Screen s:theatre.getListOfScreens())
			{
				if(s.getScreenId()==screenId)
				{
					
					theatre.getListOfScreens().remove(s);
					theatreRepository.save(theatre);
					System.out.println();
					
					return theatre;
				}
			
			}
		}
		return null;
	}
}
