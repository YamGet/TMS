package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.FuelCompany;
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
@RequestMapping("fuelcompany")
public class FuelCompanyController {
	
	@Autowired
	private FuelCompanyService fuelCompanyService;
	
	@RequestMapping(value="/getFuelCompanyTemplate", method=RequestMethod.GET)
	public ModelAndView getFuelCompanyTemplate(@ModelAttribute("fuelcompany") FuelCompany fuelCompany, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}		
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<FuelCompany> getFuelCompanyList = fuelCompanyService.getAllFuelCompanyList();
		
		ModelAndView model = new ModelAndView("admin/fuelCompany/fuelCompanyTemplate");
		
		model.addObject("getFuelCompanyList", getFuelCompanyList);
		
		return model;
	}
		
	@RequestMapping(value="/saveFuelCompany", method=RequestMethod.GET)
	public ModelAndView saveFuelCompany(@ModelAttribute("fuelcompany") FuelCompany fuelCompany, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}		
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		boolean rslt = fuelCompanyService.saveFuelCompany(fuelCompany);
		
		
		List<FuelCompany> getFuelCompanyList = fuelCompanyService.getAllFuelCompanyList();
		
		ModelAndView model = new ModelAndView("admin/fuelCompany/fuelCompanyTemplate");
		
		model.addObject("getFuelCompanyList", getFuelCompanyList);
		
		if(rslt){
			
			model.addObject("successMessage", "Fuel Company successfully saved.");
		} else {
			
			model.addObject("errorMessage", "Fuel Company is not saved. Please try again later.");
		}
		
		return model;
	}
	
	@RequestMapping(value="/getFuelCompanyUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFuelCompanyUpdateForm(@ModelAttribute("fuelcompany") FuelCompany fuelCompany, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}		
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		ModelAndView model = new ModelAndView("admin/fuelCompany/fuelCompanyUpdateForm");
		
		return model;
	}
	
	@RequestMapping(value="/updateFuelCompany", method=RequestMethod.GET)
	public ModelAndView updateFuelCompany(@ModelAttribute("fuelcompany") FuelCompany fuelCompany, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}		
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		boolean rslt = fuelCompanyService.updateFuelCompany(fuelCompany);
		
		
		List<FuelCompany> getFuelCompanyList = fuelCompanyService.getAllFuelCompanyList();
		
		ModelAndView model = new ModelAndView("admin/fuelCompany/fuelCompanyTemplate");
		
		model.addObject("getFuelCompanyList", getFuelCompanyList);
		
		if(rslt){
			
			model.addObject("successMessage", "Fuel Company successfully updated.");
		} else {
			
			model.addObject("errorMessage", "Fuel Company is not updated. Please try again later.");
		}
		
		return model;
	}

}
