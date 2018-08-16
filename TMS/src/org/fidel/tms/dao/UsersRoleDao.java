package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.UsersRole;

public interface UsersRoleDao {
	
	public List<UsersRole> getActiveUsersRole();
	
	public List<UsersRole> getAllUsersRole();
	
	public boolean saveUsersRole(UsersRole usersRole);
	
	public boolean updateUsersRole(UsersRole usersRole);
	
	public boolean checkUsersRole(String userrole_name);
	
	public boolean checkUsersRole(String userrole_name, int ur_id);

}
