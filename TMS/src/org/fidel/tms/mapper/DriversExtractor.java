package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Drivers;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class DriversExtractor implements ResultSetExtractor<Drivers> {

	@Override
	public Drivers extractData(ResultSet result) throws SQLException, DataAccessException {
		
		Drivers dr = new Drivers();
		
		dr.setDr_id(result.getInt("dr_id"));
		dr.setFname(result.getString("fname"));
		dr.setMname(result.getString("mname"));
		dr.setGname(result.getString("gname"));
		dr.setDriving_license_no(result.getString("driving_license_no"));
		dr.setLocal_phone(result.getString("local_phone"));
		dr.setAbroad_phone(result.getString("abroad_phone"));
		dr.setDr_status(result.getString("dr_status"));
		dr.setFullName(result.getString("fname") + " " + result.getString("mname"));
		
		return dr;
	}

}
