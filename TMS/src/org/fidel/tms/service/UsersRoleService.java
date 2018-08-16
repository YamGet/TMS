package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.UsersRole;

public interface UsersRoleService {
	
	public List<UsersRole> getActiveUsersRole();
	
	public List<UsersRole> getAllUsersRole();
	
	public boolean saveUsersRole(UsersRole usersRole);
	
	public boolean updateUsersRole(UsersRole usersRole);

}
