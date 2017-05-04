/**
 * 
 */
package com.walmart.parking.client;

/**Tax Automation 4.0.0
 * @author Saumya Ranjan Sahu
 * @date: Mar 30, 2017
 */

import java.net.UnknownHostException;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.walmart.parking.transaction.impl.Slot;

public class DBTestClient {

	public static void main(String[] args) throws UnknownHostException {
	
		Slot s = new Slot();
		DBObject doc = createDBObject(s);
		
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("parking");
		
		DBCollection col = db.getCollection("slots");
		
		//create slot
		WriteResult result = col.insert(doc);
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());

		
		//read slot
		DBObject query = BasicDBObjectBuilder.start().add("slotId", s.getSlotId()).get();
		DBCursor cursor = col.find(query);
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		
		//update slot
		s.setEndTime("4.30PM");
		s.setStartTime("5.10PM");
		s.setIsAvailable(true);
		s.setSlotId("69");
		
		
		doc = createDBObject(s);
		result = col.update(query, doc);
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());
		
		//delete slot
		result = col.remove(query);
		System.out.println(result.getUpsertedId());
		System.out.println(result.getN());
		System.out.println(result.isUpdateOfExisting());

		
		//close resources
		mongo.close();
	}

	private static DBObject createDBObject(Slot s) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		docBuilder.append("slotId", s.getSlotId());
		docBuilder.append("EndTime", s.getEndTime());
		docBuilder.append("StartTime", s.getStartTime());
		docBuilder.append("isAvailable", s.getIsAvailable());
		return docBuilder.get();
	}

	private static Slot createSlot() {
		Slot s = new Slot();
		return s;
	}

}
