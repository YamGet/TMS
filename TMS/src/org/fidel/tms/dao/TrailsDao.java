package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.TrailAvailabilityStatus;
import org.fidel.tms.model.TrailInformation;

public interface TrailsDao {
	
	public List<TrailInformation> getActiveTrails();
	
	public List<TrailInformation> getAllTrails();
	
	public boolean saveTrailInformation(TrailInformation trail);
	
	public boolean updateTrailInformation(TrailInformation trail);
	
	public boolean checkTrailExistance(String plate_no);
	
	public boolean checkTrailExistance(String plate_no, int tli_id);
	
	public void insertTrailsAvailability(TrailAvailabilityStatus trail);
	
	public List<TrailInformation> getLastInsertedTrail(TrailAvailabilityStatus trail);

}
