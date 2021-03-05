package com.capg.movie.capg.movie.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.movie.capg.movie.booking.entities.Screen;
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer>{

	public List<Screen> findByTheatreId(int theatreId);
}
