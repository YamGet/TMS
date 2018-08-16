package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.FrightOrderTripDetail;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class FrightOrderTripDetailWithKMExtractor implements ResultSetExtractor<FrightOrderTripDetail> {

	@Override
	public FrightOrderTripDetail extractData(ResultSet result) throws SQLException, DataAccessException {
		
		FrightOrderTripDetail fotd = new FrightOrderTripDetail();
		
		fotd.setFotd_id(result.getInt("fotd_id"));
		fotd.setFo_id(result.getInt("fo_id"));
		fotd.setClient_organization(result.getString("client_organization"));
		fotd.setLoading_type(result.getString("loading_type"));
		fotd.setOrigin_place(result.getString("origin_place"));
		fotd.setDestination_place(result.getString("destination_place"));
		fotd.setInitial_km(result.getString("initial_km"));
		fotd.setLoading_quantity(result.getString("loading_quantity"));
		fotd.setDistance(result.getString("distance"));
		fotd.setPrice(result.getDouble("price"));
		fotd.setCrv_number(result.getString("crv_number"));
		fotd.setDispatch_doc_ref_no(result.getString("dispatch_doc_ref_no"));
		fotd.setDelivery_doc_ref_no(result.getString("delivery_doc_ref_no"));
		fotd.setDelivered_quantity(result.getDouble("delivered_quantity"));
		fotd.setDelivery_date(result.getString("delivery_date"));
		fotd.setFright_note(result.getString("fright_note"));
		
		return fotd;
	}

}
