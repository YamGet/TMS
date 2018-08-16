package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.Drivers;
import org.fidel.tms.model.Users;
import org.fidel.tms.service.DriversService;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("drivers")
public class DriversController {
	
	@Autowired
	DriversService driverServices;
	
	@RequestMapping(value="/getDriversInformation", method=RequestMethod.GET)
	public ModelAndView getDriversInformation(@ModelAttribute("drivers") Drivers drivers, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Drivers> driversList = driverServices.getAllDrivers();
		
		ModelAndView model = new ModelAndView("admin/drivers/driversTemplate");
		
		model.addObject("driversList", driversList);
		
		return model;
	} 
	
	@RequestMapping(value="/getDriversInformationUpdateForm", method=RequestMethod.GET)
	public ModelAndView getDriversInformationUpdateForm(@ModelAttribute("drivers") Drivers drivers, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/drivers/driversUpdateForm");
		
		model.addObject("drivers", drivers);
		
		return model;
	}  
	
	@RequestMapping(value="/saveDriversInformation", method=RequestMethod.POST)
	public ModelAndView saveDriversInformation(@ModelAttribute("drivers") Drivers drivers, BindingResult result, Errors errors){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		if(drivers.getFname().isEmpty()){
			
			errors.rejectValue("fname", "First Name is required.");
		}
		
		if(result.hasFieldErrors()){
			
			List<Drivers> driversList = driverServices.getAllDrivers();
			
			ModelAndView model = new ModelAndView("admin/drivers/driversTemplate");
			
			model.addObject("driversList", driversList);
			
			return model;
		}
		
		boolean driverSave = driverServices.saveDrivers(drivers);
		
		List<Drivers> driversList = driverServices.getAllDrivers();
		
		ModelAndView model = new ModelAndView("admin/drivers/driversTemplate");
		
		if(driverSave){
			
			drivers.setFname("");
			drivers.setMname("");
			drivers.setGname("");
			drivers.setDriving_license_no("");
			drivers.setLocal_phone("");
			drivers.setAbroad_phone("");
						
		} else {			
			
			model.addObject("errorMessage", "The driver already exist.");
		}
		
		model.addObject("driversList", driversList);
		
		return model;
	}
	
	@RequestMapping(value="/updateDriversInformation", method=RequestMethod.POST)
	public ModelAndView updateDriversInformation(@ModelAttribute("drivers") Drivers drivers, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean driverUpdate = driverServices.updateDrivers(drivers);
		
		List<Drivers> driversList = driverServices.getAllDrivers();
		
		ModelAndView model = new ModelAndView("admin/drivers/driversTemplate");
		
		model.addObject("driversList", driversList);
		
		if(driverUpdate){
			
			drivers.setFname("");
			drivers.setMname("");
			drivers.setGname("");
			drivers.setDriving_license_no("");
			drivers.setLocal_phone("");
			drivers.setAbroad_phone("");
						
		} else {
			
			model.addObject("drivers", drivers);
			model.addObject("errorMessage", "The driver already exist.");
					
		}
		
		return model;
		
	}

}
