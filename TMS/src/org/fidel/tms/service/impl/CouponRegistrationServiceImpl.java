package org.fidel.tms.service.impl;

import java.util.List;

import org.fidel.tms.dao.CouponRegistrationDao;
import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;
import org.fidel.tms.service.CouponRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponRegistrationServiceImpl implements CouponRegistrationService {
	
	@Autowired
	CouponRegistrationDao couponRegistrationDao;

	@Override
	public List<CouponRegistration> getActiveCoupon() {
		
		return couponRegistrationDao.getActiveCoupon();
	}

	@Override
	public boolean saveSingleCoupon(CouponRegistration coupon) {
		
		return couponRegistrationDao.saveSingleCoupon(coupon);
	}

	@Override
	public boolean saveMultipleCoupons(CouponRegistration coupon) {
				
		return couponRegistrationDao.saveMultipleCoupons(coupon);
	}

	@Override
	public boolean updateCoupon(CouponRegistration coupon) {
		
		return couponRegistrationDao.updateCoupon(coupon);
	}

	@Override
	public List<CouponRegistration> getSelectedActiveCoupon(CouponRegistration coupon) {
		
		return couponRegistrationDao.getSelectedActiveCoupon(coupon);
	}

	@Override
	public List<CouponRegistration> getRelatedCouponList(int fo_id) {
		
		return couponRegistrationDao.getRelatedCouponList(fo_id);
	}

	@Override
	public boolean validateCoupon(CouponRegistration coupon) {
		
		return couponRegistrationDao.validateCoupon(coupon);
	}

	@Override
	public List<CouponRegistration> getCouponCategory() {
		
		return couponRegistrationDao.getCouponCategory();
	}

	@Override
	public List<CouponDissemination> getConsumedCoupon(CouponRegistration coupon) {

		return couponRegistrationDao.getConsumedCoupon(coupon);
	}

	@Override
	public List<CouponDissemination> getCouponTotalConsumed(CouponRegistration coupon) {
		
		return couponRegistrationDao.getCouponTotalConsumed(coupon);
	}

}
