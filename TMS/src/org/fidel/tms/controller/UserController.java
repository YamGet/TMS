package org.fidel.tms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fidel.tms.model.SystemURL;
import org.fidel.tms.model.Users;
import org.fidel.tms.model.UsersRole;
import org.fidel.tms.service.SystemURLService;
import org.fidel.tms.service.UserService;
import org.fidel.tms.service.UsersRoleService;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.DeleteSecurityFile;
import org.fidel.tms.utils.PasswordEncription;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.SystemTrialVersion;
import org.fidel.tms.utils.SystemValidator;
import org.fidel.tms.utils.SystemValidatorTextAndMacAddressCreator;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	UsersRoleService usersRoleService;
	@Autowired
	SystemURLService systemURLService;
	
	@RequestMapping(value="/installation", method=RequestMethod.GET)
	public ModelAndView installation(@ModelAttribute("user") Users users, BindingResult result) {
		
		List<Users> usersList = userService.getAllUsersList();
		
		if(usersList.size() == 0){
			
			Users user = new Users();
			user.setFname("Super");
			user.setMname("Super");
			user.setGname("Super");
			user.setUser_name("superUser");
			user.setUr_id(1);			
			user.setPassword("super*password");			
			
			boolean rslt = userService.saveInitialRequiredInformation(users);
			
			if(!rslt){
				
				ModelAndView modelandview = new ModelAndView("installation_failur");
				
				return modelandview;
			}
			
			SystemValidatorTextAndMacAddressCreator.generateValidationText("Tilahun_Mesafenet");
		}
		
		ModelAndView modelandview = new ModelAndView("login");
		
		return modelandview;
	}
	
	@RequestMapping(value="/validateLoginForm", method=RequestMethod.POST)
	public ModelAndView validateLoginForm(@ModelAttribute("user") Users users, BindingResult result, Errors errors) {
		
		///*** check system security ***///
		if(!isTheSystemSecure()){
			
			DeleteSecurityFile.deleteSystemFileForSecurityPurpose();
			
			ModelAndView modelandview = new ModelAndView("redirect:access_denied");
			
			return modelandview;
		}
		
		////**** trial version ****////
//		if(SystemTrialVersion.checkTrialVersionBoundery()){
//			
//			ModelAndView modelandview = new ModelAndView("redirect:access_denied");
//			
//			return modelandview;
//		}
		
		if(users.getUser_name().equals("")){
			errors.rejectValue("user_name", "username is required");
		}
		if(users.getPassword().equals("")){
			errors.rejectValue("password", "password is required");
		}
		
		///*** validate user_name and password ***///
		boolean loginValidation = false;
		if((!users.getUser_name().isEmpty() && !users.getPassword().isEmpty()) && !userService.validateUser(users.getUser_name(), users.getPassword())){
			errors.reject("");
			loginValidation = true;
		}
		
		if(result.hasErrors()){
			
			ModelAndView modelandview = new ModelAndView("login");			
			
			modelandview.addObject("username_error", (users.getUser_name().isEmpty())?"username is required":"");
			modelandview.addObject("password_error", (users.getPassword().isEmpty())?"password is required":"");
			modelandview.addObject("input_error", (loginValidation)?"Username or Password is incorrect.":"");
			
			if(!users.getUser_name().equals("")){
				users.setUser_name(users.getUser_name());
			}
			
			return modelandview;
		}
		
		if(userService.checkUserStatus(users.getUser_name(), users.getPassword())){
			
			ModelAndView modelandview = new ModelAndView("login");
			modelandview.addObject("input_error", "Your cannot use the system any more, please talk to the system administrator.");
			return modelandview;
		}
		
		List<SystemURL> urlList = systemURLService.getAuthenticatedURL(users.getUser_name());
		
		SessionManager.addingUserOnSession(users.getUser_name(), userService.getUserId(users.getUser_name()), urlList);
				
		ModelAndView modelandview = new ModelAndView("redirect:index");
		
		return modelandview;
	}
	
	///*** check system security ***///
	public boolean isTheSystemSecure(){
		
		return SystemValidator.systemValidation();
	}
	
	///*** validate user_name and password ***///
	public boolean isUserNameAndPasswordValidate(String username, String password){
		
		return userService.validateUser(username, password);		
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView getIndexPage(){
		
		ModelAndView modelandview = new ModelAndView();
		
		if(SessionManager.isSessionOn()){
			modelandview = new ModelAndView("index");
		} else {
			modelandview = new ModelAndView("redirect:login");
		}
		
		return modelandview;
	}
	
	@RequestMapping(value="/validateLoginForm_old", method=RequestMethod.GET)
	public ModelAndView valLoginForm(@ModelAttribute("user") Users users, BindingResult result) {
		
		if(result.hasErrors()){
			
			ModelAndView modelandview = new ModelAndView("login");
			return modelandview;
		}
		
		ModelAndView modelandview = new ModelAndView("index");
		
		return modelandview;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginForm(@ModelAttribute("user") Users users, BindingResult result) {
				
		ModelAndView modelandview = new ModelAndView("login");
		
		return modelandview;
	}
	
	@RequestMapping(value="/changeProfile", method=RequestMethod.GET)
	public ModelAndView changeProfile(@ModelAttribute("user") Users users, BindingResult result) {
		
		ModelAndView modelandview = new ModelAndView("change_profile");
		
		return modelandview;
	}
	
	@RequestMapping(value="/helpPage", method=RequestMethod.GET)
	public ModelAndView helpPage(@ModelAttribute("user") Users users, BindingResult result) {
				
		ModelAndView modelandview = new ModelAndView("help/help_template");
		
		return modelandview;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(){
		
		SessionManager.clearSession();
		
		return "redirect:login";
	}
	
	@RequestMapping(value="/session_expire", method=RequestMethod.GET)
	public ModelAndView session_expire(@ModelAttribute("user") Users users, BindingResult result) {
				
		ModelAndView modelandview = new ModelAndView("session_expire");
		
		return modelandview;
	}
	
	@RequestMapping(value="/access_denied", method=RequestMethod.GET)
	public ModelAndView access_denied(@ModelAttribute("user") Users users, BindingResult result) {
				
		ModelAndView modelandview = new ModelAndView("access_denied_page");
		
		return modelandview;
	}
	
	@RequestMapping(value="/getUsersInformation", method=RequestMethod.GET)
	public ModelAndView getUsersInformation(@ModelAttribute("user") Users users, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Users> getUserList = userService.getAllUsersList();
		List<UsersRole> getUsersRoleList = usersRoleService.getActiveUsersRole();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userList", getUserList);
		map.put("usersRoleList", getUsersRoleList);
		
		ModelAndView model = new ModelAndView("admin/users/usersTemplate");
		
		model.addObject("map", map);
		
		return model;
	}
	
	@RequestMapping(value="/saveUsersInformation", method=RequestMethod.GET)
	public ModelAndView saveUsersInformation(@ModelAttribute("user") Users users, BindingResult result){
				
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = userService.saveUserInformation(users);
		
		List<Users> getUserList = userService.getAllUsersList();
		List<UsersRole> getUsersRoleList = usersRoleService.getActiveUsersRole();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userList", getUserList);
		map.put("usersRoleList", getUsersRoleList);
		
		ModelAndView model = new ModelAndView("admin/users/usersTemplate");
		
		model.addObject("map", map);
		
		if(rslt){
			model.addObject("successMessage", "User successfully saved.");
		}
		
		return model;		
	}
	
	@RequestMapping(value="/getUsersInformationUpdateForm", method=RequestMethod.GET)
	public ModelAndView getUsersInformationUpdateForm(@ModelAttribute("user") Users users, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Users> getUserList = userService.getUserById(users);
		users.setFname(getUserList.get(0).getFname());
		users.setMname(getUserList.get(0).getMname());
		users.setGname(getUserList.get(0).getGname());
		users.setUser_name(getUserList.get(0).getUser_name());
		try {
			users.setPassword(PasswordEncription.decrypt(getUserList.get(0).getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		users.setUr_id(getUserList.get(0).getUr_id());
		
		List<UsersRole> getUsersRoleList = usersRoleService.getActiveUsersRole();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usersRoleList", getUsersRoleList);
		
		ModelAndView model = new ModelAndView("admin/users/usersUpdateForm");
		
		model.addObject("map", map);
		
		return model;
	}
	
	@RequestMapping(value="/updateUsersInformation", method=RequestMethod.GET)
	public ModelAndView updateUsersInformation(@ModelAttribute("user") Users users, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = userService.updateUserInformation(users);
		
		List<Users> getUserList = userService.getAllUsersList();
		List<UsersRole> getUsersRoleList = usersRoleService.getActiveUsersRole();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userList", getUserList);
		map.put("usersRoleList", getUsersRoleList);
		
		ModelAndView model = new ModelAndView("admin/users/usersTemplate");
		
		model.addObject("map", map);
		
		if(rslt){
			model.addObject("successMessage", "User successfully updated.");
		}
		
		return model;
	}
	
	@RequestMapping(value="/changeUserProfile", method=RequestMethod.GET)
	public ModelAndView changeUserProfile(@ModelAttribute("user") Users users, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = userService.updateUserNamePassword(users);
		
		if(rslt){
			
			ModelAndView modelandview = new ModelAndView("login");
			
			return modelandview;
			
		} else {
			
			ModelAndView modelandview = new ModelAndView("change_profile");
			
			modelandview.addObject("errorMessage", "The old password is not correct.");
			
			return modelandview;
		}				
	} 
}
