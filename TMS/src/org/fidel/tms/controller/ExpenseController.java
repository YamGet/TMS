package org.fidel.tms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fidel.tms.excel.Excel_RevenueExpensePerFon;
import org.fidel.tms.excel.Excel_RevenueExpensePerTruck;
import org.fidel.tms.excel.Excel_TruckDailyActivities;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.model.Expense;
import org.fidel.tms.model.ExpenseType;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.report.ReportPDF_RevenueExpensePerTruck;
import org.fidel.tms.report.AttachmentPDF_FrightOrderRegisteredExpenseList;
import org.fidel.tms.report.ReportPDF_RevenueExpensePerFon;
import org.fidel.tms.service.AdvancePaymentService;
import org.fidel.tms.service.ExpenseService;
import org.fidel.tms.service.ExpenseTypeService;
import org.fidel.tms.service.FilesService;
import org.fidel.tms.service.FrightOrderService;
import org.fidel.tms.utils.CheckFileExistance;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.OpenFile;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.SysConstant;
import org.fidel.tms.utils.TodayDate_YYYYMMDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("expense")
public class ExpenseController {
	
	@Autowired
	ExpenseService expenseService;
	@Autowired
	FrightOrderService frightOrderService;
	@Autowired
	ExpenseTypeService expenseTypeService;
	@Autowired
	FilesService filesService;
	@Autowired
	AdvancePaymentService advancePaymentService;

	@RequestMapping(value="/getExpenseTemplate", method=RequestMethod.POST)
	public ModelAndView getExpenseTemplate(@ModelAttribute("expense") Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("expense/expenseTemplate");
		
		return model;
	} 
	
//	@RequestMapping(value="searchResultInformation", method=RequestMethod.GET)
//	public ModelAndView getSearchResultInformation(@ModelAttribute("expense") Expense expense, BindingResult result){
//		
//		if(SessionManager.sessionExpire()){
//			
//			return SessionManager.redirectToLogin();
//		}
//		
//		List<FrightOrder> frightOrderList = frightOrderService.getCompleteFrightOrderList();
//		List<ExpenseType> expenseTypeList = expenseTypeService.getUnrelatedExpenseType(expense.getFo_id());
//		
//		Map<String, List> map = new HashMap<String, List>();
//		map.put("frightOrderList", frightOrderList);
//		map.put("expenseTypeList", expenseTypeList);
//		
//		ModelAndView model = new ModelAndView("expense/expenseSearchResult");
//		
//		model.addObject("map", map);
//		
//		return model;
//	}
	
	@RequestMapping(value="/saveFrightOrderExpenseAmount", method=RequestMethod.GET)
	public ModelAndView saveFrightOrderExpenseAmount(@ModelAttribute("expense") Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		///>>>============ used to manage coverage of fuel, weather it is via coupon only or coupon and cash ============<<<///
		if(expense.getEt_id() == 1){
			
			///fuel expense value
			double fuelExp = expense.getExpense_amount();
			
			///given coupon amount
			AdvancePayment advancePayment = new AdvancePayment();
			advancePayment.setFo_id(expense.getFo_id());
			
			List<FrightOrder> frightOrderInfo = frightOrderService.getFrightOrder(advancePayment.getFo_id());
			
			double total_coupon_amount = 0;
			
			for(int i = 0; i < frightOrderInfo.size(); i++){
				
				total_coupon_amount = frightOrderInfo.get(i).getTotal_coupon_amount();
			}
			
			///previously transfered coupon amount
			double previously_transfered_coupon_value = 0.0;
			
			List<AdvancePayment> previouslyTransferCouponValue = advancePaymentService.getAdvancePaymentWithCouponTransferByFOId(advancePayment.getFo_id());
			
			for(int i = 0; i < previouslyTransferCouponValue.size(); i++){
				
				previously_transfered_coupon_value += previouslyTransferCouponValue.get(i).getCoupon_receive_amount();
			}
			
			///comparison of the fuel expense with given coupon
			double additional_cash_payment = 0.0, coupon_amount_sum = total_coupon_amount + previously_transfered_coupon_value;
			
			if(coupon_amount_sum < fuelExp){
				
				additional_cash_payment = fuelExp - coupon_amount_sum;
				advancePayment.setCoupon_status("End+Cash");
				
				advancePaymentService.updateAdvancePaymentCouponStatus(advancePayment);
			}
			if(coupon_amount_sum == fuelExp){
				
				additional_cash_payment = fuelExp - coupon_amount_sum;
				advancePayment.setCoupon_status("End");
				
				advancePaymentService.updateAdvancePaymentCouponStatus(advancePayment);
			}
		}
		///>>>============ used to manage coverage of fuel, weather it is via coupon only or coupon and cash ============<<<///
		
		boolean rslt = expenseService.saveFrightOrderExpenseAmount(expense);
		
		ModelAndView model = new ModelAndView("expense/expenseTemplate");
		
		if(rslt){
		
			model.addObject("message", "The fright order expense successfully registered.");
			
		} else {
			
			model.addObject("message", "The fright order expense is not saved. Please try again later.");
		}
		
		return model;
	}
	
