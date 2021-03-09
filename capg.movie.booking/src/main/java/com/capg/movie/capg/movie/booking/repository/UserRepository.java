package com.capg.movie.capg.movie.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.movie.capg.movie.booking.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
