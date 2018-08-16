package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.FrightOrderDao;
import org.fidel.tms.dao.TrucksDao;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.TruckAvailabilityStatus;
import org.fidel.tms.model.TruckInformation;
import org.fidel.tms.service.TrucksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrucksServiceImpl implements TrucksService {
	
	@Autowired
	TrucksDao trucksDao;
	@Autowired
	FrightOrderDao frightOrderDao;

	@Override
	public List<TruckInformation> getActiveTrucks() {
		
		return trucksDao.getActiveTrucks();
	}

	@Override
	public List<TruckInformation> getAllTrucks() {
		
		return trucksDao.getAllTrucks();
	}

	@Override
	public List<TruckInformation> getTrucksListByTruckType(String truck_type) {
		
		return trucksDao.getTrucksListByTruckType(truck_type);
	}

	@Override
	public boolean saveTrucks(TruckInformation trucks) {
		
		return trucksDao.saveTrucks(trucks);
	}

	@Override
	public boolean updateTrucks(TruckInformation trucks) {
		
		return trucksDao.updateTrucks(trucks);
	}

	@Override
	public void insertTrucksAvailability(TruckAvailabilityStatus trucksAvaStatus) {
		
		List<TruckInformation> truckList = trucksDao.getLastTruckInserted();
		
		for(int i = 0; i < truckList.size(); i++){
			
			trucksAvaStatus.setTri_id(truckList.get(i).getTri_id());
		}
		
		trucksDao.insertTrucksAvailability(trucksAvaStatus);
	}

}
