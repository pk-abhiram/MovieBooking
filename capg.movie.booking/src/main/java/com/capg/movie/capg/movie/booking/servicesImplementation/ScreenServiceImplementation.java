package com.capg.movie.capg.movie.booking.servicesImplementation;


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
import com.capg.movie.capg.movie.booking.repository.TheatreRepository;
import com.capg.movie.capg.movie.booking.service.ScreenService;

@Service
public class ScreenServiceImplementation implements ScreenService {
	@Autowired
	ScreenRepository screenRepository;
	
	@Autowired
	ShowRepository showRepository;

	@Autowired
	TheatreRepository theatreRepository;
	
	public Screen addScreen(Screen screen) {
		Optional<Screen> screenFind=screenRepository.findById(screen.getScreenId());
		if(screen.getScreenId()==0 ||screenFind.isEmpty()) {
			if(theatreRepository.findById(screen.getTheatreId()).isPresent()) {
				screenRepository.save(screen);
				return screen;
			}
		}
		return null;
	}
	
	@Transactional
	public Screen updateScreen(Screen screen) {
		Optional<Screen> getUpdateScreen=screenRepository.findById(screen.getScreenId());
		System.out.println(getUpdateScreen.get());
		Screen updateScreen=new Screen();
		List<Show>shows=new ArrayList<Show>();
		System.out.println(screen.getShowList());
		
		if(getUpdateScreen.isPresent()) {
		updateScreen=getUpdateScreen.get();
		System.out.println(updateScreen);
		
		if(screen.getColumns()!=0)
		{updateScreen.setColumns(screen.getColumns());}
		
		if(screen.getRows()!=0)
		{updateScreen.setRows(screen.getRows());}
		
		if(screen.getScreenName()!=null)
		{updateScreen.setScreenName(screen.getScreenName());}
		
		if(screen.getShowList()!=null)
		{	shows.addAll(screen.getShowList());
			updateScreen.setShowList(shows);
		}
		
		if(screen.getTheatreId()!=0)
		{updateScreen.setTheatreId(screen.getTheatreId());}
		
		}
		return updateScreen;
	}
	
	public Screen removeScreen(Screen screen) {
		Optional<Screen> findRemoveScreen=screenRepository.findById(screen.getScreenId());
		Screen removeScreen=null;
		if(findRemoveScreen.isPresent()) {
		removeScreen=findRemoveScreen.get();
		screenRepository.delete(removeScreen);
		}
		return removeScreen;
	}
	
	public Screen viewScreenById(int screenId) {
		
		Optional<Screen> findRemoveScreen=screenRepository.findById(screenId);
		if(findRemoveScreen.isPresent()) {
			return findRemoveScreen.get();
		}
		return null;
	}
	public List<Screen>viewScreenListAll(){
		List<Screen>screens=screenRepository.findAll();
		return screens;
	}
	
	public List<Screen>viewScreenList(int theatreId){
		List<Screen>screens=screenRepository.findByTheatreId(theatreId);
		System.out.println(screens);
		return screens;
	}
	
	public Screen removeScreen(int screenId) {
		Optional<Screen> findRemoveScreen=screenRepository.findById(screenId);
		Screen removeScreen=null;
		if(findRemoveScreen.isPresent()) {
		removeScreen=findRemoveScreen.get();
		screenRepository.delete(removeScreen);
		}
		return removeScreen;
	}
}
