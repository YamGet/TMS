package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;

public interface FrightOrderTripDetailDao {
	
	public boolean saveFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail);
	
	public boolean updateFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail);
	
	public List<FrightOrderTripDetail> getFrightOrderTripDetail(int fo_id);
	
	public boolean closeFrightOrder(FrightOrderTripDetail frightOrderTripDetail);
	
	public boolean updateFrightOrderPaymentAppointmentDate(FrightOrderTripDetail foDetail);
	
	public void openTruckTrailAvailiability(int tri_id, int tli_id);
	
	public List<FrightOrderTripDetail> getFrightOrderTripDetailById(int fotd_id);
	
	public boolean updateFrightOrderTripDetailPrice(FrightOrderTripDetail frightOrderTripDetail);
	
}
