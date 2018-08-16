package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.AdvancePayment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AdvancePaymentWithCouponTransferExtractor implements ResultSetExtractor<AdvancePayment> {

	@Override
	public AdvancePayment extractData(ResultSet result) throws SQLException, DataAccessException {
		
		AdvancePayment ap = new AdvancePayment();
		
		ap.setAp_id(result.getInt("ap_id"));
		ap.setAdvance_payment_amount(result.getDouble("advance_payment_amount"));
		ap.setReturn_amount(result.getDouble("return_amount"));
		ap.setAdditional_amount(result.getDouble("additional_amount"));
		ap.setSend_reference_number(result.getString("send_reference_number"));
		ap.setSend_date(result.getString("send_date"));
		ap.setCoupon_transfer_amount(result.getDouble("coupon_transfer_amount"));
		ap.setCoupon_receive_amount(result.getDouble("coupon_receive_amount"));
		
		ap.setFo_id(result.getInt("fo_id"));
		
		return ap;
	}

}
