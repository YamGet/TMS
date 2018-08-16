package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.UsersRoleDao;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.service.UsersRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRoleServiceImpl implements UsersRoleService {
	
	@Autowired
	UsersRoleDao usersRoleDao;

	@Override
	public List<UsersRole> getActiveUsersRole() {
		
		return usersRoleDao.getActiveUsersRole();
	}

	@Override
	public List<UsersRole> getAllUsersRole() {
		
		return usersRoleDao.getActiveUsersRole();
	}

	@Override
	public boolean saveUsersRole(UsersRole usersRole) {
		
		return usersRoleDao.saveUsersRole(usersRole);
	}

	@Override
	public boolean updateUsersRole(UsersRole usersRole) {
		
		return usersRoleDao.updateUsersRole(usersRole);
	}

}
