package com.instabus.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instabus.dao.AdminDao;
import com.instabus.dao.BookingDetailsDao;
import com.instabus.dao.BusDetailsDao;
import com.instabus.dao.PassengerDao;
import com.instabus.entity.Admin;
import com.instabus.entity.BookingDetails;
import com.instabus.entity.BusDetails;
import com.instabus.entity.Passenger;
import com.instabus.exception.AdminAlreadyExistException;
import com.instabus.exception.AdminDoesnotExistException;
import com.instabus.exception.BookingDoesNotFoundException;
import com.instabus.exception.BusDetailsNotFoundException;
import com.instabus.exception.NullAdminException;
import com.instabus.exception.NullBusDetailsException;
import com.instabus.exception.NullUserException;
import com.instabus.exception.UserDoesnotExistException;
import com.instabus.service.AdminService;
import com.instabus.utils.AdminAuth;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	@Autowired
	BusDetailsDao busDao;
	
	@Autowired
	PassengerDao passengerDao;
	
	@Autowired
	BookingDetailsDao bookingDao;

	
	@Override
	public Admin addAdmin(Admin admin) {
		if (admin == null)
			throw new NullAdminException("no data provided");
		Integer adminId = (int) ((Math.random() * 900) + 100);
		
		admin.setAdminId(adminId);
		Optional<Admin> checkAdmin = adminDao.findById(admin.getAdminId());
		if (checkAdmin.isPresent()) {
			throw new AdminAlreadyExistException("admin already exist exception");
		} else {
			adminDao.save(admin);
			System.out.println(adminId);
			return admin;
		}
	}

	@Override
	public Admin getAdmin(Integer adminId) {
		if (adminId == null)
			throw new NullAdminException("no data provided");
		Optional<Admin> admin = adminDao.findById(adminId);
		if (!admin.isPresent()) {
			throw new AdminDoesnotExistException("admin does not exist ");
		}
		return admin.get();
	}
	
	
	@Override
	public void deleteAdmin(Integer adminId) {
		if (adminId == null)
			throw new NullAdminException("no data provided");
		Optional<Admin> admin = adminDao.findById(adminId);
		if (!admin.isPresent()) {
			throw new AdminDoesnotExistException("admin Doesnot Exist Exception");
		}
		adminDao.deleteById(adminId);
	}

	@Override
	public Admin adminLogin(AdminAuth auth) {
		if (auth == null) {
			throw new NullAdminException("no data provided");
		}
		Optional<Admin> admin = adminDao.findById(auth.getAdminId());
		if (admin.isPresent()) {
			if (admin.get().getAdminId() == auth.getAdminId() && admin.get().getPassword().equals(auth.getPassword())) {
				return admin.get();
			} else {
				throw new AdminDoesnotExistException("invalid login id or password");
			}
			
		} else
			throw new AdminDoesnotExistException("admin doesnot exist");
	}

	@Override
	public List<BusDetails> getAllBusDetails() {
		return busDao.findAll();
	}

	@Override
	public BusDetails addBusDetails(BusDetails details) {
		if (details == null) {
			throw new NullBusDetailsException("no data provided");
		}
		Integer busNumber = (int) ((Math.random() * 9000) + 1000);
		details.setBusNumber(busNumber);
		busDao.save(details);
		return details;
	}

	@Override
	public void deleteBus(Integer busNumber) {
		if (busNumber == null)
			throw new NullBusDetailsException("No data recieved..");
		Optional<BusDetails> details = busDao.findById(busNumber);
		if (!details.isPresent()) {
			throw new BusDetailsNotFoundException("Bus Details not found");
		}
		busDao.deleteById(busNumber);
	}

	@Override
	public BusDetails updateBus(BusDetails details) {
		if (details == null)
			throw new NullBusDetailsException("No data recieved..");
		Optional<BusDetails> busDetails = busDao.findById(details.getBusNumber());
		if (!busDetails.isPresent()) {
			throw new BusDetailsNotFoundException("Bus with busNumber: " + details.getBusNumber() + " not exists..");
		}
		busDao.save(details);
		return details;
	}
	
	public List<Passenger> getAllPassengers(){
		return passengerDao.findAll();
	}
	
	public List<Passenger> getPassengersByBooking(Integer id){
		if (id == null) throw new BookingDoesNotFoundException("no data provided");
		Optional<BookingDetails> details = bookingDao.findById(id);
		if (!details.isPresent())
			throw new BookingDoesNotFoundException("booking not found");
		return details.get().getPassengers();
	}

}
