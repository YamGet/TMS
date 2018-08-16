package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.Payment;
import org.fidel.tms.model.TruckInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class FrightOrderCollectedPaymentExtractor implements ResultSetExtractor<FrightOrder> {

	@Override
	public FrightOrder extractData(ResultSet result) throws SQLException, DataAccessException {

		FrightOrder fo = new FrightOrder();
		
		fo.setFo_id(Integer.parseInt(result.getString("fo_id")));		
		fo.setFon(result.getString("fon"));
		fo.setCommission(result.getDouble("commission"));
		
		FrightOrderTripDetail fotd = new FrightOrderTripDetail();
		fotd.setPrice(Double.parseDouble(result.getString("price")));
		fotd.setDelivered_quantity(result.getDouble("delivered_quantity"));
		fotd.setOrigin_place(result.getString("origin_place"));
		fotd.setDestination_place(result.getString("destination_place"));
		fotd.setClient_organization(result.getString("client_organization"));
		fotd.setCrv_number(result.getString("crv_number"));
		fo.setFrightOrderTripDetail(fotd);
		
		Payment p = new Payment();
		p.setPayment_amount(result.getDouble("payment_amount"));
		p.setPayment_type(result.getString("payment_type"));
		p.setPayment_doc_ref_no(result.getString("payment_doc_ref_no"));
		p.setPayment_date(result.getString("payment_date"));
		fo.setPayment(p);
		
		TruckInformation ti =  new TruckInformation();
		ti.setTruck_plate_no(result.getString("truck_plate_no"));
		fo.setTruckInformation(ti);
		
		return fo;
	}

}
