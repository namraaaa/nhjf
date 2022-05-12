package com.instabus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instabus.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

}
