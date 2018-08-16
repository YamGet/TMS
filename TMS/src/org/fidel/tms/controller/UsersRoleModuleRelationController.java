package org.fidel.tms.controller;

import java.util.ArrayList;
import java.util.List;

import org.fidel.tms.model.SystemModule;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.fidel.tms.service.SystemModuleService;
import org.fidel.tms.service.UsersRoleModuleRelationService;
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
@RequestMapping("userRoleModuleRel")
public class UsersRoleModuleRelationController {
	
	@Autowired
	UsersRoleModuleRelationService usersRoleModuleRelationService;
	@Autowired
	SystemModuleService systemModuleService;

	public List<Integer> selectedModule = new ArrayList<Integer>();
	
	@RequestMapping(value="/relateUsersRoleWithModuleForm", method=RequestMethod.GET)
	public ModelAndView relateUsersRoleWithModuleForm(@ModelAttribute("usersRoleModuleRelation") UsersRoleModuleRelation usersRoleModuleRelation, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		selectedModule.clear();
		
		List<SystemModule> unrelatedSystemModuleList = systemModuleService.getUnrelatedSystemModuleList(usersRoleModuleRelation.getUr_id());
		
		ModelAndView model = new ModelAndView("admin/usersRole/usersRoleModuleRelateForm");
		
		model.addObject("unrelatedSystemModuleList", unrelatedSystemModuleList);
		
		return model;
	} 
	
	@RequestMapping(value="/addSystemModule", method=RequestMethod.GET)
	public ModelAndView addSystemModuleToRelate(@ModelAttribute("usersRoleModuleRelation") UsersRoleModuleRelation usersRoleModuleRelation, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		int counter = 0, index = -1;
		
		for(int i = 0; i < selectedModule.size(); i++){
			
			if(selectedModule.get(i).equals(usersRoleModuleRelation.getM_id())){
				counter++;
				index = i;
			}
		}
		
		if(counter == 0){
			
			selectedModule.add(usersRoleModuleRelation.getM_id());
			
		} else {
			
			selectedModule.remove(index);			
		}
		
		ModelAndView model = new ModelAndView("");
		
		return model;
	}
	
	@RequestMapping(value="/saveUsersRoleRelationWithModule", method=RequestMethod.GET)
	public ModelAndView saveUsersRoleRelationWithModule(@ModelAttribute("usersRoleModuleRelation") UsersRoleModuleRelation usersRoleModuleRelation, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = usersRoleModuleRelationService.saveUsersRoleModuleRelation(usersRoleModuleRelation, selectedModule);
		
		if(rslt){
			
			List<SystemModule> relatedSystemModuleList = systemModuleService.getRelatedSystemModuleList(usersRoleModuleRelation.getUr_id());
			
			ModelAndView model = new ModelAndView("admin/usersRole/usersRoleModuleUnrelateForm");
			
			model.addObject("relatedSystemModuleList", relatedSystemModuleList);
			
			model.addObject("successMessage", "Modules related successfully.");
			
			return model;
			
		} else {
			
			List<SystemModule> unrelatedSystemModuleList = systemModuleService.getUnrelatedSystemModuleList(usersRoleModuleRelation.getUr_id());
			
			ModelAndView model = new ModelAndView("admin/usersRole/usersRoleModuleRelateForm");
			
			model.addObject("unrelatedSystemModuleList", unrelatedSystemModuleList);
			
			model.addObject("errorMessage", "Modules are not related.");
			
			return model;
		}
	}
	
	@RequestMapping(value="/updateUsersRoleRelationWithModule", method=RequestMethod.GET)
	public ModelAndView updateUsersRoleRelationWithModule(@ModelAttribute("usersRoleModuleRelation") UsersRoleModuleRelation usersRoleModuleRelation, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = usersRoleModuleRelationService.updateUsersRoleModuleRelation(usersRoleModuleRelation, selectedModule);
		
		if(rslt){
			
			List<SystemModule> unrelatedSystemModuleList = systemModuleService.getUnrelatedSystemModuleList(usersRoleModuleRelation.getUr_id());
			
			ModelAndView model = new ModelAndView("admin/usersRole/usersRoleModuleRelateForm");
			
			model.addObject("unrelatedSystemModuleList", unrelatedSystemModuleList);
			
			model.addObject("successMessage", "Modules are unrelated.");
			
			return model;
			
		} else {
			
			List<SystemModule> relatedSystemModuleList = systemModuleService.getRelatedSystemModuleList(usersRoleModuleRelation.getUr_id());
			
			ModelAndView model = new ModelAndView("admin/usersRole/usersRoleModuleUnrelateForm");
			
			model.addObject("relatedSystemModuleList", relatedSystemModuleList);
			
			model.addObject("errorMessage", "Modules isnot unrelated.");
			
			return model;
		}
	}
	
	@RequestMapping(value="/unrelateUsersRoleWithModuleForm", method=RequestMethod.GET)
	public ModelAndView unrelateUsersRoleWithModuleForm(@ModelAttribute("usersRoleModuleRelation") UsersRoleModuleRelation usersRoleModuleRelation, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<SystemModule> relatedSystemModuleList = systemModuleService.getRelatedSystemModuleList(usersRoleModuleRelation.getUr_id());
		
		ModelAndView model = new ModelAndView("admin/usersRole/usersRoleModuleUnrelateForm");
		
		model.addObject("relatedSystemModuleList", relatedSystemModuleList);
		
		return model;
	} 

	
}
