package org.fidel.tms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fidel.tms.model.SystemModule;
import org.fidel.tms.model.SystemURL;
import org.fidel.tms.model.UsersAuthentication;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.model.UsersRoleModuleRelation;
import org.fidel.tms.service.SystemModuleService;
import org.fidel.tms.service.SystemURLService;
import org.fidel.tms.service.UsersAuthenticationService;
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
@RequestMapping("usersAuth")
public class UsersAuthenticationController {
	
	@Autowired
	UsersAuthenticationService usersAuthenticationService;
	@Autowired
	UsersRoleService userRoleService;
	@Autowired
	SystemModuleService systemModuleService;
	@Autowired
	SystemURLService systemURLService;
	
	@RequestMapping(value="/getUsersRoleAuthenticationTemplate", method=RequestMethod.GET)
	public ModelAndView getUsersRoleAuthenticationTemplate(@ModelAttribute("usersAuth") UsersAuthentication usersAuth, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<UsersRole> usersRoleList = userRoleService.getActiveUsersRole();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usersRoleList", usersRoleList);
		
		ModelAndView model = new ModelAndView("/admin/roleAuthentication/roleAuthenticationTemplate");
		
		model.addObject("map", map);
		
		return model;
		
	}
	
	@RequestMapping(value="/getUsersRoleModuleList", method=RequestMethod.GET)
	public ModelAndView getUsersRoleModuleList(@ModelAttribute("usersAuth") UsersAuthentication usersAuth, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<UsersRoleModuleRelation> relatedSystemModuleList = systemModuleService.getUsersRoleRelatedSystemModuleList(usersAuth.getUsersRoleModuleRelation().getUr_id());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relatedSystemModuleList", relatedSystemModuleList);
		
		ModelAndView model = new ModelAndView("/admin/roleAuthentication/moduleList");
		
		model.addObject("map", map);
		
		return model;
		
	}
	
	@RequestMapping(value="/getSystemURLListPerModule", method=RequestMethod.GET)
	public ModelAndView getSystemURLListPerModule(@ModelAttribute("usersAuth") UsersAuthentication usersAuth, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<SystemURL> relatedUrl = systemURLService.getRelatedModule(usersAuth.getUsersRoleModuleRelation().getM_id(), usersAuth.getUsersRoleModuleRelation().getUr_id());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relatedUrl", relatedUrl);
		
		ModelAndView model = new ModelAndView("/admin/roleAuthentication/urlResult");
		
		model.addObject("relatedUrl", relatedUrl);
		
		return model;
	}
	
	@RequestMapping(value="/getRelatedURL", method=RequestMethod.GET)
	public ModelAndView getRelatedURL(@ModelAttribute("usersAuth") UsersAuthentication usersAuth, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<SystemURL> relatedUrl = systemURLService.getRelatedModule(usersAuth.getUsersRoleModuleRelation().getM_id(), usersAuth.getUsersRoleModuleRelation().getUr_id());
		
		ModelAndView model = new ModelAndView("/admin/roleAuthentication/relatedURL");
		
		model.addObject("relatedUrl", relatedUrl);
		
		return model;
	}
	
	@RequestMapping(value="/getUnrelatedURL", method=RequestMethod.GET)
	public ModelAndView getUnrelatedURL(@ModelAttribute("usersAuth") UsersAuthentication usersAuth, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<SystemURL> unrelatedUrl = systemURLService.getUnrelatedModule(usersAuth.getUsersRoleModuleRelation().getM_id(), usersAuth.getUsersRoleModuleRelation().getUr_id());
		
		ModelAndView model = new ModelAndView("/admin/roleAuthentication/unrelatedURL");
		
		model.addObject("unrelatedUrl", unrelatedUrl);
		
		return model;
	}	
	
	@RequestMapping(value="/relateURL", method=RequestMethod.GET)
	public ModelAndView relateURL(@ModelAttribute("usersAuth") UsersAuthentication usersAuth, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = usersAuthenticationService.relateURL(usersAuth);
		
		List<SystemURL> unrelatedUrl = systemURLService.getUnrelatedModule(usersAuth.getUsersRoleModuleRelation().getM_id(), usersAuth.getUsersRoleModuleRelation().getUr_id());
		
		ModelAndView model = new ModelAndView("/admin/roleAuthentication/unrelatedURL");
		
		model.addObject("unrelatedUrl", unrelatedUrl);
		
		if(rslt){
			
			model.addObject("successMessage", "Successfully Related");
		} else {
			
			model.addObject("errorMessage", "URL is not successfully Related");
		}
		
		return model;
	}
	
	@RequestMapping(value="/unrelateURL", method=RequestMethod.GET)
	public ModelAndView unrelateURL(@ModelAttribute("usersAuth") UsersAuthentication usersAuth, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = usersAuthenticationService.unrelateURL(usersAuth);
		
		List<SystemURL> relatedUrl = systemURLService.getRelatedModule(usersAuth.getUsersRoleModuleRelation().getM_id(), usersAuth.getUsersRoleModuleRelation().getUr_id());
		
		ModelAndView model = new ModelAndView("/admin/roleAuthentication/relatedURL");
		
		model.addObject("relatedUrl", relatedUrl);
		
		if(rslt){
			
			model.addObject("successMessage", "Successfully Related");
		} else {
			
			model.addObject("errorMessage", "URL is not successfully Related");
		}
		
		return model;
	}

}
