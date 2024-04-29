package com.bms.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bms.entity.Bus;

@Repository
@Transactional
public interface BusRepository extends JpaRepository <Bus,Integer>{


	@Modifying
	@Query("UPDATE Bus f SET f.seats = :seat WHERE f.busId = :busId")
	public void updateSeat(int seat,int busId);

	public List<Bus> findBySourceAndDestinationAndTraveldate(String src, String dest, Date d);
	

}
