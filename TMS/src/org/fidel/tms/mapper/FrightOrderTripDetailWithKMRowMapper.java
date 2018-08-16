package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.FrightOrderTripDetail;
import org.springframework.jdbc.core.RowMapper;

public class FrightOrderTripDetailWithKMRowMapper implements RowMapper<FrightOrderTripDetail> {

	@Override
	public FrightOrderTripDetail mapRow(ResultSet result, int rowNum) throws SQLException {
		
		FrightOrderTripDetailWithKMExtractor ford = new FrightOrderTripDetailWithKMExtractor();
		
		return ford.extractData(result);
	}

}
