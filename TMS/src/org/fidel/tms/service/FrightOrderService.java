package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.model.Associations;
import org.fidel.tms.model.Drivers;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.model.TruckInformation;

public interface FrightOrderService {
	
	public List<FrightOrder> getCompleteFrightOrderList();
	
	public List<FrightOrder> getCompleteFrightOrderListByTruckType(String truck_type);
	
	public List<FrightOrder> filterCompleteFrightOrderList(String search_value, List<FrightOrder> frightOrderList);
	
	public List<FrightOrder> getIncompleteFrightOrderList();
	
	public List<FrightOrder> getAllFrightOrderList();
	
	public List<FrightOrder> getFrightOrder(int fo_id);
	
	public List<Associations> getAssociationList();
	
	public List<TruckInformation> getAvailableTrucks();
	
	public List<TrailInformation> getAvailableTrails();
	
	public List<TrailInformation> getAvailableTrailsBySelectedTruckCarryingType(int tri_id);
	
	public List<Drivers> getAvailableDrivers();
	
	public List<TruckInformation> getAvailableTrucksForUpdate(int tri_id);
	
	public List<TrailInformation> getAvailableTrailsForUpdate(int tli_id);
	
	public List<Drivers> getAvailableDriversForUpdate(int dr_id);
	
	public boolean insertNewFrightOrder(FrightOrder frightOrder);
	
	public boolean updateFrightOrder(FrightOrder frightOrder);
	
	public List<FrightOrderTripDetail> getFrightOrderTripDetail(int fo_id);
	
	public boolean updateFrightOrderCommission(FrightOrder frightOrder);
	
	public List<FrightOrder> getFrightOrderInfoByFon(String fon);
	
	public List<FrightOrder> getLastFrightOrderInserted();
	
	public void insertTrackReserve(FrightOrder frightOrder);
	
	public void updateTrackReserve(FrightOrder frightOrder);
	
	public void insertTrailReserve(FrightOrder frightOrder);
	
	public void updateTrailReserve(FrightOrder frightOrder);
	
	public boolean insertCouponDissemination(FrightOrder frightOrder);
	
	public boolean assignCouponDissemination(FrightOrder frightOrder);
	
	public boolean assignNotGivenCouponDissemination(FrightOrder frightOrder);
	
	public List<FrightOrder> getTruckDailyActivityList();
	
	public List<FrightOrder> getTruckDailyActivityListByTruckType(String value);
	
	public List<FrightOrder> getActiveFrightOrderListWithPriceZero(AdvancePayment advancePayment);
	
	public List<FrightOrder> getActiveFrightOrderList(AdvancePayment advancePayment);
	
	public List<FrightOrder> getTransactionListPerTruck(FrightOrder frightOrder);
	
	public List<FrightOrder> getClosedFrightOrderList();
	
	public List<FrightOrder> getLoadingUnloadingDifferencePerTruck(FrightOrder frightOrder);

}
