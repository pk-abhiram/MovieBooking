package com.capg.movie.capg.movie.booking.servicesImplementation;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.movie.capg.movie.booking.entities.Screen;
import com.capg.movie.capg.movie.booking.repository.ScreenRepository;
import com.capg.movie.capg.movie.booking.repository.ShowRepository;
import com.capg.movie.capg.movie.booking.service.ScreenService;

@Service
public class ScreenServiceImplementation implements ScreenService {
	@Autowired
	ScreenRepository screenRepository;
	
	@Autowired
	ShowRepository showRepository;

	public Screen addScreen(Screen screen) {
		screenRepository.save(screen);
		return screen;
	}
	
	@Transactional
	public Screen updateScreen(Screen screen) {
		Optional<Screen> getUpdateScreen=screenRepository.findById(screen.getScreenId());
		Screen updateScreen=null;
		if(getUpdateScreen.isPresent()) {
		updateScreen=getUpdateScreen.get();
		if(screen.getColumns()!=0)
		{updateScreen.setColumns(screen.getColumns());}
		if(screen.getRows()!=0)
		{updateScreen.setRows(screen.getRows());}
		if(screen.getScreenName()!=null)
		{updateScreen.setScreenName(screen.getScreenName());}
		if(screen.getScreenName()!=null)
		{updateScreen.setShowList(screen.getShowList());}
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
		return findRemoveScreen.get();
	}
	public List<Screen>viewScreenListAll(){
		List<Screen>screens=screenRepository.findAll();
		return screens;
	}
	
	public List<Screen>viewScreenList(int theatreId){
		List<Screen>screens=screenRepository.findByTheatreId(theatreId);
		return screens;
	}
	

}
