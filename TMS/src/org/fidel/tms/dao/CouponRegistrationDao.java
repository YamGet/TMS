package org.fidel.tms.dao;

import java.util.List;

import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;

public interface CouponRegistrationDao {
	
	public List<CouponRegistration> getActiveCoupon();
	
	public List<CouponRegistration> getSelectedActiveCoupon(CouponRegistration coupon);
	
	public boolean saveSingleCoupon(CouponRegistration coupon);
	
	public boolean saveMultipleCoupons(CouponRegistration coupon);
	
	public boolean updateCoupon(CouponRegistration coupon);
	
	public boolean validateCoupon(CouponRegistration coupon);
	
	public boolean checkCoupon(String oil_company, String c_serial_no);
	
	public boolean checkCoupon(String oil_company, String c_serial_no, int c_id);
	
	public List<CouponRegistration> getRelatedCouponList(int fo_id);
	
	public List<CouponRegistration> getCouponCategory();
	
	public List<CouponDissemination> getCouponTotalConsumed(CouponRegistration coupon);
	
	public List<CouponDissemination> getConsumedCoupon(CouponRegistration coupon);

}
