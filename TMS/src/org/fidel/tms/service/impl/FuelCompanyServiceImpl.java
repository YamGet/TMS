package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.FuelCompanyDao;
import org.fidel.tms.model.FuelCompany;
import org.fidel.tms.service.FuelCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelCompanyServiceImpl implements FuelCompanyService {

	@Autowired
	private FuelCompanyDao fuelCompanyDao;
	
	@Override
	public List<FuelCompany> getAllFuelCompanyList() {
		
		return fuelCompanyDao.getAllFuelCompanyList();
	}

	@Override
	public boolean saveFuelCompany(FuelCompany fuelCompany) {
		
		return fuelCompanyDao.saveFuelCompany(fuelCompany);
	}

	@Override
	public List<FuelCompany> getFuelComponyByFc_id(int fc_id) {
		
		return fuelCompanyDao.getFuelComponyByFc_id(fc_id);
	}

	@Override
	public boolean updateFuelCompany(FuelCompany fuelCompany) {
		
		return fuelCompanyDao.updateFuelCompany(fuelCompany);
	}

	@Override
	public List<FuelCompany> getActiveFuelCompanyList() {
		
		return fuelCompanyDao.getActiveFuelCompanyList();
	}

}
