package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.SystemURL;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class SystemURLExtractor implements ResultSetExtractor<SystemURL> {

	@Override
	public SystemURL extractData(ResultSet result) throws SQLException, DataAccessException {
		
		SystemURL su = new SystemURL();
		
		su.setSm_id(result.getInt("sm_id"));
		su.setDescription(result.getString("description"));
		su.setUrl(result.getString("url"));
		su.setSu_id(result.getInt("su_id"));
		
		UsersRoleModuleRelation urmr = new UsersRoleModuleRelation();
		urmr.setUrmr_id(result.getInt("urmr_id"));
		su.setUsersRoleModuleRelation(urmr);
		
		return su;
	}

}
