package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CouponDisseminationWithCouponTypeExtractor implements ResultSetExtractor<CouponDissemination> {

	@Override
	public CouponDissemination extractData(ResultSet result) throws SQLException, DataAccessException {
		
		CouponDissemination cd = new CouponDissemination();
		
		cd.setCd_id(result.getInt("cd_id"));
		cd.setC_id(result.getInt("c_id"));
		cd.setFo_id(result.getInt("fo_id"));
		cd.setCd_status(result.getString("cd_status"));
		
		CouponRegistration cr = new CouponRegistration();
		cr.setAmount(result.getString("amount"));
		cd.setCouponRegistration(cr);
		
		return cd;
	}

}
