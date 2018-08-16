package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.FuelCard;

public interface FuelCardService {
	
	public List<FuelCard> getAllFuelCardList();
	
	public boolean saveFuelCard(FuelCard fuelCard);
	
	public List<FuelCard> findFuelCardByFcNo(String fc_no);
	
	public boolean updateFuelCard(FuelCard fuelCard);

}
