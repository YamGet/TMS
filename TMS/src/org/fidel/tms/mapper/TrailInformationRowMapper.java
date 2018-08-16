package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.TrailInformation;
import org.springframework.jdbc.core.RowMapper;

public class TrailInformationRowMapper implements RowMapper<TrailInformation> {

	@Override
	public TrailInformation mapRow(ResultSet result, int rowNum) throws SQLException {
		
		TrailInformationExtractor tli = new TrailInformationExtractor();
		
		return tli.extractData(result);
	}

}
