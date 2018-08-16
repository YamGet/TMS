package org.fidel.tms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fidel.tms.model.CouponDissemination;
import org.fidel.tms.model.CouponRegistration;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FuelCompany;
import org.fidel.tms.service.CouponRegistrationService;
import org.fidel.tms.service.FuelCompanyService;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("couponReg")
public class CouponRegistrationController {
	
	@Autowired
	CouponRegistrationService couponRegistrationService;
	@Autowired
	FuelCompanyService fuelCompanyService;
	
	@RequestMapping(value="/getCouponRegistrationTemplate", method=RequestMethod.POST)
	public ModelAndView getCouponRegistrationTemplate(@ModelAttribute("couponReg") CouponRegistration couponReg, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}

		List<FuelCompany> fuelCompanyList = fuelCompanyService.getActiveFuelCompanyList();
		
		List<CouponRegistration> couponList = couponRegistrationService.getActiveCoupon();
		
		ModelAndView model = new ModelAndView("couponRegistration/couponRegistrationTemplate");
		
		model.addObject("couponList", couponList);

		model.addObject("fuelCompanyList", fuelCompanyList);
		
		return model;
	} 
	
	@RequestMapping(value="/saveCoupon", method=RequestMethod.GET)
	public ModelAndView saveCoupon(@ModelAttribute("couponReg") CouponRegistration couponReg, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = false;
		
		if(couponReg.getForm_type().equals("single")){
			
			rslt = couponRegistrationService.saveSingleCoupon(couponReg);
		}
		
		if(couponReg.getForm_type().equals("multiple")){
			
			rslt = couponRegistrationService.saveMultipleCoupons(couponReg);
		}
		
		List<CouponRegistration> couponList = couponRegistrationService.getActiveCoupon();
		
		ModelAndView model = new ModelAndView("couponRegistration/couponRegistrationTemplate");
		
		model.addObject("couponList", couponList);
		
		if(!rslt && couponReg.getForm_type().equals("single")){
			
			model.addObject("errorMessage", "The coupon is not registered. Please try again.");
			
		} else {
			
			couponReg.setOil_company("");
			couponReg.setAmount("");
			couponReg.setC_serial_no("");
		}
		
		if(!rslt && couponReg.getForm_type().equals("multiple")){
			
			model.addObject("errorMessage", "Some coupons are not registered. It might be duplicate.");
			
			couponReg.setOil_company("");
			couponReg.setAmount("");
			couponReg.setNo_of_coupon("");
			couponReg.setC_serial_no_from("");
			couponReg.setC_serial_no_to("");

		} else {
			
			couponReg.setOil_company("");
			couponReg.setAmount("");
			couponReg.setNo_of_coupon("");
			couponReg.setC_serial_no_from("");
			couponReg.setC_serial_no_to("");
		}
		
		return model;
	} 
	
	@RequestMapping(value="/getCouponUpdateForm", method=RequestMethod.GET)
	public ModelAndView getCouponUpdateForm(@ModelAttribute("couponReg") CouponRegistration couponReg, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}

		List<FuelCompany> fuelCompanyList = fuelCompanyService.getActiveFuelCompanyList();
		
		ModelAndView model = new ModelAndView("couponRegistration/couponRegistrationUpdateForm");
		
		model.addObject("fuelCompanyList", fuelCompanyList);
		
		return model;
	}
	
	@RequestMapping(value="/updateCoupon", method=RequestMethod.GET)
	public ModelAndView updateCoupon(@ModelAttribute("couponReg") CouponRegistration couponReg, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		///>>> Validate Coupon
		boolean check = couponRegistrationService.validateCoupon(couponReg);
		
		if(check){
			
			List<CouponRegistration> couponList = couponRegistrationService.getActiveCoupon();
			
			ModelAndView model = new ModelAndView("couponRegistration/couponRegistrationTemplate");
			
			model.addObject("couponList", couponList);
			
			model.addObject("errorMessage", "The coupon is not updated. It already exist.");
			
			return model;
		}
		///>>> Validate Coupon
		
		boolean rslt = couponRegistrationService.updateCoupon(couponReg);
		
		List<CouponRegistration> couponList = couponRegistrationService.getActiveCoupon();
		
		ModelAndView model = new ModelAndView("couponRegistration/couponRegistrationTemplate");
		
		model.addObject("couponList", couponList);
		
		if(!rslt){
			
			model.addObject("errorMessage", "The coupon is not updated. Please try again.");
			
		} else {
			
			couponReg.setOil_company("");
			couponReg.setAmount("");
			couponReg.setC_serial_no("");
		}
				
		return model;
	}
	
	List<List<CouponRegistration>> selCouponList = new ArrayList<List<CouponRegistration>>();
	
	@RequestMapping(value="/getCouponTypeList", method=RequestMethod.GET)
	public ModelAndView getCouponTypeList(@ModelAttribute("couponReg") CouponRegistration couponReg, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<CouponRegistration> couponList = couponRegistrationService.getSelectedActiveCoupon(couponReg);
		
		selCouponList.add(couponList);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderSelectedCouponTypeList");
		
		modelandview.addObject("couponList", couponList);
						
		return modelandview;
	}
	
	@RequestMapping(value="/getCouponUsageFilterForm", method=RequestMethod.GET)
	public ModelAndView getCouponUsageFilterForm(@ModelAttribute("couponReg") CouponRegistration couponReg, BindingResult result){
		
		List<CouponRegistration> couponCategory = couponRegistrationService.getCouponCategory();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("couponCategory", couponCategory);
		
		ModelAndView modelandview = new ModelAndView("report/coupon/couponUsageFilterForm");
		
		modelandview.addObject("map", map);
		
		return modelandview;
	}

	@RequestMapping(value="/rpt_generateCouponConsumptionReport", method=RequestMethod.GET)
	public ModelAndView rpt_generateCouponConsumptionReport(@ModelAttribute("couponReg") CouponRegistration couponReg, BindingResult result){
		
		///>>> calculate total coupon consumption <<<///
		List<CouponDissemination> totalConsumedCoupon = couponRegistrationService.getCouponTotalConsumed(couponReg);
		
		int total_coupon_consumption = 0;
		
		for(int i = 0; i < totalConsumedCoupon.size(); i++){
			total_coupon_consumption += Integer.parseInt(totalConsumedCoupon.get(i).getCouponRegistration().getAmount());
		}
				
		String total_coupon_amount = couponReg.getAmount();
		
		///>>> calculate coupon consumption between the gap <<<///
		List<CouponDissemination> consumedCoupon = couponRegistrationService.getConsumedCoupon(couponReg);
		
		int total_consumption = 0;
		
		for(int i = 0; i < consumedCoupon.size(); i++){
			total_consumption += Integer.parseInt(consumedCoupon.get(i).getCouponRegistration().getAmount());
		}
		
		int remain_balance = Integer.parseInt(total_coupon_amount)- total_coupon_consumption;
		
		ModelAndView modelandview = new ModelAndView("report/coupon/couponUsageSearchResult");
		
		modelandview.addObject("consumedCoupon", consumedCoupon);
		
		modelandview.addObject("total_coupon_amount", total_coupon_amount);		
		modelandview.addObject("total_coupon_consumption", total_coupon_consumption);
		modelandview.addObject("remain_balance", remain_balance);
		
		modelandview.addObject("total_consumption", total_consumption);
		
		return modelandview;
	}
}
