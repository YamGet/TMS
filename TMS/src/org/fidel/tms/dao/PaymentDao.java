package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.Payment;

public interface PaymentDao {
	
	public List<FrightOrder> getUnprocessedPaymentList();

	public List<FrightOrder> getUnprocessedPaymentListByTruckType(String truck_type);
	
	public List<FrightOrder> getInprocessPaymentList();

	public List<FrightOrder> getInprocessPaymentListByTruckType(String truck_type);
	
	public List<FrightOrder> getFrightOrderInfo(int fo_id);
	
	public boolean updateFrightOrderPaymentRequestDate(int fo_id);
	
	public boolean saveFrightOrderPayment(List<Integer> foid, Payment payment);
	
	public List<FrightOrder> getCollectedPaymentReport(FrightOrder frightOrder);

}
