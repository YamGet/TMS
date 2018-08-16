package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.FrightOrderDao;
import org.fidel.tms.dao.FrightOrderTripDetailDao;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FrightOrderTripDetailService implements org.fidel.tms.service.FrightOrderTripDetailService {

	@Autowired
	FrightOrderTripDetailDao frightOrderTripDetailDao;
	@Autowired
	FrightOrderDao frightOrderDao;
	
	@Override
	public boolean saveFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail) {
		
		return frightOrderTripDetailDao.saveFrightOrderTripDetail(frightOrderTripDetail);
	}

	@Override
	public List<FrightOrderTripDetail> getFrightOrderTripDetail(int fo_id) {
		
		return frightOrderTripDetailDao.getFrightOrderTripDetail(fo_id);
	}

	@Override
	public boolean closeFrightOrder(FrightOrderTripDetail frightOrderTripDetail) {
		
		return frightOrderTripDetailDao.closeFrightOrder(frightOrderTripDetail);
	}
	
	@Override
	public boolean updateFrightOrderPaymentAppointmentDate(FrightOrderTripDetail foDetail) {
		
		return frightOrderTripDetailDao.updateFrightOrderPaymentAppointmentDate(foDetail);
	}

	@Override
	public void openTruckTrailAvailiability(FrightOrderTripDetail frightOrderTripDetail) {
		
		List<FrightOrder> foList = frightOrderDao.getFrightOrder(frightOrderTripDetail.getFo_id());
		
		frightOrderTripDetailDao.openTruckTrailAvailiability(foList.get(0).getTri_id(), foList.get(0).getTli_id());
	}

	@Override
	public List<FrightOrderTripDetail> getFrightOrderTripDetailById(int fotd_id) {
		
		return frightOrderTripDetailDao.getFrightOrderTripDetailById(fotd_id);
	}

	@Override
	public boolean updateFrightOrderTripDetailPrice(FrightOrderTripDetail frightOrderTripDetail) {
		
		return frightOrderTripDetailDao.updateFrightOrderTripDetailPrice(frightOrderTripDetail);
	}

	@Override
	public boolean updateFrightOrderTripDetail(FrightOrderTripDetail frightOrderTripDetail) {
		
		return frightOrderTripDetailDao.updateFrightOrderTripDetail(frightOrderTripDetail);
	}

}
