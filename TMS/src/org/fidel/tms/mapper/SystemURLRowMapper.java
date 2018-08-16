package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.SystemURL;
import org.springframework.jdbc.core.RowMapper;

public class SystemURLRowMapper implements RowMapper<SystemURL> {

	@Override
	public SystemURL mapRow(ResultSet result, int rowNum) throws SQLException {
		SystemURLExtractor urlext = new SystemURLExtractor();
		return urlext.extractData(result);
	}

}
