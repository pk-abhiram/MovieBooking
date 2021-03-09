package com.capg.movie.capg.movie.booking.servicesImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capg.movie.capg.movie.booking.entities.Movie;
import com.capg.movie.capg.movie.booking.entities.Show;
import com.capg.movie.capg.movie.booking.entities.Theatre;
import com.capg.movie.capg.movie.booking.repository.MovieRepository;
import com.capg.movie.capg.movie.booking.repository.ShowRepository;
import com.capg.movie.capg.movie.booking.repository.TheatreRepository;
import com.capg.movie.capg.movie.booking.service.MovieService;

@Service
public class MovieServiceImplementation implements MovieService {
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	public Movie addMovie(Movie movie) {
		Optional<Movie> findMovie=movieRepository.findById(movie.getMovieId());
		if(findMovie.isEmpty()) {
			movieRepository.save(movie);
			return movie;
		}
		return null;
	}
	
	@Transactional
	public Movie updateMovie(Movie movie) {
		Optional<Movie> getUpdateMovie=movieRepository.findById(movie.getMovieId());
		Movie updateMovie=null;
		if(getUpdateMovie.isPresent()) {
		updateMovie=getUpdateMovie.get();
		if(movie.getDescription()!=null)
		{updateMovie.setDescription(movie.getDescription());}
		if(movie.getLanguage()!=null)
		{updateMovie.setLanguage(movie.getLanguage());}
		if(movie.getMovieGenre()!=null)
		{updateMovie.setMovieGenre(movie.getMovieGenre());}
		if(movie.getMovieHours()!=null)
		{updateMovie.setMovieHours(movie.getMovieHours());}
		if(movie.getMovieName()!=null)
		{updateMovie.setMovieName(movie.getMovieName());}
		}
		return updateMovie;
	}
	
	public Movie removeMovie(int movieId) {
		Optional<Movie> findRemoveMovie=movieRepository.findById(movieId);
		Movie removeMovie=null;
		if(findRemoveMovie.isPresent()) {
		removeMovie=findRemoveMovie.get();
		movieRepository.delete(removeMovie);
		}
		return removeMovie;
	}
	
	public Movie viewMovie(int movieId) {
		Optional<Movie> findRemoveMovie=movieRepository.findById(movieId);
		if(findRemoveMovie.isPresent()) {
			return findRemoveMovie.get();
		}
		return null;
	}
	public List<Movie>viewMovieList(){
		List<Movie>movies=movieRepository.findAll();
		return movies;
	}
	@Transactional
	public List<Movie>viewMovieList(int theatreId){
		Optional<Theatre>theatres=theatreRepository.findById(theatreId);
		
		List<Movie>movies=null;
		if(theatres.isPresent())
		{	movies=theatres.get().getListOfMovies();}
		return movies;
	}
	public List<Movie>viewMovieList(LocalDate date){
		List<Show>shows=showRepository.findAll();
		List<Movie>movies = new ArrayList<Movie>();
		if(!shows.isEmpty()) {
		for(Show show:shows) {
			if(show.getShowStartTime().toLocalDate().isEqual(date)) {
				movies.add(show.getMovie());
			}
		}
		}
		return movies;
	}
}
