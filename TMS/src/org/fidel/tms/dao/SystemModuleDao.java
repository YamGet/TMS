package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.SystemModule;
import org.fidel.tms.model.UsersRoleModuleRelation;

public interface SystemModuleDao {
	
	public List<SystemModule> getUnrelatedSystemModuleList(int ur_id);
	
	public List<SystemModule> getRelatedSystemModuleList(int ur_id);
	
	public List<UsersRoleModuleRelation> getUsersRoleRelatedSystemModuleList(int ur_id);
	
}
