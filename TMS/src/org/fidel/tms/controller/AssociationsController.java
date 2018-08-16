package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.Associations;
import org.fidel.tms.service.AssociationsService;
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
@RequestMapping("associations")
public class AssociationsController {
	
	@Autowired
	AssociationsService associationsService;
	
	@RequestMapping(value="/getAssociationInformation", method=RequestMethod.GET)
	public ModelAndView getAssociationInformation(@ModelAttribute("associations") Associations associations, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Associations> associationsList = associationsService.getAllAssociations();
		
		ModelAndView model = new ModelAndView("admin/associations/associationsTemplate");
		
		model.addObject("associationList", associationsList);
		
		return model;
	}
	
	@RequestMapping(value="/getAssociationUpdateForm", method=RequestMethod.GET)
	public ModelAndView getAssociationUpdateForm(@ModelAttribute("associations") Associations associations, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/associations/associationsUpdateForm");
		
		model.addObject("associations", associations);
		
		return model;
	}
	
	@RequestMapping(value="/updateAssociationInformation", method=RequestMethod.GET)
	public ModelAndView updateAssociationInformation(@ModelAttribute("associations") Associations associations, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = associationsService.updateAssociations(associations);
		
		if(rslt){
			
			return getAssociationInformation(associations, result);
		
		} else {
			
			List<Associations> associationsList = associationsService.getAllAssociations();
			
			ModelAndView model = new ModelAndView("admin/associations/associationsTemplate");
			
			model.addObject("associationList", associationsList);
			model.addObject("associations", associations);
			model.addObject("errorMessage", "Association already exist");
			
			return model;			
		}
	}
	
	@RequestMapping(value="/saveAssociationInformation", method=RequestMethod.GET)
	public ModelAndView saveAssociationInformation(@ModelAttribute("associations") Associations associations, BindingResult result, Errors error){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = associationsService.saveAssociations(associations);
		
		if(rslt){
			
			return getAssociationInformation(associations, result);
		
		} else {
			
			List<Associations> associationsList = associationsService.getAllAssociations();
			
			ModelAndView model = new ModelAndView("admin/associations/associationsTemplate");
			
			model.addObject("associationList", associationsList);
			model.addObject("associations", associations);
			model.addObject("errorMessage", "Association already exist");
			
			return model;			
		}
	}

}
