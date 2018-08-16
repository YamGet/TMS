package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.FrightOrder;
import org.springframework.jdbc.core.RowMapper;

public class FrightOrderCollectedPaymentMapper implements RowMapper<FrightOrder> {

	@Override
	public FrightOrder mapRow(ResultSet result, int rowNum) throws SQLException {
		
		FrightOrderCollectedPaymentExtractor focpe = new FrightOrderCollectedPaymentExtractor();
		
		return focpe.extractData(result);
	}

}
