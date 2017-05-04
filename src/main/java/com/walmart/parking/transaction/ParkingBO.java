/**
 * 
 */
package com.walmart.parking.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.walmart.parking.transaction.impl.ParkingBOImpl;
import com.walmart.parking.transaction.impl.Slot;

/**Tax Automation 4.0.0
 * @author Saumya Ranjan Sahu
 * @date: Mar 29, 2017
 */

public interface ParkingBO{

	List<Slot> getAllSlots();
	Slot addSlot(Slot s);
	Slot createSlot();
	Slot bookSlot(Slot s);
	Slot getSlot(String slotId);
}
