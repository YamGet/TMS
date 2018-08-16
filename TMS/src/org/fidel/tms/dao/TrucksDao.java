package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.TruckAvailabilityStatus;
import org.fidel.tms.model.TruckInformation;

public interface TrucksDao {
	
	public List<TruckInformation> getActiveTrucks();
	
	public List<TruckInformation> getAllTrucks();

	public List<TruckInformation> getTrucksListByTruckType(String truck_type);
	
	public boolean saveTrucks(TruckInformation trucks);
	
	public boolean updateTrucks(TruckInformation trucks);
	
	public boolean checkTruckExistance(String shanci_no, String truck_plate_no);
	
	public boolean checkTruckExistance(String shanci_no, String truck_plate_no, int tri_id);
	
	public void insertTrucksAvailability(TruckAvailabilityStatus trucksAvaStatus);
	
	public List<TruckInformation> getLastTruckInserted();

}
