package org.fidel.tms.controller;

import org.fidel.tms.model.Report;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("report")
public class ReportController {
	
	@RequestMapping(value="/getReportTemplate", method=RequestMethod.POST)
	public ModelAndView reportTemplate(@ModelAttribute("report") Report report, BindingResult result) {
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = new ModelAndView("report/reportTemplate");
		
		return modelandview;
	}
	
	@RequestMapping(value="/reportFilterFormPerTruck", method=RequestMethod.POST)
	public ModelAndView reportFilterFormPerTruck(@ModelAttribute("report") Report report, BindingResult result) {
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = new ModelAndView("report/expense/reportFilterFormPerTruck");
		
		return modelandview;
	}
	
	@RequestMapping(value="/reportFilterFormPerFon", method=RequestMethod.POST)
	public ModelAndView reportFilterFormPerFon(@ModelAttribute("report") Report report, BindingResult result) {
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = new ModelAndView("report/expense/reportFilterFormPerFon");
		
		return modelandview;
	}
}
