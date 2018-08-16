package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.DriversDao;
import org.fidel.tms.model.Drivers;
import org.fidel.tms.service.DriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriversServiceImpl implements DriversService {
	
	@Autowired
	DriversDao driversDao;

	@Override
	public List<Drivers> getAllDrivers() {
		
		return driversDao.getAllDrivers();
	}

	@Override
	public List<Drivers> getActiveDrivers() {
		
		return driversDao.getActiveDrivers();
	}

	@Override
	public boolean saveDrivers(Drivers drivers) {
		
		return driversDao.saveDrivers(drivers);
	}

	@Override
	public boolean updateDrivers(Drivers drivers) {
		
		return driversDao.updateDrivers(drivers);
	}

}
