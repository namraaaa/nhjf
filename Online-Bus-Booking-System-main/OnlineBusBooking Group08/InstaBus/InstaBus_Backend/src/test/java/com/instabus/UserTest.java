package com.instabus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.instabus.dao.UserDao;
import com.instabus.entity.User;
import com.instabus.service.UserService;

@SpringBootTest
public class UserTest {
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserDao dao;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setUserName("tarun");
		user.setPhone(123456789l);
		user.setEmail("someone@example.com");
		user.setPassword("12345678");
		when(dao.save(user)).thenReturn(user);
		assertEquals(user, userService.addUser(user));
	}
		
}
