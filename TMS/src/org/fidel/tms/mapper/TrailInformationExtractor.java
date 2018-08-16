package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.TrailInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class TrailInformationExtractor implements ResultSetExtractor<TrailInformation> {

	@Override
	public TrailInformation extractData(ResultSet result) throws SQLException, DataAccessException {
		
		TrailInformation tl = new TrailInformation();
		
		tl.setTli_id(result.getInt("tli_id"));
		tl.setTrail_plate_no(result.getString("trail_plate_no"));
		
		return tl;
	}

}
