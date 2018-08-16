package org.fidel.tms.controller;

import org.fidel.tms.model.Report;
import org.fidel.tms.model.Users;
import org.fidel.tms.utils.CheckFileExistance;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.DatabaseBackup;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.SysConstant;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {
		
	
	@RequestMapping(value="/getAdminTemplate", method=RequestMethod.GET)
	public ModelAndView getAdminTemplate(@ModelAttribute("admin") Users users, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/adminTemplate");
		
		return model;
	} 
	
	@RequestMapping(value="/takeDatabaseBackup", method=RequestMethod.GET)
	public ModelAndView takeDatabaseBackup(@ModelAttribute("admin") Users users, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		CheckFileExistance.checkAndCreateFile(SysConstant.DATABASE_BACKUP_FOLDER);
		
		boolean rslt = DatabaseBackup.backupDataWithDatabase(SysConstant.DUMP_EXE_PATH, SysConstant.HOST, SysConstant.PORT, SysConstant.DATABASE_USERNAME, SysConstant.DATABASE_PASSWORD, SysConstant.DATABASE_NAME, SysConstant.DATABASE_BACKUP_FOLDER);
		
		if(rslt){
			
			ModelAndView model = new ModelAndView("database_backup_successful");
			
			return model;
		} else {
			
			ModelAndView model = new ModelAndView("database_backup_failed");
			
			return model;
		}
	} 

	@RequestMapping(value="/getSecuredDataFromDBTemplate", method=RequestMethod.GET)
	public ModelAndView getSecuredDataFromDBTemplate(@ModelAttribute("report") Report report, BindingResult result) {
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = new ModelAndView("admin/securePage/secure_page_template");
		
		return modelandview;
	} 
}
