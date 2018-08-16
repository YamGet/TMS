package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.TruckInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class TruckInformationExtractor implements ResultSetExtractor<TruckInformation> {

	@Override
	public TruckInformation extractData(ResultSet result) throws SQLException, DataAccessException {
		
		TruckInformation tr = new TruckInformation();
		
		tr.setTri_id(result.getInt("tri_id"));
		tr.setTruck_plate_no(result.getString("truck_plate_no"));
		
		return tr;
	}

}
