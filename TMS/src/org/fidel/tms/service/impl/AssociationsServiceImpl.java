package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.AssociationsDao;
import org.fidel.tms.model.Associations;
import org.fidel.tms.service.AssociationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociationsServiceImpl implements AssociationsService {
	
	@Autowired
	AssociationsDao associationDao;

	@Override
	public List<Associations> getActiveAssociations() {
		
		return null;
	}

	@Override
	public List<Associations> getAllAssociations() {
		
		return associationDao.getAllAssociations();
	}

	@Override
	public boolean saveAssociations(Associations associaton) {
		
		return associationDao.saveAssociations(associaton);
	}

	@Override
	public boolean updateAssociations(Associations association) {
		
		return associationDao.updateAssociations(association);
	}
}
