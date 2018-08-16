package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.TrailAvailabilityStatus;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.service.TrailsService;
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
@RequestMapping("trails")
public class TrailsController {
	
	@Autowired
	TrailsService trailsService;
	
	@RequestMapping(value="/getTrailsInformation", method=RequestMethod.GET)
	public ModelAndView getTrailsInformation(@ModelAttribute("trails") TrailInformation trails, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<TrailInformation> trailsList = trailsService.getAllTrails();
		
		ModelAndView model = new ModelAndView("admin/trails/trailsTemplate");
		
		model.addObject("trailsList", trailsList);
		
		return model;
	}  
	
	@RequestMapping(value="/getTrailsInformationUpdateForm", method=RequestMethod.GET)
	public ModelAndView getTrailsInformationUpdateForm(@ModelAttribute("trails") TrailInformation trails, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/trails/trailsUpdateForm");
		
		return model;
	} 
	
	@RequestMapping(value="/saveTrailInformation", method=RequestMethod.GET)
	public ModelAndView saveTrailInformation(@ModelAttribute("trails") TrailInformation trails, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = trailsService.saveTrailInformation(trails);
		
		List<TrailInformation> trailsList = trailsService.getAllTrails();
		
		ModelAndView model = new ModelAndView("admin/trails/trailsTemplate");
		
		model.addObject("trailsList", trailsList);
		
		if(!rslt){
			
			model.addObject("errorMessage", "Trails already exist.");
			
		}  else {
			
			TrailAvailabilityStatus tas = new TrailAvailabilityStatus();
			trailsService.insertTrucksAvailability(tas);
			
			trails.setTrail_plate_no("");
			trails.setLoading_capacity("");
			trails.setTrail_owner("");
			trails.setTrail_status("");
		}
		
		return model;
	}
	
	@RequestMapping(value="/updateTrailInformation", method=RequestMethod.GET)
	public ModelAndView upateTrailInformation(@ModelAttribute("trails") TrailInformation trails, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = trailsService.updateTrailInformation(trails);
		
		List<TrailInformation> trailsList = trailsService.getAllTrails();
		
		ModelAndView model = new ModelAndView("admin/trails/trailsTemplate");
		
		model.addObject("trailsList", trailsList);
		
		if(!rslt){
			
			model.addObject("errorMessage", "Trails already exist.");
			
		} else {
			
			trails.setTrail_plate_no("");
			trails.setLoading_capacity("");
			trails.setTrail_owner("");
			trails.setTrail_status("");
		}
		
		return model;
	}

}
