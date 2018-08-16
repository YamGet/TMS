package org.fidel.tms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fidel.tms.dao.PaymentDao;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.Payment;
import org.fidel.tms.service.PaymentService;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentDao paymenetDao;

	@Override
	public List<FrightOrder> getUnprocessedPaymentList() {
		
		List<FrightOrder> unprocessedPaymentList = paymenetDao.getUnprocessedPaymentList(); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0; i < unprocessedPaymentList.size(); i++){
			
			Date date_from = unprocessedPaymentList.get(i).getFrightOrderTripDetail().getClose_date();
			
			long dateDiff = 0;
			
			try {
				
				Date td = sdf.parse(today);		
				
				dateDiff = (td.getTime() - date_from.getTime())/(24*60*60*1000);
				
			} catch (ParseException e) {					
				e.printStackTrace();
			}
			
			unprocessedPaymentList.get(i).setNo_of_days(dateDiff);
		}
		
		return unprocessedPaymentList;
	}
	
	@Override
	public List<FrightOrder> getUnprocessedPaymentListByTruckType(String truck_type) {
		
		List<FrightOrder> unprocessedPaymentList = paymenetDao.getUnprocessedPaymentListByTruckType(truck_type); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0; i < unprocessedPaymentList.size(); i++){
			
			Date date_from = unprocessedPaymentList.get(i).getFrightOrderTripDetail().getClose_date();
			
			long dateDiff = 0;
			
			try {
				
				Date td = sdf.parse(today);		
				
				dateDiff = (td.getTime() - date_from.getTime())/(24*60*60*1000);
				
			} catch (ParseException e) {					
				e.printStackTrace();
			}
			
			unprocessedPaymentList.get(i).setNo_of_days(dateDiff);
		}
		
		return unprocessedPaymentList;
	}

	@Override
	public List<FrightOrder> getInprocessPaymentList() {
		
		List<FrightOrder> inprocessPaymentList = paymenetDao.getInprocessPaymentList();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0; i < inprocessPaymentList.size(); i++){
			
			if(inprocessPaymentList.get(i).getFrightOrderTripDetail().getPayment_request_date()!=null){
			
				Date date_from = inprocessPaymentList.get(i).getFrightOrderTripDetail().getPayment_request_date();
				
				long dateDiff = 0;
				
				try {
					
					Date td = sdf.parse(today);		
					
					dateDiff = (td.getTime() - date_from.getTime())/(24*60*60*1000);
					
				} catch (ParseException e) {					
					e.printStackTrace();
				}
				
				inprocessPaymentList.get(i).setNo_of_days(dateDiff);
			}
		}
		
		return inprocessPaymentList;
	}
	
	@Override
	public List<FrightOrder> getInprocessPaymentListByTruckType(String truck_type) {
		
		List<FrightOrder> inprocessPaymentList = paymenetDao.getInprocessPaymentListByTruckType(truck_type);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String today = TodayDate_YYYYMMDD.getToday();
		
		for(int i = 0; i < inprocessPaymentList.size(); i++){
			
			Date date_from = inprocessPaymentList.get(i).getFrightOrderTripDetail().getPayment_request_date();
			
			long dateDiff = 0;
			
			try {
				
				Date td = sdf.parse(today);		
				
				dateDiff = (td.getTime() - date_from.getTime())/(24*60*60*1000);
				
			} catch (ParseException e) {					
				e.printStackTrace();
			}
			
			inprocessPaymentList.get(i).setNo_of_days(dateDiff);
		}
		
		return inprocessPaymentList;
	}

	@Override
	public List<FrightOrder> getFrightOrderInfo(int fo_id) {
		
		return paymenetDao.getFrightOrderInfo(fo_id);
	}

	@Override
	public boolean updateFrightOrderPaymentRequestDate(int fo_id) {
		
		return paymenetDao.updateFrightOrderPaymentRequestDate(fo_id);
	}

	@Override
	public boolean checkPaymentAmount(List<Integer> foid, Double payment_amount, String coupon_status) {
		
		double total_payment = 0.0;
		
		DecimalFormat df = new DecimalFormat("#.##");
		String totalpayment = "";
		
		for(int i = 0; i < foid.size(); i++){
			
			List<FrightOrder> foInfo = paymenetDao.getFrightOrderInfo(foid.get(i));
			
			Double total_price = foInfo.get(0).getFrightOrderTripDetail().getDelivered_quantity() * foInfo.get(0).getFrightOrderTripDetail().getPrice();
			
			Double fo_net_payment = 0.0;
			
			if(coupon_status.equals("deduct")){
				
				fo_net_payment = total_price - ((total_price*foInfo.get(0).getCommission()) + foInfo.get(0).getTotal_coupon_amount());
			} else {
				
				fo_net_payment = total_price - ((total_price*foInfo.get(0).getCommission()));
			}
			total_payment = Double.valueOf(df.format(total_payment + fo_net_payment));

//			BigDecimal a = new BigDecimal(String.valueOf(total_payment));
//			BigDecimal b = a.setScale(2, RoundingMode.HALF_UP);
//			total_payment = b.doubleValue();
		}
		
		int t_payment = (int)total_payment; 
		int p_amount = (int)payment_amount.doubleValue();
		
		if(t_payment == p_amount){
			
			return true;
		} else {
			
			return false;
		}
	}

	@Override
	public boolean saveFrightOrderPayment(List<Integer> foid, Payment payment) {
		
		return paymenetDao.saveFrightOrderPayment(foid, payment);
	}

	@Override
	public List<FrightOrder> getCollectedPaymentReport(FrightOrder frightOrder) {
		
		return paymenetDao.getCollectedPaymentReport(frightOrder);
	}

	@Override
	public List<FrightOrder> getFilteredUnprocessedPaymentList(String search_value, List<FrightOrder> unprocessedFrightOrder) {
		
		int counter = 0;
		
		List<FrightOrder> unprocessedFrightOrderFiltered = new ArrayList<FrightOrder>();
		
		for(int i = 0; i < unprocessedFrightOrder.size(); i++){
			
			if(unprocessedFrightOrder.get(i).getFon().toString().contains(search_value)){
				counter++;
			}
			if(unprocessedFrightOrder.get(i).getTruckInformation().getTruck_plate_no().toLowerCase().toString().contains(search_value)){
				counter++;
			}
			if(unprocessedFrightOrder.get(i).getTrailInformation().getTrail_plate_no().toLowerCase().toString().contains(search_value)){
				counter++;
			}
			if(unprocessedFrightOrder.get(i).getDate_from().toString().contains(search_value)){
				counter++;
			}
			if(unprocessedFrightOrder.get(i).getDate_to().toString().contains(search_value)){
				counter++;
			}
			
			if(counter > 0){
				unprocessedFrightOrderFiltered.add(unprocessedFrightOrder.get(i));
			}
			
			counter = 0;
		}
		
		return unprocessedFrightOrderFiltered;
	}
	
	@Override
	public List<FrightOrder> getFilteredInprocessedPaymentList(String search_value, List<FrightOrder> inprocessedFrightOrder) {
		
		int counter = 0;
		
		List<FrightOrder> inprocessedFrightOrderFiltered = new ArrayList<FrightOrder>();
		
		for(int i = 0; i < inprocessedFrightOrder.size(); i++){
			
			if(inprocessedFrightOrder.get(i).getFon().toString().contains(search_value)){
				counter++;
			}
			if(inprocessedFrightOrder.get(i).getTruckInformation().getTruck_plate_no().toLowerCase().toString().contains(search_value)){
				counter++;
			}
			if(inprocessedFrightOrder.get(i).getTrailInformation().getTrail_plate_no().toLowerCase().toString().contains(search_value)){
				counter++;
			}
			if(inprocessedFrightOrder.get(i).getDate_from().toString().contains(search_value)){
				counter++;
			}
			if(inprocessedFrightOrder.get(i).getDate_to().toString().contains(search_value)){
				counter++;
			}
			if(inprocessedFrightOrder.get(i).getFrightOrderTripDetail().getPayment_request_date().toString().contains(search_value)){
				counter++;
			}
			if(counter > 0){
				inprocessedFrightOrderFiltered.add(inprocessedFrightOrder.get(i));
			}
			
			counter = 0;
		}
		
		return inprocessedFrightOrderFiltered;
	}

}
