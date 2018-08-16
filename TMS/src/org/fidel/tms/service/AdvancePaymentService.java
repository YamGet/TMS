package org.fidel.tms.service;

import java.util.List;

import org.fidel.tms.model.AdvancePayment;

public interface AdvancePaymentService {
	
	public boolean insertAdvancePayment(AdvancePayment advancePayment);
	
	public boolean updateAdvancePayment(AdvancePayment advancePayment);
	
	public List<AdvancePayment> getAdvancePayment(int fo_id, int ap_id);
	
	public List<AdvancePayment> getAdvancePaymentById(AdvancePayment advancePayment);
	
	public List<AdvancePayment> getAdvancePaymentByFOId(int fo_id);
	
	public List<AdvancePayment> getAdvancePaymentWithCouponTransferByFOId(int fo_id);
	
	public boolean insertAdvancePaymentAdditionalAmount(AdvancePayment advancePayment);
	
	public boolean saveRemainingBalanceReturn(AdvancePayment advancePayment);
	
	public boolean saveRemainingBalanceTransfer(AdvancePayment advancePayment);
	
	public boolean saveAdditionalExpenseAmount(AdvancePayment advancePayment);
	
	public boolean updateAdvancePaymentCouponStatus(AdvancePayment advancePayment);

}
