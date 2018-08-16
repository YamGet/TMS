package org.fidel.tms.controller;

import java.util.List;

import org.fidel.tms.model.ExpenseType;
import org.fidel.tms.model.Users;
import org.fidel.tms.service.ExpenseTypeService;
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
@RequestMapping("expenseType")
public class ExpenseTypeController {
	
	@Autowired
	ExpenseTypeService expenseTypeService;
	
	@RequestMapping(value="/getExpenseTypeInformation", method=RequestMethod.GET)
	public ModelAndView getExpenseTypeInformation(@ModelAttribute("expenseType") ExpenseType expenseType, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<ExpenseType> expenseTypeList = expenseTypeService.getAllExpenseType();
		
		ModelAndView model = new ModelAndView("admin/expenseType/expenseTypeTemplate");
		
		model.addObject("expenseTypeList", expenseTypeList);
		
		return model;
	} 
	
	@RequestMapping(value="/saveExpenseTypeInformation", method=RequestMethod.GET)
	public ModelAndView saveExpenseTypeInformation(@ModelAttribute("expenseType") ExpenseType expenseType, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = expenseTypeService.saveExpenseType(expenseType);
		
		ModelAndView model = new ModelAndView("admin/expenseType/expenseTypeTemplate");
		
		List<ExpenseType> expenseTypeList = expenseTypeService.getAllExpenseType();
		
		if(!rslt){
			
			model.addObject("errorMessage", "Expense type already exist.");
			
		} else {
			
			expenseType.setExpense_type_name("");
			expenseType.setExpense_type_status("");
			expenseType.setAccount_number("");
		}
		
		model.addObject("expenseTypeList", expenseTypeList);
		
		return model;
	} 
	
	@RequestMapping(value="/getExpenseTypeInformationUpdateForm", method=RequestMethod.GET)
	public ModelAndView getExpenseTypeInformationUpdateForm(@ModelAttribute("expenseType") ExpenseType expenseType, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/expenseType/expenseTypeUpdateForm");
		
		return model;
	}
	
	@RequestMapping(value="/updateExpenseTypeInformation", method=RequestMethod.GET)
	public ModelAndView updateExpenseTypeInformation(@ModelAttribute("expenseType") ExpenseType expenseType, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = expenseTypeService.updateExpenseType(expenseType);
		
		List<ExpenseType> expenseTypeList = expenseTypeService.getAllExpenseType();
		
		ModelAndView model = new ModelAndView("admin/expenseType/expenseTypeTemplate");
		
		if(!rslt){
			
			model.addObject("errorMessage", "Expense type already exist.");
			
		} else {
			
			expenseType.setExpense_type_name("");
			expenseType.setAccount_number("");
			expenseType.setExpense_type_status("");
		}
		
		model.addObject("expenseTypeList", expenseTypeList);
		
		return model;
	} 

}
