package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponRegistration;
import org.springframework.jdbc.core.RowMapper;

public class CouponRegistrationCategoryRowMapper implements RowMapper<CouponRegistration> {

	@Override
	public CouponRegistration mapRow(ResultSet result, int rowNum) throws SQLException {
		CouponRegistrationCategoryExtractor cre = new CouponRegistrationCategoryExtractor();
		return cre.extractData(result);
	}
	
	

}
