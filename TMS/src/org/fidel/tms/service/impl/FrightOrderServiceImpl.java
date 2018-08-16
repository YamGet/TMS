package org.fidel.tms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fidel.tms.dao.FrightOrderDao;
import org.fidel.tms.dao.FrightOrderTripDetailDao;
import org.fidel.tms.dao.TrucksDao;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.model.Associations;
import org.fidel.tms.model.Drivers;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.model.TruckInformation;
import org.fidel.tms.service.FrightOrderService;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrightOrderServiceImpl implements FrightOrderService {
	
	@Autowired
	FrightOrderDao frightOrderDao;
	
	@Autowired
	FrightOrderTripDetailDao frightOrderTripDetailDao;
	
	@Autowired
	TrucksDao trucksDao;
	
	@Override
	public List<FrightOrder> getCompleteFrightOrderList() {
		
		return frightOrderDao.getCompleteFrightOrderList();
	}	

	@Override
	public List<FrightOrder> getCompleteFrightOrderListByTruckType(String truck_type) {
		
		return frightOrderDao.getCompleteFrightOrderListByTruckType(truck_type);
	}


	@Override
	public List<FrightOrder> getIncompleteFrightOrderList() {
		
		return frightOrderDao.getIncompleteFrightOrderList();
	}
	
	@Override
	public List<FrightOrder> getAllFrightOrderList() {
		
		return frightOrderDao.getAllFrightOrderList();
	}

	@Override
	public List<FrightOrder> getFrightOrder(int fo_id) {
		
		return frightOrderDao.getFrightOrder(fo_id);
	}
	
	@Override
	public List<Associations> getAssociationList() {
		
		return frightOrderDao.getAssociationList();
	}

	@Override
	public List<TruckInformation> getAvailableTrucks() {
		
		return frightOrderDao.getAvailableTrucks();
	}

	@Override
	public List<TrailInformation> getAvailableTrails() {
		
		return frightOrderDao.getAvailableTrails();
	}
	
	@Override
	public List<TrailInformation> getAvailableTrailsBySelectedTruckCarryingType(int tri_id) {
		
		return frightOrderDao.getAvailableTrailsBySelectedTruckCarryingType(tri_id);
	}

	@Override
	public List<Drivers> getAvailableDrivers() {
		
		return frightOrderDao.getAvailableDrivers();
	}

	@Override
	public boolean insertNewFrightOrder(FrightOrder frightOrder) {
		
		return frightOrderDao.insertNewFrightOrder(frightOrder);
	}

	@Override
	public List<FrightOrderTripDetail> getFrightOrderTripDetail(int fo_id) {
		
		return frightOrderTripDetailDao.getFrightOrderTripDetail(fo_id);
	}

	@Override
	public boolean updateFrightOrderCommission(FrightOrder frightOrder) {
		
		return frightOrderDao.updateFrightOrderCommission(frightOrder);
	}

	@Override
	public List<FrightOrder> getFrightOrderInfoByFon(String fon) {
		
		return frightOrderDao.getFrightOrderInfoByFon(fon);
	}

	@Override
	public List<FrightOrder> getLastFrightOrderInserted() {
		
		return frightOrderDao.getLastFrightOrderInserted();
	}

	@Override
	public void insertTrackReserve(FrightOrder frightOrder) {
		
		List<FrightOrder> foList = frightOrderDao.getLastFrightOrderInserted();
		
		for(int i = 0; i < foList.size(); i++){
			
			frightOrder.setFo_id(foList.get(i).getFo_id());
		}
		
		frightOrderDao.insertTrackReserve(frightOrder);
	}
	
	@Override
	public void insertTrailReserve(FrightOrder frightOrder) {
		
		List<FrightOrder> foList = frightOrderDao.getLastFrightOrderInserted();
		
		for(int i = 0; i < foList.size(); i++){
			
			frightOrder.setFo_id(foList.get(i).getFo_id());
		}
				
		frightOrderDao.insertTrailReserve(frightOrder);
	}

	@Override
	public boolean insertCouponDissemination(FrightOrder frightOrder) {
		
		List<FrightOrder> foList = frightOrderDao.getFrightOrder(frightOrder.getFo_id());
		
		for(int i = 0; i < foList.size(); i++){
			frightOrder.setTotal_coupon_amount(foList.get(i).getTotal_coupon_amount());
		}
		
		return frightOrderDao.insertCouponDissemination(frightOrder);
	}

	@Override
	public boolean assignCouponDissemination(FrightOrder frightOrder) {

		List<FrightOrder> foList = frightOrderDao.getFrightOrder(frightOrder.getFo_id());
		
		for(int i = 0; i < foList.size(); i++){
			frightOrder.setTotal_coupon_amount(foList.get(i).getTotal_coupon_amount());
		}
		
		return frightOrderDao.assignCouponDissemination(frightOrder);
	}

	@Override
	public boolean assignNotGivenCouponDissemination(FrightOrder frightOrder) {

		List<FrightOrder> foList = frightOrderDao.getFrightOrder(frightOrder.getFo_id());
		
		for(int i = 0; i < foList.size(); i++){
			frightOrder.setTotal_coupon_amount(foList.get(i).getTotal_coupon_amount());
		}
		
		return frightOrderDao.assignNotGivenCouponDissemination(frightOrder);
	}

	@Override
	public List<FrightOrder> getTruckDailyActivityList() {
		
		List<FrightOrder> filteredList = new ArrayList<FrightOrder>();
		
		List<FrightOrder> dailyActivityList = frightOrderDao.getTruckDailyActivityList();
		
		List<TruckInformation> activeTrucks = trucksDao.getActiveTrucks();
		
		for(int i = 0; i < activeTrucks.size(); i++){
			
			int index = -1;
			
			for(int j = 0; j < dailyActivityList.size(); j++){
				
				if(activeTrucks.get(i).getTruck_plate_no().equals(dailyActivityList.get(j).getTruckInformation().getTruck_plate_no())){
					
					index = j;
				}
			}
			
			if(index != -1){
				
				filteredList.add(dailyActivityList.get(index));
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0; i < filteredList.size(); i++){
			
			String date_from = filteredList.get(i).getDate_from();
			long dateDiff = 0;
			try {
				Date td = sdf.parse(today);
				Date df = sdf.parse(date_from);
				dateDiff = (td.getTime() - df.getTime())/(24*60*60*1000);
			} catch (ParseException e) {					
				e.printStackTrace();
			}
			filteredList.get(i).setNo_of_days(dateDiff);
		}
		
		return filteredList;
	}
	
	@Override
	public List<FrightOrder> getTruckDailyActivityListByTruckType(String value) {
		
		List<FrightOrder> filteredList = new ArrayList<FrightOrder>();
		
		List<FrightOrder> dailyActivityList = frightOrderDao.getTruckDailyActivityListByTruckType(value);
		
		List<TruckInformation> activeTrucks = trucksDao.getActiveTrucks();
		
		for(int i = 0; i < activeTrucks.size(); i++){
			
			int index = 0;
			
			for(int j = 0; j < dailyActivityList.size(); j++){
				
				if(activeTrucks.get(i).getTruck_plate_no().equals(dailyActivityList.get(j).getTruckInformation().getTruck_plate_no())){
					
					index = j;
				}
			}
			
			if(index != 0){
				
				filteredList.add(dailyActivityList.get(index));
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0; i < filteredList.size(); i++){
			
			String date_from = filteredList.get(i).getDate_from();
			long dateDiff = 0;
			try {
				Date td = sdf.parse(today);
				Date df = sdf.parse(date_from);
				dateDiff = (td.getTime() - df.getTime())/(24*60*60*1000);
			} catch (ParseException e) {					
				e.printStackTrace();
			}
			filteredList.get(i).setNo_of_days(dateDiff);
		}
		
		return filteredList;
	}

	@Override
	public List<FrightOrder> getActiveFrightOrderListWithPriceZero(AdvancePayment advancePayment) {
		
		return frightOrderDao.getActiveFrightOrderListWithPriceZero(advancePayment);
	}

	@Override
	public boolean updateFrightOrder(FrightOrder frightOrder) {
		
		return frightOrderDao.updateFrightOrder(frightOrder);
	}

	@Override
	public void updateTrackReserve(FrightOrder frightOrder) {
		
		frightOrderDao.updateTrackReserve(frightOrder);
	}

	@Override
	public void updateTrailReserve(FrightOrder frightOrder) {
		
		frightOrderDao.updateTrailReserve(frightOrder);
	}

	@Override
	public List<TruckInformation> getAvailableTrucksForUpdate(int tri_id) {
		
		return frightOrderDao.getAvailableTrucksForUpdate(tri_id);
	}

	@Override
	public List<TrailInformation> getAvailableTrailsForUpdate(int tli_id) {
		
		return frightOrderDao.getAvailableTrailsForUpdate(tli_id);
	}

	@Override
	public List<Drivers> getAvailableDriversForUpdate(int dr_id) {
		
		return frightOrderDao.getAvailableDriversForUpdate(dr_id);
	}

	@Override
	public List<FrightOrder> filterCompleteFrightOrderList(String search_value, List<FrightOrder> frightOrderList) {
		
		int counter = 0, size = (frightOrderList == null)?0:frightOrderList.size();
		
		List<FrightOrder> filteredOrderList = new ArrayList<FrightOrder>();
		
		for(int i = 0; i < size; i++){
			
			if(frightOrderList.get(i).getFrightOrderTripDetail().getClient_organization().toLowerCase().toString().contains(search_value)){
				
				counter++;
			}			
			if(frightOrderList.get(i).getAssociations().getAssociation_name().toLowerCase().toString().contains(search_value)){
				
				counter++;
			}
			if(frightOrderList.get(i).getFrightOrderTripDetail().getClient_organization().toLowerCase().toString().contains(search_value)){
				
				counter++;
			}
			if(frightOrderList.get(i).getFon().toString().contains(search_value)){
				
				counter++;
			}
			if(frightOrderList.get(i).getTruckInformation().getTruck_plate_no().toLowerCase().toString().contains(search_value.toLowerCase())){
				
				counter++;
			}
			if(frightOrderList.get(i).getTrailInformation().getTrail_plate_no().toLowerCase().toString().contains(search_value)){
				
				counter++;
			}			
			if((frightOrderList.get(i).getDrivers().getFname() + " " + frightOrderList.get(i).getDrivers().getMname()).toLowerCase().toString().contains(search_value)){
				
				counter++;
			}
			if(frightOrderList.get(i).getDate_from().toLowerCase().toString().contains(search_value)){
				
				counter++;
			}
			if(frightOrderList.get(i).getDate_to().toLowerCase().toString().contains(search_value)){
				
				counter++;
			}
			
			if(counter > 0){
				
				filteredOrderList.add(frightOrderList.get(i));
			}
			
			counter = 0;
		}
		
		return filteredOrderList;
	}

	@Override
	public List<FrightOrder> getTransactionListPerTruck(FrightOrder frightOrder) {
		
		return frightOrderDao.getTransactionListPerTruck(frightOrder);
	}

	@Override
	public List<FrightOrder> getClosedFrightOrderList() {
		
		return frightOrderDao.getClosedFrightOrderList();
	}

	@Override
	public List<FrightOrder> getActiveFrightOrderList(AdvancePayment advancePayment) {
		
		return frightOrderDao.getActiveFrightOrderList(advancePayment);
	}

	@Override
	public List<FrightOrder> getLoadingUnloadingDifferencePerTruck(FrightOrder frightOrder) {
		
		return frightOrderDao.getLoadingUnloadingDifferencePerTruck(frightOrder);
	}

	
}
