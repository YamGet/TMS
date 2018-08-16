package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.SystemModuleDao;
import org.fidel.tms.model.SystemModule;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.fidel.tms.service.SystemModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemModuleServiceImpl implements SystemModuleService {
	
	@Autowired
	SystemModuleDao systemModuleDao;

	@Override
	public List<SystemModule> getUnrelatedSystemModuleList(int ur_id) {
		
		return systemModuleDao.getUnrelatedSystemModuleList(ur_id);
	}

	@Override
	public List<SystemModule> getRelatedSystemModuleList(int ur_id) {
		
		return systemModuleDao.getRelatedSystemModuleList(ur_id);
	}

	@Override
	public List<UsersRoleModuleRelation> getUsersRoleRelatedSystemModuleList(int ur_id) {
		
		return systemModuleDao.getUsersRoleRelatedSystemModuleList(ur_id);
	}

}
