package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponRegistration;
import org.springframework.jdbc.core.RowMapper;

public class CouponRegistrationRowMapper implements RowMapper<CouponRegistration> {

	@Override
	public CouponRegistration mapRow(ResultSet result, int rowNum) throws SQLException {
		CouponRegistrationExtractor cre = new CouponRegistrationExtractor();
		return cre.extractData(result);
	}
	
	

}
