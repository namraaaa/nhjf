package com.instabus.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instabus.dao.BookingDetailsDao;
import com.instabus.dao.BusDetailsDao;
import com.instabus.dao.PassengerDao;
import com.instabus.dao.UserDao;
import com.instabus.entity.BookingDetails;
import com.instabus.entity.BusDetails;
import com.instabus.entity.Passenger;
import com.instabus.entity.User;
import com.instabus.exception.BusDetailsNotFoundException;
import com.instabus.exception.NullBusDetailsException;
import com.instabus.exception.NullUserException;
import com.instabus.exception.PassengerNotFoundException;
import com.instabus.exception.UserAlreadyExistException;
import com.instabus.exception.UserDoesnotExistException;
import com.instabus.service.UserService;
import com.instabus.utils.UserAuth;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;

	@Autowired
	BusDetailsDao busDao;

	@Autowired
	BookingDetailsDao bookingDao;
	
	@Autowired
	PassengerDao passengerDao;

	@Override
	public User addUser(User user) {

		if (user == null)
			throw new NullUserException("No data received");
		Integer userId = (int) ((Math.random() * 900) + 100);
		user.setUserId(userId);
		Optional<User> checkUser = userDao.findById(user.getUserId());
		if (checkUser.isPresent())
			throw new UserAlreadyExistException("user already exists");

		userDao.save(user);
		System.out.println("user Added");
		return user;

	}

	
	@Override
	public void updateUser(User user) {
		if (user == null)
			throw new NullUserException("No data received");
		Optional<User> checkUser = userDao.findById(user.getUserId());
		if (checkUser.isPresent())
			userDao.save(user);
		else
			throw new UserDoesnotExistException("User not found");

	}

	
	@Override
	public User getUser(Integer userId) {
		if (userId == null)
			throw new NullUserException("No data received");
		Optional<User> user = userDao.findById(userId);
		if (!user.isPresent())
			throw new UserDoesnotExistException("User not found");
		return user.get();
	}

	
	@Override
	public void deleteUser(Integer userId) {
		if (userId == null)
			throw new NullUserException("No data received");
		Optional<User> user = userDao.findById(userId);
		if (!user.isPresent())
			throw new UserDoesnotExistException("User not found");
		userDao.deleteById(userId);
	}

	
	@Override
	public User userLogin(UserAuth auth) {
		if (auth == null) {
			throw new NullUserException("No data received");
		}
		Optional<User> user = userDao.findById(auth.getUserId());
		if (user.isPresent()) {
			if (user.get().getUserId() == auth.getUserId() && user.get().getPassword().equals(auth.getPassword())) {
				return user.get();
			} else {
				throw new UserDoesnotExistException("invalid login id or password");
			}
			
		} else {
			throw new UserDoesnotExistException("User not found");
		}
	}

	@Override
	public BookingDetails addBooking(BookingDetails booking, Integer userId, Integer busNumber) {
		Optional<User> user = userDao.findById(userId);
		Optional<BusDetails> bus = busDao.findById(busNumber);
		if (!user.isPresent()) {
			throw new UserDoesnotExistException("user id not found");
		}
		if (!bus.isPresent()) {
			throw new BusDetailsNotFoundException("bus details not found");
		}
		Integer bookingId = (int) ((Math.random() * 9000) + 1000);
		booking.setBookingId(bookingId);
		booking.setOwnerId(userId);
		booking.setBusNumber(busNumber);
		booking.setBookingDate(LocalDate.now().toString());
		booking.setBookingTime(LocalTime.now().toString().substring(0, 5));
		booking.setTotalCost(bus.get().getCost() * booking.getPassengers().size());
		List<BookingDetails> bookingList = user.get().getBookingDetails();
		bookingList.add(booking);
		user.get().setBookingDetails(bookingList);
		updateUser(user.get());
		return bookingDao.getOne(bookingId);
	}

	@Override
	public void deleteBooking(Integer bookingId, Integer userId) {
		Optional<User> u = userDao.findById(userId);
		Optional<BookingDetails> bd = bookingDao.findById(bookingId);
		if (!bd.isPresent()) {
			throw new UserDoesnotExistException("booking not found");
		}
		if (!u.isPresent()) {
			throw new UserDoesnotExistException("user id not found");
		}
		User user = u.get();
		List<BookingDetails> bookingList = user.getBookingDetails();
		BookingDetails deleteBooking = null;
		for (BookingDetails b : bookingList) {
			if (b.getBookingId() == bookingId) {
				System.out.println("booking id found");
				deleteBooking = b;
			}
		}
		bookingList.remove(deleteBooking);
		user.setBookingDetails(bookingList);
		bookingDao.deleteById(bookingId);
		updateUser(user);
	}

	@Override
	public List<BookingDetails> getBookingByUserId(Integer userId) {
		Optional<User> user = userDao.findById(userId);
		if (!user.isPresent()) {
			throw new UserDoesnotExistException("user id not found");
		}
		return user.get().getBookingDetails();
	}

	@Override
	public BusDetails findByRouteAndDate(String arrivalAirport, String departureAirport, String date) {
		List<BusDetails> list = busDao.findByRouteDate(arrivalAirport.toLowerCase(),
				departureAirport.toLowerCase());
		for (BusDetails f : list) {
			if (f.getDepartureDate().equals(date)) {
				return f;
			}
		}
		throw new BusDetailsNotFoundException("details not found");
	}
	
	
	@Override
	public BusDetails getBusByBusNumber(Integer busNumber) {
		if (busNumber == null) {
			throw new NullBusDetailsException("no data privided");
		}
		Optional<BusDetails> details = busDao.findById(busNumber);
		if (!details.isPresent()) {
			throw new BusDetailsNotFoundException("no bus found for given number");
		}
		return details.get();
	}
	
	@Override
	public Passenger updatePassenger(Passenger passenger) {
		if (passenger == null) {
			throw new PassengerNotFoundException("no data provided");
		}
		Optional<Passenger> oldPassenger = passengerDao.findById(passenger.getPassengerId()); 
		if (!oldPassenger.isPresent()) {
			throw new PassengerNotFoundException("passenger not found");
		}
		passengerDao.save(passenger);
		return passenger;
	}

}
