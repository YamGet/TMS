package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponRegistration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CouponRegistrationExtractor implements ResultSetExtractor<CouponRegistration> {

	@Override
	public CouponRegistration extractData(ResultSet result) throws SQLException, DataAccessException {
		
		CouponRegistration cr = new CouponRegistration();
		
		cr.setC_id(result.getInt("c_id"));
		cr.setOil_company(result.getString("oil_company"));
		cr.setC_serial_no(result.getString("c_serial_no"));
		cr.setAmount(result.getString("amount"));
		cr.setC_status(result.getString("c_status"));
		
		return cr;
	}

}
