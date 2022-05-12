package com.instabus;




import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.instabus.entity.Admin;
import com.instabus.serviceImpl.AdminServiceImpl;



@SpringBootApplication
public class InstaBusApplication {
	
	@Autowired
	AdminServiceImpl adminService;
	
	@PostConstruct
	public void initAdmin() {
		Admin admin = new Admin();
		admin.setAdminName("Namratha");
		admin.setPassword("36783455");
		adminService.addAdmin(admin);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InstaBusApplication.class, args);
	}

}