	@RequestMapping(value="/getExpenseByFoId", method=RequestMethod.GET)
	public ModelAndView getExpenseByFoId(@ModelAttribute("expense") Expense expense, BindingResult result){
		
		expense.setExpense_amount(Double.parseDouble(expenseService.getTotalExpenseByFoid(expense)));
		
		ModelAndView model = new ModelAndView("expense/registered_exp_amount");
		
		return model;
	}
	
	@RequestMapping(value="/getExpenseListByFoId", method=RequestMethod.GET)
	public ModelAndView getExpenseListByFoId(@ModelAttribute("expense") Expense expense, BindingResult result){
				
		List<Expense> expenseList = expenseService.getExpenseListByFoid(expense);
		
		ModelAndView model = new ModelAndView("expense/expenseList");
		
		model.addObject("expenseList", expenseList);
		
		return model;
	} 
	
	@RequestMapping(value="/attachmentPDF_FrightOrderRegisteredExpensList", method=RequestMethod.GET)
	public ModelAndView attachmentPDF_FrightOrderRegisteredExpensList(@ModelAttribute("expense") Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<Expense> expenseList = expenseService.getExpenseListByFoid(expense);
		
		CheckFileExistance.checkAndCreateFile(SysConstant.CERT_PDF_PATH);
		
		String fileName = "Fright_Order_Expens_List" + "_" + timeStamp + ".pdf";
		
		byte[] byteFile = AttachmentPDF_FrightOrderRegisteredExpenseList.generatePDFDocument(fileName, expenseList, expense.getIs_coupon_on_credit());
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_10);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("expenseList", expenseList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());		
				
