package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Drivers;
import org.springframework.jdbc.core.RowMapper;

public class DriversRowMapper implements RowMapper<Drivers> {

	@Override
	public Drivers mapRow(ResultSet result, int rowNum) throws SQLException {
		DriversExtractor de = new DriversExtractor();
		return de.extractData(result);
	}

}
