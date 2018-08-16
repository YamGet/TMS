package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.AdvancePayment;
import org.springframework.jdbc.core.RowMapper;

public class AdvancePaymentRowMapper implements RowMapper<AdvancePayment> {

	@Override
	public AdvancePayment mapRow(ResultSet result, int rowNum) throws SQLException {
		AdvancePaymentExtractor ape = new AdvancePaymentExtractor();
		return ape.extractData(result);
	}

}
