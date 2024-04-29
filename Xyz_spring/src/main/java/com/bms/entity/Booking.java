package com.bms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;
	private String userName;
	private String userGender;
	private int userAge;
	private long userMobile;
	private int userSeat;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userBusId" ,referencedColumnName = "busId")
	private Bus bus;
	
	private double totalPrice;
	private String userPassword;
	
	
	public Booking() {
		super();
	}


	public Booking(int bookingId, String userName, String userGender, int userAge, long userMobile, int userSeat,
			Bus bus, double totalPrice, String userPassword) {
		super();
		this.bookingId = bookingId;
		this.userName = userName;
		this.userGender = userGender;
		this.userAge = userAge;
		this.userMobile = userMobile;
		this.userSeat = userSeat;
		this.bus = bus;
		this.totalPrice = totalPrice;
		this.userPassword = userPassword;
	}


	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserGender() {
		return userGender;
	}


	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}


	public int getUserAge() {
		return userAge;
	}


	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}


	public long getUserMobile() {
		return userMobile;
	}


	public void setUserMobile(long userMobile) {
		this.userMobile = userMobile;
	}


	public int getUserSeat() {
		return userSeat;
	}


	public void setUserSeat(int userSeat) {
		this.userSeat = userSeat;
	}


	public Bus getBus() {
		return bus;
	}


	public void setBus(Bus bus) {
		this.bus = bus;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	

}
