package org.fidel.tms.controller;

import java.util.ArrayList;
import java.util.List;

import org.fidel.tms.model.SystemModule;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.service.SystemModuleService;
import org.fidel.tms.service.UsersRoleService;
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
@RequestMapping("usersRole")
public class UsersRoleController {
	
	@Autowired
	UsersRoleService userRoleService;
	@Autowired
	SystemModuleService systemModuleService;
	
	@RequestMapping(value="/getUsersRoleInformation", method=RequestMethod.GET)
	public ModelAndView getUsersRoleInformation(@ModelAttribute("usersRole") UsersRole usersRole, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<UsersRole> usersRoleList = userRoleService.getAllUsersRole();
		
		ModelAndView model = new ModelAndView("admin/usersRole/usersRoleTemplate");
		
		model.addObject("usersRoleList", usersRoleList);
		
		return model;
	} 
	
	@RequestMapping(value="/saveUsersRoleInformation", method=RequestMethod.GET)
	public ModelAndView saveUsersRoleInformation(@ModelAttribute("usersRole") UsersRole usersRole, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = userRoleService.saveUsersRole(usersRole);
		
		List<UsersRole> usersRoleList = userRoleService.getAllUsersRole();
		
		ModelAndView model = new ModelAndView("admin/usersRole/usersRoleTemplate");
		
		model.addObject("usersRoleList", usersRoleList);
		
		if(!rslt){
			
			model.addObject("errorMessage", "Users Role already exist.");
			
		} else {
			
			usersRole.setUserrole_name("");
		}
		
		return model;
	}
	
	@RequestMapping(value="/getUsersRoleUpdateForm", method=RequestMethod.GET)
	public ModelAndView getUsersRoleUpdateForm(@ModelAttribute("usersRole") UsersRole usersRole, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/usersRole/usersRoleUpdateForm");
		
		return model;
	} 
	
	@RequestMapping(value="/updateUsersRoleInformation", method=RequestMethod.GET)
	public ModelAndView updateUsersRoleInformation(@ModelAttribute("usersRole") UsersRole usersRole, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = userRoleService.updateUsersRole(usersRole);
		
		List<UsersRole> usersRoleList = userRoleService.getAllUsersRole();
		
		ModelAndView model = new ModelAndView("admin/usersRole/usersRoleTemplate");
		
		model.addObject("usersRoleList", usersRoleList);
		
		if(!rslt){
			
			model.addObject("errorMessage", "Users Role already exist.");
			
		} else {
			
			usersRole.setUserrole_name("");
			usersRole.setUserrole_status("");
		}
		
		return model;
	} 
	
	
}