		return model;
	}
	
	@RequestMapping(value="/rpt_generateRevenueExpensePerTruckReport", method=RequestMethod.GET)
	public ModelAndView rpt_generateRevenueExpensePerTruckReport(@ModelAttribute("expense")Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Expense> expRevPerTruckReport = expenseService.rpt_generateRevenueExpensePerTruckReport(expense);
		
		expense.setDate_from(TodayDate_YYYYMMDD.getDayMonthYearFormat(expense.getDate_from()));
		expense.setDate_to(TodayDate_YYYYMMDD.getDayMonthYearFormat(expense.getDate_to()));
		
		ModelAndView model = new ModelAndView("report/expense/revenue_expense_per_truck_rpt");
		
		model.addObject("expRevPerTruckReport", expRevPerTruckReport);
		
		return model;
	}
	
	@RequestMapping(value="/rptPDF_generateRevenueExpensePerTruckReport", method=RequestMethod.GET)
	public ModelAndView rptPDF_generateRevenueExpensePerTruckReport(@ModelAttribute("expense")Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<Expense> expRevPerTruckReport = expenseService.rpt_generateRevenueExpensePerTruckReport(expense);
		
		String fileName = "Revenue_and_Expense_Per_Truck_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_RevenueExpensePerTruck.generatePDFDocument(fileName, expRevPerTruckReport, expense);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_8);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("expRevPerTruckReport", expRevPerTruckReport);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="/excel_generateRevenueExpensePerTruck", method=RequestMethod.GET)
	public ModelAndView excel_generateRevenueExpensePerTruck(@ModelAttribute("expense")Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<Expense> expRevPerTruckReport = expenseService.rpt_generateRevenueExpensePerTruckReport(expense);
		
		String fileName = "Revenue_and_Expense_Per_Truck" + "_" + timeStamp + ".xls";
				
		byte[] byteFile = Excel_RevenueExpensePerTruck.excelRequiredInfo(fileName, expRevPerTruckReport);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn_Excel(fileName, byteFile, SysConstant.REPORT_NOTE_8);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("expRevPerTruckReport", expRevPerTruckReport);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="/rpt_generateRevenueExpensePerFonReport", method=RequestMethod.GET)
	public ModelAndView rpt_generateRevenueExpensePerFonReport(@ModelAttribute("expense")Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Expense> expRevPerFonReport = expenseService.rpt_generateRevenueExpensePerFonReport(expense);
		
		expense.setDate_from(TodayDate_YYYYMMDD.getDayMonthYearFormat(expense.getDate_from()));
		expense.setDate_to(TodayDate_YYYYMMDD.getDayMonthYearFormat(expense.getDate_to()));
		
		ModelAndView model = new ModelAndView("report/expense/revenue_expense_per_fon_rpt");
		
		model.addObject("expRevPerFonReport", expRevPerFonReport);
		
		return model;
	}
	
	@RequestMapping(value="/rptPDF_generateRevenueExpensePerFonReport", method=RequestMethod.GET)
	public ModelAndView rptPDF_generateRevenueExpensePerFonReport(@ModelAttribute("expense")Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<Expense> expRevPerFonReport = expenseService.rpt_generateRevenueExpensePerFonReport(expense);
		
		String fileName = "Revenue_and_Expense_Per_Fon" + "_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_RevenueExpensePerFon.generatePDFDocument(fileName, expRevPerFonReport, expense);

		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_9);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("expRevPerFonReport", expRevPerFonReport);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="excel_generateRevenueExpensePerFonReport", method=RequestMethod.GET)
	public ModelAndView excel_generateRevenueExpensePerFonReport(@ModelAttribute("expense")Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<Expense> expRevPerFonReport = expenseService.rpt_generateRevenueExpensePerFonReport(expense);
		
		String fileName = "Revenue_and_Expense_Per_Fon" + "_" + timeStamp + ".xls";
				
		byte[] byteFile = Excel_RevenueExpensePerFon.excelRequiredInfo(fileName, expRevPerFonReport);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn_Excel(fileName, byteFile, SysConstant.REPORT_NOTE_9);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("expRevPerFonReport", expRevPerFonReport);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
				
		return model;
	}
	
	/*
	 * common method to upload file and return download link >>>> for PDF
	 */
	public org.fidel.tms.model.Files fileUploadAndDownloadLinkReturn(String fileName, byte[] byteFile, String report_note){
		
		org.fidel.tms.model.Files fl = new org.fidel.tms.model.Files();
		fl.setFile(byteFile);
		fl.setFilename(fileName);
		fl.setNotes(SysConstant.REPORT_NOTE_1);
		fl.setType(SysConstant.REPORT_TYPE_1);
		
		boolean rslt = false;
		
		if(byteFile.length > 0){
		
			rslt = filesService.save(fl);
		}
		
		if(rslt){
				
			org.fidel.tms.model.Files f = filesService.findByName(fileName);
			
			return f;
		}
		return null;
	}
	
	/*
	 * common method to upload file and return download link >>>> for EXCEL
	 */
	public org.fidel.tms.model.Files fileUploadAndDownloadLinkReturn_Excel(String fileName, byte[] byteFile, String report_note){
		
		org.fidel.tms.model.Files fl = new org.fidel.tms.model.Files();
		fl.setFile(byteFile);
		fl.setFilename(fileName);
		fl.setNotes(SysConstant.REPORT_NOTE_1);
		fl.setType(SysConstant.REPORT_TYPE_2);
		
		boolean rslt = false;		
		
		if(byteFile.length > 0){
		
			rslt = filesService.save(fl);
		}
		
		if(rslt){
				
			org.fidel.tms.model.Files f = filesService.findByName(fileName);
			
			return f;
		}
		return null;
	}
	
	@RequestMapping(value="/getFORegExpenseListByFoId", method=RequestMethod.GET)
	public ModelAndView getFORegExpenseListByFoId(@ModelAttribute("expense") Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
				
		List<Expense> expenseList = expenseService.getExpenseListByFoid(expense);
		
		ModelAndView model = new ModelAndView("admin/securePage/freight_order_reg_expense_list");
		
		model.addObject("expenseList", expenseList);
		
		return model;
	}
	
	@RequestMapping(value="/getFORegExpenseUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFORegExpenseUpdateForm(@ModelAttribute("expense") Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("admin/securePage/freight_order_reg_expense_update_form");
		
		return model;
	}
	
	@RequestMapping(value="/updateFOExpenseAmount", method=RequestMethod.GET)
	public ModelAndView updateFOExpenseAmount(@ModelAttribute("expense") Expense expense, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = expenseService.updateFOExpenseAmount(expense);
		
		if(rslt){
			
			List<Expense> expenseList = expenseService.getExpenseListByFoid(expense);
			
			ModelAndView model = new ModelAndView("admin/securePage/freight_order_reg_expense_list");
			
			model.addObject("expenseList", expenseList);
						
			return model;
		}
		
		ModelAndView model = new ModelAndView("admin/securePage/freight_order_reg_expense_update_form");
		
		model.addObject("errorMessage", "The fright order expense is not updated. Please try again later.");
		
		return model;
	}
}
