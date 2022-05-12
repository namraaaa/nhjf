package com.instabus.service;

import java.util.List;

import com.instabus.entity.Admin;
import com.instabus.entity.BusDetails;
import com.instabus.entity.Passenger;
import com.instabus.utils.AdminAuth;

public interface AdminService {
	public Admin addAdmin(Admin admin);

	public Admin getAdmin(Integer adminId);

	public void deleteAdmin(Integer adminId);

	public Admin adminLogin(AdminAuth auth);

	public List<BusDetails> getAllBusDetails();

	public BusDetails addBusDetails(BusDetails details);

	public void deleteBus(Integer busNumber);

	public BusDetails updateBus(BusDetails details);
	
	public List<Passenger> getAllPassengers();
	
	public List<Passenger> getPassengersByBooking(Integer id);

}
