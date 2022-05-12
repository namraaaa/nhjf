package com.instabus;

import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.instabus.dao.BusDetailsDao;
import com.instabus.dao.BusDetailsDao;
import com.instabus.entity.BusDetails;
import com.instabus.service.AdminService;



@SpringBootTest
public class AddBusDetailsTest {

	
	@Autowired
	private AdminService busService;
	
	@MockBean
	private BusDetailsDao repository;
	
	/*
	 * Testing getAllBusDetails()
	 */
	@Test
	public void testGetBusDetails() {
		when(repository.findAll()).thenReturn(Stream
				.of(new BusDetails("delhi", "mumbai", 25, "16-04-2022", "16-04-2022", 
						"23:00", "17:00", "prasanna", 7899.00)).collect(Collectors.toList()));
		assertEquals(1, busService.getAllBusDetails().size());
	}
	
	
	@Test
	public void testDisplayAllBusDetails() {
		when(repository.findAll()).thenReturn(Stream
				.of(new BusDetails("delhi", "mumbai", 25, "17-04-2022", "17-04-2022", "23:00", 
						"17:00", "prasanna", 7899.00), 
						new BusDetails("bangalore", "kolkata", 48, "02-12-2020", "03-12-2020", 
								"05:20", "23:05", "prasanna", 12899.00)).collect(Collectors.toList()));
		assertEquals(2, busService.getAllBusDetails().size());
	}
	
	

	@Test
	public void testAddBusDetails() {
		
		BusDetails busObj = new BusDetails("pune", "delhi", 56, "18-04-2022", "18-04-2022", 
				"05:00", "23:55", "prasanna", 9899.55);
		when(repository.save(busObj)).thenReturn(busObj);
		assertEquals(busObj, busService.addBusDetails(busObj));
	}
}
