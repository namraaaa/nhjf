package com.instabus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instabus.entity.Admin;
import com.instabus.entity.BusDetails;
import com.instabus.entity.Passenger;
import com.instabus.serviceImpl.AdminServiceImpl;
import com.instabus.utils.AdminAuth;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminServiceImpl service;
	
	
	
	@PostMapping("/adminLogin")
	public ResponseEntity<Admin> loginAdmin(@RequestBody AdminAuth auth){
		Admin admin = service.adminLogin(auth);
		return ResponseEntity.ok(admin);
	}
	
	@GetMapping("/getAdmin/{id}")
	public ResponseEntity<Admin> getAdmin(@PathVariable Integer id){
		Admin admin = service.getAdmin(id);
		return ResponseEntity.ok(admin);
	}
	
	@DeleteMapping("/deleteAdmin/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id){
		service.deleteAdmin(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/getAllBusDetails")
	public ResponseEntity<List<BusDetails>> getAllBusDetails(){
		List<BusDetails> list = service.getAllBusDetails();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping("/addBusDetails")
	public ResponseEntity<BusDetails> addBus(@RequestBody BusDetails busDetails){
		BusDetails details = service.addBusDetails(busDetails);
		return ResponseEntity.ok().body(details);
	}
	
	@DeleteMapping("/deleteBusDetails/{busNumber}")
	public void deleteBus(@PathVariable Integer busNumber) {
		service.deleteBus(busNumber);
	}
	
	@PostMapping("/updateBusDetails")
	public ResponseEntity<BusDetails> updateBus(@RequestBody BusDetails busDetails){
		BusDetails details = service.updateBus(busDetails);
		return ResponseEntity.ok().body(details);
	}
	
	@GetMapping("/getAllPassengers")
	public ResponseEntity<List<Passenger>> getAllPassengers(){
		List<Passenger> passengers = service.getAllPassengers();
		return ResponseEntity.ok().body(passengers);
	}
	
	@GetMapping("/getPassengerByBooking/{id}")
	public ResponseEntity<List<Passenger>> getPassengerByBooking(@PathVariable Integer id){
		List<Passenger> passengers = service.getPassengersByBooking(id);
		return ResponseEntity.ok().body(passengers);
	}
	
	@PostMapping("/addAdmin")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
		Admin addedAdmin = service.addAdmin(admin);
		return ResponseEntity.ok(addedAdmin);
	}
		
}
