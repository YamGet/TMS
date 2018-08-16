package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponDissemination;
import org.springframework.jdbc.core.RowMapper;

public class CouponDisseminationWithCouponTypeRowMapper implements RowMapper<CouponDissemination> {

	@Override
	public CouponDissemination mapRow(ResultSet result, int rowNum) throws SQLException {
		
		CouponDisseminationWithCouponTypeExtractor cde = new CouponDisseminationWithCouponTypeExtractor();
		
		return cde.extractData(result);
	}

}
