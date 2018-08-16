package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.AdvancePaymentDao;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.service.AdvancePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvancePaymentServiceImpl implements AdvancePaymentService {
	
	@Autowired
	AdvancePaymentDao advancePaymentDao;

	@Override
	public boolean insertAdvancePayment(AdvancePayment advancePayment) {
		
		return advancePaymentDao.insertAdvancePayment(advancePayment);
	}

	@Override
	public List<AdvancePayment> getAdvancePayment(int fo_id, int ap_id) {
		
		return advancePaymentDao.getAdvancePayment(fo_id, ap_id);
	}

	@Override
	public boolean updateAdvancePayment(AdvancePayment advancePayment) {
		
		return advancePaymentDao.updateAdvancePayment(advancePayment);
	}

	@Override
	public boolean insertAdvancePaymentAdditionalAmount(AdvancePayment advancePayment) {
		
		return advancePaymentDao.insertAdvancePaymentAdditionalAmount(advancePayment);
	}

	@Override
	public List<AdvancePayment> getAdvancePaymentById(AdvancePayment advancePayment) {
		
		return advancePaymentDao.getAdvancePaymentById(advancePayment);
	}

	@Override
	public List<AdvancePayment> getAdvancePaymentByFOId(int fo_id) {
		
		return advancePaymentDao.getAdvancePaymentByFOId(fo_id);
	}
	
	@Override
	public List<AdvancePayment> getAdvancePaymentWithCouponTransferByFOId(int fo_id) {
		
		return advancePaymentDao.getAdvancePaymentWithCouponTransferByFOId(fo_id);
	}

	@Override
	public boolean saveRemainingBalanceReturn(AdvancePayment advancePayment) {
		
		return advancePaymentDao.saveRemainingBalanceReturn(advancePayment);
	}

	@Override
	public boolean saveRemainingBalanceTransfer(AdvancePayment advancePayment) {
		
		return advancePaymentDao.saveRemainingBalanceTransfer(advancePayment);
	}

	@Override
	public boolean saveAdditionalExpenseAmount(AdvancePayment advancePayment) {
		
		return advancePaymentDao.saveAdditionalExpenseAmount(advancePayment);
	}
	
	@Override
	public boolean updateAdvancePaymentCouponStatus(AdvancePayment advancePayment) {
		
		return advancePaymentDao.updateAdvancePaymentCouponStatus(advancePayment);
	}

}
