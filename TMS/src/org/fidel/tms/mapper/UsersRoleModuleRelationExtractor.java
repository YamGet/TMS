package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.SystemModule;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UsersRoleModuleRelationExtractor implements ResultSetExtractor<UsersRoleModuleRelation> {

	@Override
	public UsersRoleModuleRelation extractData(ResultSet result) throws SQLException, DataAccessException {
		
		UsersRoleModuleRelation urmr = new UsersRoleModuleRelation();
		
		urmr.setM_id(result.getInt("m_id"));
		urmr.setUr_id(result.getInt("ur_id"));
		urmr.setUrmr_id(result.getInt("urmr_id"));
		urmr.setUrmr_status(result.getString("urmr_status"));

		SystemModule sm = new SystemModule();
		sm.setM_id(result.getInt("m_id"));
		sm.setModule_name(result.getString("module_name"));
		urmr.setSystemModule(sm);
		
//		UsersRole ur = new UsersRole();
//		ur.setUserrole_name(result.getString("userrole_name"));
//		urmr.setUsersRole(ur);
		
		return urmr;
	}

}
