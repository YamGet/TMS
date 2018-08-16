package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.Payment;

public interface PaymentService {
	
	public List<FrightOrder> getUnprocessedPaymentList();
	
	public List<FrightOrder> getUnprocessedPaymentListByTruckType(String truck_type);
	
	public List<FrightOrder> getFilteredUnprocessedPaymentList(String search_value, List<FrightOrder> unprocessedFrightOrder);
	
	public List<FrightOrder> getInprocessPaymentList();
	
	public List<FrightOrder> getInprocessPaymentListByTruckType(String truck_type);
	
	public List<FrightOrder> getFilteredInprocessedPaymentList(String search_value, List<FrightOrder> unprocessedFrightOrder);

	public List<FrightOrder> getFrightOrderInfo(int fo_id);
	
	public boolean updateFrightOrderPaymentRequestDate(int fo_id);
	
	public boolean checkPaymentAmount(List<Integer> foid, Double payment_amount, String coupon_status);
	
	public boolean saveFrightOrderPayment(List<Integer> foid, Payment payment);
	
	public List<FrightOrder> getCollectedPaymentReport(FrightOrder frightOrder);
	
}
