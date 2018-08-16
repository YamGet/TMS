package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.service.FrightOrderService;
import org.fidel.tms.service.FrightOrderTripDetailService;
import org.fidel.tms.service.PaymentService;
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
@RequestMapping("foDetail")
public class FrightOrderTripDetailController {
	
	@Autowired
	FrightOrderTripDetailService frightOrderTripDetailService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	FrightOrderService frightOrderService;

	@RequestMapping(value="/saveFrightOrderDetailInformation", method=RequestMethod.GET)
	public ModelAndView saveFrightOrderDetailInformation(@ModelAttribute("foDetail") FrightOrderTripDetail foDetail, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = frightOrderTripDetailService.saveFrightOrderTripDetail(foDetail);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderDetail/frightOrderTripDetailInformation");
		
		modelandview.addObject("foDetail", foDetail);
		
		if(rslt){
			modelandview.addObject("successMessage", "Fright order trip detail successfully saved.");
		} else {
			modelandview.addObject("errorMessage", "Fright order trip detail is not saved.");
		}
						
		return modelandview;
	} 
	
	@RequestMapping(value="/updateFrightOrderDetailInformation", method=RequestMethod.GET)
	public ModelAndView updateFrightOrderDetailInformation(@ModelAttribute("foDetail") FrightOrderTripDetail foDetail, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = frightOrderTripDetailService.updateFrightOrderTripDetail(foDetail);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderDetail/frightOrderTripDetailInformation");
		
		modelandview.addObject("foDetail", foDetail);
		
		if(rslt){
			modelandview.addObject("successMessage", "Fright order trip detail successfully updated.");
		} else {
			modelandview.addObject("errorMessage", "Fright order trip detail is not updated.");
		}
						
		return modelandview;
	} 
	
	@RequestMapping(value="/savePaymentAppointmentDate", method=RequestMethod.GET)
	public ModelAndView savePaymentAppointmentDate(@ModelAttribute("foDetail") FrightOrderTripDetail foDetail, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = frightOrderTripDetailService.updateFrightOrderPaymentAppointmentDate(foDetail);
		
		if(rslt){
		
			List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
			
			ModelAndView model = new ModelAndView("payment/inProcessFrightOrder");
			
			model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
			
			return model;
		
		} else {
			
			List<FrightOrder> getFrightOrderInfo = paymentService.getFrightOrderInfo(foDetail.getFo_id());
			
			ModelAndView model = new ModelAndView("payment/frightOrderPaymentAppointmentAddForm");
			
			model.addObject("getFrightOrderInfo", getFrightOrderInfo);
			
			return model;
		}
	}
	
	@RequestMapping(value="/updateFrightOrderPrice", method=RequestMethod.GET)
	public ModelAndView updateFrightOrderPrice(@ModelAttribute("foDetail") FrightOrderTripDetail foDetail, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = frightOrderTripDetailService.updateFrightOrderTripDetailPrice(foDetail);
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
		
		ModelAndView model = new ModelAndView("payment/inProcessFrightOrder");
		
		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		
		if(!rslt){
			
			model.addObject("errorMessage", "The fright order price is not updated.");
			
		} else {
			
			model.addObject("successMessage", "The fright order price is successfully updated.");
		}
		
		return model;		
		 
	}
	
	@RequestMapping(value="/getFrightOrderClosingSecureUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFrightOrderClosingSecureUpdateForm(@ModelAttribute("foDetail") FrightOrderTripDetail foDetail, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		List<FrightOrderTripDetail> rslt = frightOrderTripDetailService.getFrightOrderTripDetail(foDetail.getFo_id());
		
		foDetail.setClient_organization(rslt.get(0).getClient_organization());
		foDetail.setDispatch_doc_ref_no(rslt.get(0).getDispatch_doc_ref_no());
		foDetail.setDelivery_doc_ref_no(rslt.get(0).getDelivery_doc_ref_no());
		foDetail.setDelivered_quantity(rslt.get(0).getDelivered_quantity());
		foDetail.setDelivery_date(rslt.get(0).getDelivery_date());
		foDetail.setFright_note(rslt.get(0).getFright_note());
				
		ModelAndView modelandview = new ModelAndView("admin/securePage/freight_order_closing_information");
		
		return modelandview;
	}
	
}
