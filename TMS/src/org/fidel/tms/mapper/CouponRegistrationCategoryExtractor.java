package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponRegistration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CouponRegistrationCategoryExtractor implements ResultSetExtractor<CouponRegistration> {

	@Override
	public CouponRegistration extractData(ResultSet result) throws SQLException, DataAccessException {
		
		CouponRegistration cr = new CouponRegistration();
		
		cr.setOil_company(result.getString("oil_company"));
		cr.setCreate_date(result.getString("create_date") + "*" + result.getString("total"));
		cr.setCoup_category_label(result.getString("oil_company") + "(" + result.getDate("create_date") + ")");
		cr.setAmount(result.getString("total"));
		
		return cr;
	}

}
