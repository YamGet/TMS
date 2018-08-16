package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.SystemModule;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class SystemModuleExtractor implements ResultSetExtractor<SystemModule> {

	@Override
	public SystemModule extractData(ResultSet result) throws SQLException, DataAccessException {

		SystemModule sm = new SystemModule();
		
		sm.setM_id(result.getInt("m_id"));
		sm.setModule_name(result.getString("module_name"));
		sm.setModule_status(result.getString("module_status"));
		
		return null;
	}

}
