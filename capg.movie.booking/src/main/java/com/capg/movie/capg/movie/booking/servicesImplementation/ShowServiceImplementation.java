package com.capg.movie.capg.movie.booking.servicesImplementation;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.movie.capg.movie.booking.entities.Screen;
import com.capg.movie.capg.movie.booking.entities.Show;
import com.capg.movie.capg.movie.booking.repository.ScreenRepository;
import com.capg.movie.capg.movie.booking.repository.ShowRepository;
import com.capg.movie.capg.movie.booking.service.ShowService;


@Service
public class ShowServiceImplementation implements ShowService {

@Autowired
ShowRepository showRepository; 

@Autowired
ScreenRepository screenRepository;
	
	public Show addShow(Show show) {
		Optional<Screen> findScreen = screenRepository.findById(show.getScreenid());
		if(findScreen.isPresent() && (findScreen.get().getTheatreId()==show.getTheatreId() ) ) {
			showRepository.save(show);
			return show;
		}
		
		return null;
	}
	
	@Transactional
	public Show updateShow(Show show) {
		Optional<Show> getUpdateShow=showRepository.findById(show.getShowId());
		Show updateShow=null;
		Optional<Screen> findScreen = screenRepository.findById(show.getScreenid());
		if(getUpdateShow.isPresent()) {
		updateShow=getUpdateShow.get();
			if(!show.getMovie().equals(null))
			updateShow.setMovie(show.getMovie());
			if(show.getScreenid()!=0  && findScreen.isPresent())
			updateShow.setScreenid(show.getScreenid());
			if(show.getShowEndTime()!=null)
			updateShow.setShowEndTime(show.getShowEndTime());
			if(!show.getShowName().equals(null))
			updateShow.setShowName(show.getShowName());
			if(show.getShowStartTime()!=null)
			updateShow.setShowStartTime(show.getShowStartTime());
			if(show.getTheatreId()!=0 && findScreen.isPresent() && (findScreen.get().getTheatreId()==show.getTheatreId() ))
			updateShow.setTheatreId(show.getTheatreId());
		}
		return updateShow;
	}
	
	public Show removeShow(Show show) {
		
		if(show!=null) {
			for(Show findShow:showRepository.findAll()) {
				System.out.println(findShow);
				if(findShow.equals(show)) {
					System.out.println(show);
					showRepository.delete(findShow);
					return show;
				}
			}
			
		}
		return null;
	}
	
	public Show viewShow(Show show) {
		Optional<Show> findRemoveShow=showRepository.findById(show.getShowId());
		if(findRemoveShow.isPresent()) {
			return findRemoveShow.get();
		}
		return null;
	}
	
	public Show viewShowById(int showId) {
		Optional<Show> findRemoveShow=showRepository.findById(showId);
		if(findRemoveShow.isPresent()) {
			return findRemoveShow.get();
		}
		return null;
	}
	
	public List<Show> viewShowList(int theatreid){
		List<Show>shows=showRepository.findByTheatreId(theatreid);
		return shows;
	}
	

	public List<Show> viewShowList(LocalDate date){
	
		List<Show>shows=showRepository.findAll();
		List<Show>showDate=new ArrayList<Show>();
		
		for(Show show:shows) {
			if(show.getShowStartTime().toLocalDate().isEqual(date)) {
				showDate.add(show);
			}
		}
		return showDate;
	}
	
	public List<Show> viewAllShows(){
		List<Show>shows=showRepository.findAll();
		return shows;
	}

}
