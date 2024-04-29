package com.bms.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.Bus;
import com.bms.repository.BusRepository;

@Service
public class BusService {
	@Autowired
	BusRepository frepo;

	public List<Bus> getAllBusDetails() {
		return frepo.findAll();
	}

	public List<Bus> findMyBus(String src,String dest,Date d) {
		return frepo.findBySourceAndDestinationAndTraveldate(src,dest,d);
	}

	public Optional<Bus> getAllBusDetailsById(int userBusId) {
		return frepo.findById(userBusId);
	}

	public void busseatupdate(int seat,int busId) {
		frepo.updateSeat(seat,busId);
	}

}
