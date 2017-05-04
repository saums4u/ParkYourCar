/**
 * 
 */
package com.walmart.parking.transaction.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**Tax Automation 4.0.0
 * @author Saumya Ranjan Sahu
 * @date: Mar 29, 2017
 */

@XmlRootElement
@JsonInclude(Include.NON_EMPTY)
public class Slot {

	private int slotId;
	private String startTime;
	private String endTime;
	private boolean isAvailable;

	public Slot(){
		Random r = new Random();
		slotId = r.nextInt();
		startTime = getTime( System.currentTimeMillis());
		
		// 10mins slot
		endTime = getTime ( System.currentTimeMillis() + 600000);
		isAvailable = true;
	}
	
	private static String getTime(long timeinmillis){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timeinmillis);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(c.getTime());
	}
	
	public String getSlotId(){
		return Integer.toString(slotId);
	}

	public String getStartTime(){
		return startTime;
	}

	public String getEndTime(){
		return endTime;
	}
	
	public boolean getIsAvailable(){
		return isAvailable;
	}
	
	public void setIsAvailable(boolean avl){
		isAvailable = avl;
	}

	public void setSlotId(String sid){
		
		slotId = Integer.parseInt(sid);
	}

	public void setStartTime(String stime){
		startTime = stime;
	}

	public void setEndTime(String etime){
		endTime = etime;
	}
	
}
