package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.UsersRoleModuleRelation;

public interface UsersRoleModuleRelationService {
	
	public boolean saveUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation, List<Integer> selectedModule);
	
	public boolean updateUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation, List<Integer> selectedModule);
	
}
