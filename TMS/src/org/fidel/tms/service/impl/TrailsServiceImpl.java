package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.TrailsDao;
import org.fidel.tms.model.TrailAvailabilityStatus;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.service.TrailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrailsServiceImpl implements TrailsService {
	
	@Autowired
	TrailsDao trailsDao;

	@Override
	public List<TrailInformation> getActiveTrails() {
		
		return trailsDao.getActiveTrails();
	}

	@Override
	public List<TrailInformation> getAllTrails() {
		
		return trailsDao.getAllTrails();
	}

	@Override
	public boolean saveTrailInformation(TrailInformation trail) {
		
		return trailsDao.saveTrailInformation(trail);
	}

	@Override
	public boolean updateTrailInformation(TrailInformation trail) {
		
		return trailsDao.updateTrailInformation(trail);
	}

	@Override
	public void insertTrucksAvailability(TrailAvailabilityStatus trail) {
		
		List<TrailInformation> ti = trailsDao.getLastInsertedTrail(trail);
		
		for(int i = 0; i < ti.size(); i++){
			trail.setTli_id(ti.get(i).getTli_id());
		}
		
		trailsDao.insertTrailsAvailability(trail);
		
	}

}
