package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.Drivers;

public interface DriversDao {
	
	public List<Drivers> getAllDrivers();
	
	public List<Drivers> getActiveDrivers();
	
	public boolean saveDrivers(Drivers drivers);
	
	public boolean updateDrivers(Drivers drivers);
	
	public boolean checkDrivers(String driving_license_no);
	
	public boolean checkDrivers(String driving_license_no, int dr_id);

}
