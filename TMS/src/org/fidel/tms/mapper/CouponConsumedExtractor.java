package org.fidel.tms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.TruckInformation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CouponConsumedExtractor implements ResultSetExtractor<CouponDissemination> {

	@Override
	public CouponDissemination extractData(ResultSet result) throws SQLException, DataAccessException {
		
		CouponDissemination cd = new CouponDissemination();
		
		cd.setFo_id(result.getInt("fo_id"));
		
		CouponRegistration cr = new CouponRegistration();
		cr.setOil_company(result.getString("oil_company"));
		cr.setAmount(result.getString("amount"));
		cd.setCouponRegistration(cr);
		
		FrightOrderTripDetail fotd = new FrightOrderTripDetail();
		fotd.setOrigin_place(result.getString("origin_place"));
		fotd.setDestination_place(result.getString("destination_place"));
		cd.setFrightOrderTripDetail(fotd);
		
		TruckInformation ti = new TruckInformation();
		ti.setTruck_plate_no(result.getString("truck_plate_no"));
		cd.setTruckInformation(ti);
		
		return cd;
	}

}
