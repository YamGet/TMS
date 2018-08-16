package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.Associations;

public interface AssociationsService {
	
	public List<Associations> getActiveAssociations();
	
	public List<Associations> getAllAssociations();
	
	public boolean saveAssociations(Associations associaton);
	
	public boolean updateAssociations(Associations association);

}
