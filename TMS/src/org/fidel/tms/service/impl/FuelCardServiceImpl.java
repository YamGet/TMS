package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.FuelCardDao;
import org.fidel.tms.model.FuelCard;
import org.fidel.tms.service.FuelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelCardServiceImpl implements FuelCardService {
	
	@Autowired
	FuelCardDao fuelCardDao;

	@Override
	public List<FuelCard> getAllFuelCardList() {
		
		return fuelCardDao.getAllFuelCardList();
	}

	@Override
	public boolean saveFuelCard(FuelCard fuelCard) {
		
		return fuelCardDao.saveFuelCard(fuelCard);
	}

	@Override
	public List<FuelCard> findFuelCardByFcNo(String fc_no) {
		
		return fuelCardDao.findFuelCardByFcNo(fc_no);
	}

	@Override
	public boolean updateFuelCard(FuelCard fuelCard) {
		
		return fuelCardDao.updateFuelCard(fuelCard);
	}

}
