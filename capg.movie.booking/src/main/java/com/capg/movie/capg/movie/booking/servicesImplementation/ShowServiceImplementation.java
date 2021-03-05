package com.capg.movie.capg.movie.booking.servicesImplementation;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capg.movie.capg.movie.booking.entities.Show;
import com.capg.movie.capg.movie.booking.repository.ShowRepository;
import com.capg.movie.capg.movie.booking.service.ShowService;


@Service
public class ShowServiceImplementation implements ShowService {

@Autowired
ShowRepository showRepository; 
	
	public Show addShow(Show show) {
		showRepository.save(show);
		return show;
	}
	
	@Transactional
	public Show updateShow(Show show) {
		Optional<Show> getUpdateShow=showRepository.findById(show.getShowId());
		Show updateShow=null;
		if(getUpdateShow.isPresent()) {
		updateShow=getUpdateShow.get();
			if(!show.getMovie().equals(null))
			updateShow.setMovie(show.getMovie());
			if(show.getScreenid()!=0)
			updateShow.setScreenid(show.getScreenid());
			if(show.getShowEndTime()!=null)
			updateShow.setShowEndTime(show.getShowEndTime());
			if(!show.getShowName().equals(null))
			updateShow.setShowName(show.getShowName());
			if(show.getShowStartTime()!=null)
			updateShow.setShowStartTime(show.getShowStartTime());
			if(show.getTheatreId()!=0)
			updateShow.setTheatreId(show.getTheatreId());
		}
		return updateShow;
	}
	
	public Show removeShow(Show show) {
		Optional<Show> findRemoveShow=showRepository.findById(show.getShowId());
		Show removeShow=null;
		if(findRemoveShow.isPresent()) {
		removeShow=findRemoveShow.get();
		showRepository.delete(removeShow);
		}
		return removeShow;
	}
	
	public Show viewShow(Show show) {
		Optional<Show> findRemoveShow=showRepository.findById(show.getShowId());
		return findRemoveShow.get();
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
