package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.FuelCard;
import org.fidel.tms.model.FuelCompany;
import org.fidel.tms.service.FuelCardService;
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
@RequestMapping("fuelcard")
public class FuelCardController {	
	
	@Autowired
	FuelCardService fuelCardService;

	@Autowired
	FuelCompanyService fuelCompanyService;
	
	@RequestMapping(value="/getFuelCardInformation", method=RequestMethod.GET)
	public ModelAndView getFuelCardInformation(@ModelAttribute("fuelcard") FuelCard fuelCard, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}		
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}

		List<FuelCompany> fuelCompanyList = fuelCompanyService.getActiveFuelCompanyList();
		
		List<FuelCard> getFuelCardList = fuelCardService.getAllFuelCardList();
		
		ModelAndView model = new ModelAndView("admin/fuelCard/fuelCardTemplate");
		
		model.addObject("fuelCardList", getFuelCardList);

		model.addObject("fuelCompanyList", fuelCompanyList);
		
		return model;
	}

	@RequestMapping(value="/saveFuelCard", method=RequestMethod.GET)
	public ModelAndView saveFuelCard(@ModelAttribute("fuelcard") FuelCard fuelCard, BindingResult result){

		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = fuelCardService.saveFuelCard(fuelCard);
		
		ModelAndView model = new ModelAndView("admin/fuelCard/fuelCardTemplate");
		
		List<FuelCard> getFuelCardList = fuelCardService.getAllFuelCardList();
		
		model.addObject("fuelCardList", getFuelCardList);
		
		if(!rslt){
			
			model.addObject("errorMessage", "Fuel card information already exist.");
			
		} else {
			
			fuelCard.setFc_company("");
			fuelCard.setFc_no("");						
		}
		
		return model;
	}
	
	@RequestMapping(value="/getFuelCardUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFuelCardUpdateForm(@ModelAttribute("fuelcard") FuelCard fuelCard, BindingResult result){

		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}

		List<FuelCompany> fuelCompanyList = fuelCompanyService.getActiveFuelCompanyList();
		
		ModelAndView model = new ModelAndView("admin/fuelCard/fuelCardUpdateForm");

		model.addObject("fuelCompanyList", fuelCompanyList);
		
		return model;
	}
	
	@RequestMapping(value="/updateFuelCard", method=RequestMethod.GET)
	public ModelAndView updateFuelCard(@ModelAttribute("fuelcard") FuelCard fuelCard, BindingResult result){

		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = fuelCardService.updateFuelCard(fuelCard);
		
		if(rslt){
			
			fuelCard.setFc_company("");
			fuelCard.setFc_no("");
		}
		
		ModelAndView model = new ModelAndView("admin/fuelCard/fuelCardTemplate");
		
		List<FuelCard> getFuelCardList = fuelCardService.getAllFuelCardList();
		
		model.addObject("fuelCardList", getFuelCardList);
		
		if(!rslt){
			
			model.addObject("errorMessage", "Fuel card information already exist.");
		} 
		
		return model;
	}
	
}
