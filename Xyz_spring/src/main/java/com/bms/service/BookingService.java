package com.bms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.Booking;
import com.bms.entity.Bus;
import com.bms.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	BookingRepository brepo;
	
	 public void savebooking(Booking bobj) {
		 brepo.save(bobj);
		 
	 }

	public List<Booking> findmyuser(long userMobile, String userPassword) {
		return brepo.findByUserMobileAndUserPassword(userMobile,userPassword);
	}

	public void deleteTicket(int bid, long umob, String upass) {
		brepo.deleteBookings(bid,umob,upass);
	}

}
