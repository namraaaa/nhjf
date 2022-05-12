package com.instabus.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instabus.entity.BookingDetails;



@Repository
public interface BookingDetailsDao extends JpaRepository<BookingDetails, Integer> {

}
