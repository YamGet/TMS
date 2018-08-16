package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.FuelCompany;

public interface FuelCompanyDao {

	public List<FuelCompany> getAllFuelCompanyList();
	
	public boolean saveFuelCompany(FuelCompany fuelCompany);
	
	public List<FuelCompany> getFuelComponyByFc_id(int fc_id);
	
	public boolean updateFuelCompany(FuelCompany fuelCompany);
	
	public List<FuelCompany> getActiveFuelCompanyList();
}
