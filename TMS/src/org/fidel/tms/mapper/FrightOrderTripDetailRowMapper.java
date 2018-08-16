package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.FrightOrderTripDetail;
import org.springframework.jdbc.core.RowMapper;

public class FrightOrderTripDetailRowMapper implements RowMapper<FrightOrderTripDetail> {

	@Override
	public FrightOrderTripDetail mapRow(ResultSet result, int rowNum) throws SQLException {
		
		FrightOrderTripDetailExtractor ford = new FrightOrderTripDetailExtractor();
		
		return ford.extractData(result);
	}

}
