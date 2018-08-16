package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.Associations;

public interface AssociationsDao {
	
	public List<Associations> getActiveAssociations();
	
	public List<Associations> getAllAssociations();
	
	public boolean saveAssociations(Associations associaton);
	
	public boolean updateAssociations(Associations association);
	
	public boolean checkAssociationExistance(String association_name);
	
	public boolean checkAssociationExistance(String association_name, int a_id);

}
