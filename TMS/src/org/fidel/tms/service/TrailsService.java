package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.TrailAvailabilityStatus;
import org.fidel.tms.model.TrailInformation;

public interface TrailsService {
	
	public List<TrailInformation> getActiveTrails();
	
	public List<TrailInformation> getAllTrails();
	
	public boolean saveTrailInformation(TrailInformation trail);
	
	public boolean updateTrailInformation(TrailInformation trail);
	
	public void insertTrucksAvailability(TrailAvailabilityStatus trail);

}
