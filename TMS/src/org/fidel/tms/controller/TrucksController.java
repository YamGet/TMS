package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.TruckAvailabilityStatus;
import org.fidel.tms.model.TruckInformation;
import org.fidel.tms.service.TrucksService;
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
@RequestMapping("trucks")
public class TrucksController {
	
	@Autowired
	TrucksService trucksService;
	
	@RequestMapping(value="/getTrucksInformation", method=RequestMethod.GET)
	public ModelAndView getTrucksInformation(@ModelAttribute("trucks") TruckInformation trucks, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<TruckInformation> trucksList = trucksService.getAllTrucks();
		
		ModelAndView model = new ModelAndView("admin/trucks/trucksTemplate");
		
		model.addObject("trucksList", trucksList);
		
		return model;
	} 
	
	@RequestMapping(value="/getTrucksInformationUpdateForm", method=RequestMethod.GET)
	public ModelAndView getTrucksInformationUpdateForm(@ModelAttribute("trucks") TruckInformation trucks, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/trucks/trucksUpdateForm");
				
		return model;
	}  
	
	@RequestMapping(value="/saveTruckInformation", method=RequestMethod.GET)
	public ModelAndView saveTruckInformation(@ModelAttribute("trucks") TruckInformation trucks, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}

		boolean rslt = trucksService.saveTrucks(trucks);
		
		List<TruckInformation> trucksList = trucksService.getAllTrucks();
		
		ModelAndView model =  getTrucksInformation(trucks, result);
		
		model.addObject("trucksList", trucksList);
		
		if(rslt){
			
			TruckAvailabilityStatus tas = new TruckAvailabilityStatus();			
			
			trucksService.insertTrucksAvailability(tas);
			
			trucks.setshanci_no("");
			trucks.setTruck_plate_no("");
			trucks.setSide_no("");
			trucks.setTruck_model("");
			trucks.setTruck_owner("");
			trucks.setloading_capacity("");			
			
		} else {
		
			model.addObject("errorMessage", "The truck already exist (check your Chanssis number or plate number).");
		}
		
		return model;
	}
	
	@RequestMapping(value="/updateTruckInformation", method=RequestMethod.GET)
	public ModelAndView updateTruckInformation(@ModelAttribute("trucks") TruckInformation trucks, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}		
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}

		boolean rslt = trucksService.updateTrucks(trucks);
		
		List<TruckInformation> trucksList = trucksService.getAllTrucks();
		
		ModelAndView model =  getTrucksInformation(trucks, result);
		
		model.addObject("trucksList", trucksList);
		
		if(rslt){
			
			trucks.setshanci_no("");
			trucks.setTruck_plate_no("");
			trucks.setSide_no("");
			trucks.setTruck_model("");
			trucks.setTruck_owner("");
			trucks.setloading_capacity("");			
			
		} else {
		
			model.addObject("errorMessage", "The truck already exist.");			
		}
		
		return model;
	}

	
}
