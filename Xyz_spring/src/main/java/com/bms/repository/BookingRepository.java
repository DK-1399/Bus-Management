package com.bms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bms.entity.Booking;

@Repository
@Transactional
public interface  BookingRepository extends JpaRepository<Booking,Integer>{

	public List<Booking> findByUserMobileAndUserPassword(long userMobile, String userPassword);

	@Modifying
	@Query("DELETE FROM Booking b WHERE b.bookingId = :bid AND b.userMobile = :umob AND b.userPassword = :upass")
	public void deleteBookings(int bid, long umob, String upass);
	

}
