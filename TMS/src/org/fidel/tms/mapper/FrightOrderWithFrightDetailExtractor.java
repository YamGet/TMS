package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.Associations;
import org.fidel.tms.model.Drivers;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.model.TruckInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class FrightOrderWithFrightDetailExtractor implements ResultSetExtractor<FrightOrder> {

	@Override
	public FrightOrder extractData(ResultSet result) throws SQLException, DataAccessException {
		
		FrightOrder fo = new FrightOrder();
		
		fo.setFo_id(Integer.parseInt(result.getString("fo_id")));
		
		fo.setA_id(Integer.parseInt(result.getString("a_id")));
		Associations associations = new Associations();
		associations.setAssociation_name(result.getString("association_name"));
		fo.setAssociations(associations);
		
		fo.setFon(result.getString("fon"));
		
		fo.setTri_id(Integer.parseInt(result.getString("tri_id")));				
		TruckInformation truckInformation = new TruckInformation();
		truckInformation.setTruck_plate_no(result.getString("truck_plate_no"));
		truckInformation.setTruck_owner(result.getString("truck_owner"));
		fo.setTruckInformation(truckInformation);				
		
		fo.setTli_id(Integer.parseInt(result.getString("tli_id")));
		TrailInformation trailInformation = new TrailInformation();
		trailInformation.setTrail_plate_no(result.getString("trail_plate_no"));
		fo.setTrailInformation(trailInformation);
		
		fo.setDr_id(Integer.parseInt(result.getString("dr_id")));
		Drivers drivers = new Drivers();
		drivers.setFname(result.getString("fname"));
		drivers.setMname(result.getString("mname"));
		drivers.setGname(result.getString("gname"));
		fo.setDrivers(drivers);
		
		fo.setTrip(result.getString("trip"));
		fo.setTotal_coupon_amount(Integer.parseInt(result.getString("total_coupon_amount")));
		fo.setCommission(result.getDouble("commission"));
		
		fo.setDate_from(result.getString("date_from"));
		fo.setDate_to(result.getString("date_to"));
		
		fo.setFo_status(result.getString("fo_status"));
		
		FrightOrderTripDetail fotd = new FrightOrderTripDetail();
		fotd.setDispatch_doc_ref_no(result.getString("dispatch_doc_ref_no"));
		fotd.setDelivery_doc_ref_no(result.getString("delivery_doc_ref_no"));
		fotd.setDelivered_quantity(result.getDouble("delivered_quantity"));
		fotd.setOrigin_place(result.getString("origin_place"));
		fotd.setDestination_place(result.getString("destination_place"));
		fotd.setClient_organization(result.getString("client_organization").toUpperCase());
		fotd.setLoading_quantity(result.getString("loading_quantity"));
		fotd.setPrice(result.getDouble("price"));
		fotd.setCrv_number(result.getString("crv_number"));
		fotd.setPayment_request_date(result.getDate("payment_request_date"));
		fotd.setPayment_appointment_date(result.getString("payment_appointment_date"));
		fotd.setClose_date(result.getDate("close_date"));
		fo.setFrightOrderTripDetail(fotd);
		
		return fo;
	}

}
