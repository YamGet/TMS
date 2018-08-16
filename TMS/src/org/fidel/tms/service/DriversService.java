package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.Drivers;

public interface DriversService {
	
	public List<Drivers> getAllDrivers();
	
	public List<Drivers> getActiveDrivers();
	
	public boolean saveDrivers(Drivers drivers);
	
	public boolean updateDrivers(Drivers drivers);

}
