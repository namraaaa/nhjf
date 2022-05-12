package com.instabus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.instabus.entity.BusDetails;

@Repository
public interface BusDetailsDao extends JpaRepository<BusDetails, Integer> {

	@Query("select f from BusDetails f where f.arrivalBusstop = ?1 and f.departureBusstop = ?2")
	public List<BusDetails> findByRouteDate(String sourceBusstop,String destinationBusstop);
	
}
