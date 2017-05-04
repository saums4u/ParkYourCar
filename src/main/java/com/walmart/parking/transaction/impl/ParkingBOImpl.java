/**
 * 
 */
package com.walmart.parking.transaction.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.walmart.parking.transaction.ParkingBO;

/**Tax Automation 4.0.0
 * @author Saumya Ranjan Sahu
 * @date: Mar 29, 2017
 */
public class ParkingBOImpl implements ParkingBO {

	private List<Slot> slotData;
	MongoClient mongo;
	DBCollection collection;
	
	public ParkingBOImpl(){
		
		mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("parking");
		collection = db.getCollection("slots");

		slotData = new ArrayList<Slot>();
	}
	
	
	public List<Slot> getAllSlots(){
		slotData = dbReadAll();
		return slotData;
	}
	
	private void dbInsert(Slot s){

		// db insert
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("slotId", s.getSlotId());
		docBuilder.append("EndTime", s.getEndTime());
		docBuilder.append("StartTime", s.getStartTime());
		docBuilder.append("isAvailable", true);

		WriteResult result = collection.insert(docBuilder.get());
		
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());

	}
	

	private void dbUpdate(Slot s){

		// db insert
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("slotId", s.getSlotId());
		docBuilder.append("EndTime", s.getEndTime());
		docBuilder.append("StartTime", s.getStartTime());
		docBuilder.append("isAvailable", false);
		DBObject query = BasicDBObjectBuilder.start().add("slotId", s.getSlotId()).get();
		WriteResult result = collection.update(query, docBuilder.get());
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());
	}

	private List<Slot> dbReadAll(){

		// db read all
		
		DBCursor cursor = collection.find();
		List<Slot>allSlots = new ArrayList<Slot>();
		while(cursor.hasNext()){
		
			DBObject obj = cursor.next(); 
		    Gson gson = new Gson();
		    Slot s1 = gson.fromJson(obj.toString(), Slot.class);
		    allSlots.add(s1);
			System.out.println(obj);

		}
		return allSlots;
	}

	
	// add the slot to the list
	public Slot addSlot(Slot s){

		try {
			dbInsert(s);
			return s;
		} catch( Exception e){
			e.printStackTrace();
			return null;
		}
	}

	// create slot
	public Slot createSlot(){
		Slot s = new Slot();
		dbInsert(s);
		System.out.println("slot created");
		return s;
	}
	
	// book the slot provided
	public Slot bookSlot(Slot s){
		
		Slot gs = getSlot(s.getSlotId());
		if(null != gs){
			gs.setIsAvailable(false);
			
			// db update
			try {
				dbUpdate(s);
			}catch ( Exception e){
				e.printStackTrace();
				return null;
			}
			return gs;
		}
		return null;
	}

	// get the slot matching to slotId
	public Slot getSlot(String slotId) {

		slotData = dbReadAll();
		System.out.println("SlotData:");
		System.out.println(slotData);
		System.out.println("SlotId: "+slotId);
		for(Slot s: slotData){
			if(s.getSlotId().equals(slotId)){
				return s;
			}
		}
		return null;
	}
}
