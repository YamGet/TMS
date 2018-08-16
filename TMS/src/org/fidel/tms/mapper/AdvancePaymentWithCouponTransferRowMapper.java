package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.AdvancePayment;
import org.springframework.jdbc.core.RowMapper;

public class AdvancePaymentWithCouponTransferRowMapper implements RowMapper<AdvancePayment> {

	@Override
	public AdvancePayment mapRow(ResultSet result, int rowNum) throws SQLException {
		AdvancePaymentWithCouponTransferExtractor ape = new AdvancePaymentWithCouponTransferExtractor();
		return ape.extractData(result);
	}

}
