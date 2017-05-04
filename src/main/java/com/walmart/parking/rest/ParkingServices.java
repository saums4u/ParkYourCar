/**
 * 
 */
package com.walmart.parking.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.walmart.parking.transaction.ParkingBO;
import com.walmart.parking.transaction.impl.ParkingBOImpl;
import com.walmart.parking.transaction.impl.Slot;
import org.springframework.stereotype.Component;

/**Tax Automation 4.0.0
 * @author Saumya Ranjan Sahu
 * @date: Mar 29, 2017
 */

@Component
@Path("/slots")
public class ParkingServices{

	
	@Autowired
	ParkingBO parkingBo;

	// GET parking/api/slots
	// return all the slots
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Slot> getSlots(){
		
		return parkingBo.getAllSlots();
	}

	// POST parking/api/slots/create
	// create a slot - when someone drops the parking ticket and mentions the slot when it will be available
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Slot createSlot(Slot s){
		
		if(s == null){
			return parkingBo.createSlot();
		}else {
			return parkingBo.addSlot(s);
		}
	}
	
	// POST parking/api/slots/book
	// book a slot - when someone requests for a slot and gets a booking based on the availability
	@POST
	@Path("book")
	@Produces(MediaType.APPLICATION_JSON)
	public Slot reserveSlot(Slot slot){
		return parkingBo.bookSlot(slot);
	}
	
	// GET parking/api/slots/1/
	// return the particular slot as per the id
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Slot getSlots(@PathParam("id") String slotId){
		return parkingBo.getSlot(slotId);
	}
	
}
