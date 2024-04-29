package com.bms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bms.entity.Booking;
import com.bms.entity.Bus;
import com.bms.service.BookingService;
import com.bms.service.BusService;

@Controller
public class BusController {
	
	@Autowired
	BusService fservice;
	@Autowired
	BookingService bservice;
	
	

	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/buses")
	public String buses(Model model) {
		List<Bus> busDetails = fservice.getAllBusDetails();
		model.addAttribute("fd", busDetails);
		return "buses";
	}
	
	@GetMapping("/search")
	public String search(Model model) {
		return "search";
	}
	
	@GetMapping("/bookbus")
	public String booking(Model model) {
		model.addAttribute("idmango",true); 
		return "bookbus";
	}
	@GetMapping("/bookbus/{fid}")
	public String bookingwithid(Model model,@PathVariable("fid") String fid) {
	
			model.addAttribute("message",null);
			int busid = Integer.parseInt(fid);
			model.addAttribute("idmango",false);
			model.addAttribute("fid",busid);
		
		return "bookbus";
	}
	
	@GetMapping("/ticket")
	public String tickets(Model model) {
		return "ticket";
	}
	
	@GetMapping("/deletebooking/{combination}")
	public String cancelTicket(RedirectAttributes attri,@PathVariable("combination") String combination) {
		String[] arr = combination.split("-");
		int bid = Integer.parseInt(arr[0]);
		long umob = Long.parseLong(arr[1]);
		String upass = arr[2];
		int fid = Integer.parseInt(arr[3]);
		int seat = Integer.parseInt(arr[4]);
			
		//update flight seat
		Optional<Bus> opf = fservice.getAllBusDetailsById(fid);
		Bus f = opf.get();
		int updateSeat = f.getSeats() + seat;
		fservice.busseatupdate(updateSeat, fid);
		//delete booking
		bservice.deleteTicket(bid,umob,upass);
		attri.addFlashAttribute("message","Ticket Cancelled ");
		return "redirect:/ticket";
	}
	
	@PostMapping("/searchdata")
	public String searchdata(@ModelAttribute Bus obj,RedirectAttributes attributes) {
		List<Bus> searchedBuses = fservice.findMyBus(obj.getSource(),obj.getDestination(),obj.getTraveldate());
		attributes.addFlashAttribute("searchResult", searchedBuses);
		return "redirect:/search";
	}
	
	@PostMapping("/savebooking")
	public String makeBooking(@ModelAttribute Booking bobj,@Param("userBusId") String userBusId,RedirectAttributes reAttri) {
		//Check for usermobile and password combination
		List<Booking> checkUser = bservice.findmyuser(bobj.getUserMobile(),bobj.getUserPassword());
		if(checkUser.size()>0) {
			reAttri.addFlashAttribute("message","Change Your Number");
		}else {
			//First - Finding the flight with appropriate flightID
			Optional<Bus> fdet = fservice.getAllBusDetailsById(Integer.parseInt(userBusId));
			
			
			//If Bus is Not Found
			if(fdet.isEmpty()) {
				reAttri.addFlashAttribute("message", "No Such Bus Exists.");
			}else {
				//Check For Seat Availability
				if(fdet.get().getSeats() < bobj.getUserSeat()) {
					reAttri.addFlashAttribute("message", "Booking Seat Is More Than Availale Seats.");
				}else {
					//Calculate Total Price  and Set to the BookingObject
					double tp = bobj.getUserSeat() * fdet.get().getPrice();
					bobj.setTotalPrice(tp);
					
					//bobj.setFlightId(Integer.parseInt(userFlightId));
					bobj.setBus(fdet.get());
					
					//Save The Booking Object
					bservice.savebooking(bobj);
					
					//Update The Flight Availability
					int updatedSeat = fdet.get().getSeats() - bobj.getUserSeat();
					fservice.busseatupdate(updatedSeat,fdet.get().getBusId());
					
					reAttri.addFlashAttribute("message", "Booked Successfully 👍");
				}
			}
		}
		
		return "redirect:/bookbus";
	}
	
	@PostMapping("/finduser")
	public String findUser(@ModelAttribute Booking bobj,RedirectAttributes reti) {
		List<Booking> res = bservice.findmyuser(bobj.getUserMobile(),bobj.getUserPassword());
	
		if(res.size() < 1) {
			reti.addFlashAttribute("message","Invalid Credentials");
		}else {
			reti.addFlashAttribute("ticketData",res);
		}
		
		return "redirect:/ticket";
	}
	
	

}
