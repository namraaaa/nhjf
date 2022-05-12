package com.instabus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.instabus.entity.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer> {

}
