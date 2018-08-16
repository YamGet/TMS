package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.UsersRoleModuleRelationDao;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.fidel.tms.service.UsersRoleModuleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRoleModuleRelationServiceImpl implements UsersRoleModuleRelationService {
	
	@Autowired
	UsersRoleModuleRelationDao usersRoleModuleRelationDao;

	@Override
	public boolean saveUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation, List<Integer> selectedModule) {
		
		int counter = 0;
		
		for(int i = 0; i < selectedModule.size(); i++){
			
			usersRoleModuleRelation.setM_id(selectedModule.get(i));
			
			boolean rslt = usersRoleModuleRelationDao.saveUsersRoleModuleRelation(usersRoleModuleRelation);
			
			if(rslt){
				counter++;
			}
		}
		if(counter == selectedModule.size()){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUsersRoleModuleRelation(UsersRoleModuleRelation usersRoleModuleRelation, List<Integer> selectedModule) {

		int counter = 0;
		
		for(int i = 0; i < selectedModule.size(); i++){
			
			usersRoleModuleRelation.setM_id(selectedModule.get(i));
			
			boolean rslt = usersRoleModuleRelationDao.updateUsersRoleModuleRelation(usersRoleModuleRelation);
			
			if(rslt){
				counter++;
			}
		}
		if(counter == selectedModule.size()){
			return true;
		}
		return false;
	}

}
