package com.bms.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int busId;
	private String busName;
	private String source;
	private String destination;
	private Date traveldate;
	private String departure;
	private String arrival;
	private int seats;
	private double price;
	
	@OneToOne(mappedBy="bus")
	private Booking booking;

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bus(int busId, String busName, String source, String destination, Date traveldate, String departure,
			String arrival, int seats, double price, Booking booking) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.source = source;
		this.destination = destination;
		this.traveldate = traveldate;
		this.departure = departure;
		this.arrival = arrival;
		this.seats = seats;
		this.price = price;
		this.booking = booking;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName =busName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getTraveldate() {
		return traveldate;
	}

	public void setTraveldate(Date traveldate) {
		this.traveldate = traveldate;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
}
