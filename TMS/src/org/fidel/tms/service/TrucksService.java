package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.TruckAvailabilityStatus;
import org.fidel.tms.model.TruckInformation;

public interface TrucksService {

	public List<TruckInformation> getActiveTrucks();
	
	public List<TruckInformation> getAllTrucks();
	
	public List<TruckInformation> getTrucksListByTruckType(String truck_type);
	
	public boolean saveTrucks(TruckInformation trucks);
	
	public boolean updateTrucks(TruckInformation trucks);
	
	public void insertTrucksAvailability(TruckAvailabilityStatus trucksAvaStatus);
	
}
