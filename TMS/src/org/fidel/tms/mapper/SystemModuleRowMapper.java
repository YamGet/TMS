package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.SystemModule;
import org.springframework.jdbc.core.RowMapper;

public class SystemModuleRowMapper implements RowMapper<SystemModule> {

	@Override
	public SystemModule mapRow(ResultSet result, int rowNum) throws SQLException {
		SystemModuleExtractor sme = new SystemModuleExtractor();
		return sme.extractData(result);
	}

}
