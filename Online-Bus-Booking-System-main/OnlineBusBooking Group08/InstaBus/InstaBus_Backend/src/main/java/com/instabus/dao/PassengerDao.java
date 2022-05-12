package com.instabus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instabus.entity.Passenger;

public interface PassengerDao extends JpaRepository<Passenger, Integer> {

}
