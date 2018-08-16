package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;
import org.springframework.jdbc.core.RowMapper;

public class CouponConsumedRowMapper implements RowMapper<CouponDissemination> {

	@Override
	public CouponDissemination mapRow(ResultSet result, int rowNum) throws SQLException {
		CouponConsumedExtractor cre = new CouponConsumedExtractor();
		return cre.extractData(result);
	}
	
	

}
