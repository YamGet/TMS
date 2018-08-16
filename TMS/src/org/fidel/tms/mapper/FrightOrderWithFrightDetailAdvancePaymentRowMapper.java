package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.FrightOrder;
import org.springframework.jdbc.core.RowMapper;

public class FrightOrderWithFrightDetailAdvancePaymentRowMapper implements RowMapper<FrightOrder> {

	@Override
	public FrightOrder mapRow(ResultSet result, int rowNum) throws SQLException {
		FrightOrderWithFrightDetailAdvancePaymentExtractor foe = new FrightOrderWithFrightDetailAdvancePaymentExtractor();
		return foe.extractData(result);
	}

}
