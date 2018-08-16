package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.TruckInformation;
import org.springframework.jdbc.core.RowMapper;

public class TruckInformationRowMapper implements RowMapper<TruckInformation> {

	@Override
	public TruckInformation mapRow(ResultSet result, int rowNum) throws SQLException {
		TruckInformationExtractor tie = new TruckInformationExtractor();
		return tie.extractData(result);
	}

	

}
