package org.fidel.tms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fidel.tms.dao.ExpenseDao;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.model.CouponRegistration;
import org.fidel.tms.model.Expense;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.Payment;
import org.fidel.tms.report.AttachmentPDF_AdditionalPayment;
import org.fidel.tms.report.AttachmentPDF_FrightOrderRegisteredExpenseList;
import org.fidel.tms.report.AttachmentPDF_PaymentRequestForm;
import org.fidel.tms.report.AttachmentPDF_RemainingBalanceReturn;
import org.fidel.tms.report.AttachmentPDF_TransferRemainingBalance;
import org.fidel.tms.service.AdvancePaymentService;
import org.fidel.tms.service.CouponRegistrationService;
import org.fidel.tms.service.ExpenseService;
import org.fidel.tms.service.FilesService;
import org.fidel.tms.service.FrightOrderService;
import org.fidel.tms.service.FrightOrderTripDetailService;
import org.fidel.tms.utils.CheckFileExistance;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.OpenFile;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("advancePayment")
public class AdvancePaymentController {
	
	@Autowired
	AdvancePaymentService advancePaymentService;	
	@Autowired
	FrightOrderService frightOrderService;
	@Autowired
	FrightOrderTripDetailService frightOrderTripDetailService;
	@Autowired
	CouponRegistrationService couponRegistrationService;
	@Autowired
	FilesService filesService;
	@Autowired
	ExpenseService expenseService;
	
	@RequestMapping(value="/insertFrightOrderAdvancePayment", method=RequestMethod.GET)
	public ModelAndView insertFrightOrderAdvancePayment(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = advancePaymentService.insertAdvancePayment(advancePayment);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderDetail/frightOrderAdvancePayment");
		
		if(rslt){
			
			modelandview.addObject("advancePayment", advancePayment);
			
		} else {
			
			modelandview.addObject("advancePayment", advancePayment);
			modelandview.addObject("errorMessage", "The advance payment is not saved.");
		}
		
		return modelandview;
	}
	
	@RequestMapping(value="/updateFrightOrderAdvancePayment", method=RequestMethod.GET)
	public ModelAndView updateFrightOrderAdvancePayment(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = false;
		
		List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePayment(advancePayment.getFo_id(), advancePayment.getAp_id());
		
		if(getAdvancePayment.size() == 0){
			
			rslt = advancePaymentService.insertAdvancePayment(advancePayment);
			
		} else {
		
			rslt = advancePaymentService.updateAdvancePayment(advancePayment);
		}
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderDetail/frightOrderAdvancePayment");
		
		if(rslt){
			
			List<AdvancePayment> advancePaymentList = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
			
			modelandview.addObject("advancePayment", advancePayment);
			modelandview.addObject("advancePaymentList", advancePaymentList);
			
		} else {
			
			modelandview.addObject("advancePayment", advancePayment);
			modelandview.addObject("errorMessage", "The advance payment is not saved.");
		}
		
		return modelandview;
	}

	@RequestMapping(value="/saveAdditionalAdvancePayment", method=RequestMethod.GET)
	public ModelAndView saveAdditionalAdvancePayment(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getFrightOrder(advancePayment.getFo_id());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			
			Date foDate = sdf.parse(frightOrderList.get(0).getDate_from());
			Date advPayAddDate = sdf.parse(advancePayment.getSend_date());
			
			if(advPayAddDate.before(foDate)){
				
				ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderAdditionAdvancePaymentForm");
				
				modelandview.addObject("errorMessage", "The date can't be prior to the date of fright order opened/registered.");
				
				return modelandview;
			}
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		boolean rslt = advancePaymentService.insertAdvancePaymentAdditionalAmount(advancePayment);
		
		if(rslt){
		
			List<FrightOrder> frOrder = frightOrderService.getFrightOrder(advancePayment.getFo_id());
			List<FrightOrderTripDetail> foTripDetail = frightOrderTripDetailService.getFrightOrderTripDetail(advancePayment.getFo_id());
			List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
			List<CouponRegistration> getAssignedCoupon = couponRegistrationService.getRelatedCouponList(advancePayment.getFo_id());
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("frightOrder", frOrder);
			model.put("foTripDetail", foTripDetail);
			model.put("getAdvancePayment", getAdvancePayment);
			model.put("getAssignedCoupon", getAssignedCoupon);
			
			ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderDetailInformation");
			
			modelandview.addObject("model", model);
			
			return modelandview;
		
		} else {
		
			ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderAdditionAdvancePaymentForm");
			
			return modelandview;
		}
		
	}
	
	@RequestMapping(value="/getFOAdvacnePaymentInfoUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFrightOrderAdvacnePaymentInfoUpdateForm(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePaymentById(advancePayment);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderForm/advancePaymentUpdateForm");
		
		modelandview.addObject("getAdvancePayment", getAdvancePayment);
		
		return modelandview;
	}
	
	@RequestMapping(value="/getAdvancePaymentByFoId_ForExp", method=RequestMethod.GET)
	public ModelAndView getAdvancePaymentByFoId_ForExp(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<AdvancePayment> advancePaymentList = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
		
		for(int i = 0; i < advancePaymentList.size(); i++){
			if(!advancePaymentList.get(i).getReturn_amount().isNaN()){
				advancePayment.setReturn_amount(advancePaymentList.get(i).getReturn_amount());
			}
			if(!advancePaymentList.get(i).getAdditional_amount().isNaN()){
				advancePayment.setAdditional_amount(advancePaymentList.get(i).getAdditional_amount());
			}
		}
		
		if(advancePayment.getReturn_amount().isNaN()){
			advancePayment.setReturn_amount(0.0);
		}
		if(advancePayment.getAdditional_amount().isNaN()){
			advancePayment.setAdditional_amount(0.0);
		}
		
		ModelAndView model = new ModelAndView("payment/return_and_addition_amount");
		
		return model;
	}
	
	@RequestMapping(value="/getRemainingBalanceReturnForm", method=RequestMethod.GET)
	public ModelAndView getRemainingBalanceReturnForm(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<AdvancePayment> advancePaymentList = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
		
		advancePayment.setAp_id(advancePaymentList.get(0).getAp_id());
		
		for(int i = 0; i < advancePaymentList.size(); i++){
			
			if(advancePayment.getAp_id() < advancePaymentList.get(i).getAp_id()){
				
				advancePayment.setAp_id(advancePaymentList.get(i).getAp_id());
			}
		}
		
		ModelAndView model = new ModelAndView("expense/RemainingBalanceReturnForm");
		
		return model;
	}
	
	@RequestMapping(value="/saveRemainingBalanceReturn", method=RequestMethod.GET)
	public ModelAndView saveRemainingBalanceReturn(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = advancePaymentService.saveRemainingBalanceReturn(advancePayment);
		
		if(rslt){
			
			///*** receipt form
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			List<FrightOrder> frightOrderInfo = frightOrderService.getFrightOrder(advancePayment.getFo_id());
			
			CheckFileExistance.checkAndCreateFile(SysConstant.CERT_PDF_PATH);
			
			String fileName = SysConstant.CERT_PDF_PATH + "\\Advance_Payment_Remaining_Balance_Return" + "_" + timeStamp + ".pdf";
			
//			AttachmentPDF_RemainingBalanceReturn.generatePDFDocument(fileName, frightOrderInfo, advancePayment.getReturn_amount());
//			
//			OpenFile.openExistingPDFFile(fileName);
			
			byte[] byteFile = AttachmentPDF_RemainingBalanceReturn.generatePDFDocument(fileName, frightOrderInfo, advancePayment.getReturn_amount());
			
			org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_12);
			
			ModelAndView model = new ModelAndView("expense/message/return_success_message");
			
			model.addObject("fileId", f.getF_id());
			model.addObject("file", f.getFile());
			model.addObject("fileName", f.getFilename());
			
			return model;
			
		} else {
			
			ModelAndView model = new ModelAndView("expense/message/return_failur_message");
			
			return model;
		}
	}
	
	@RequestMapping(value="/getRemainingBalanceTransferForm", method=RequestMethod.GET)
	public ModelAndView getRemainingBalanceTransferForm(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		///>>>============ used to manage coupon transfer ============<<<///
		
		///registered fuel expense
		Expense expense = new Expense();
		
		expense.setFo_id(advancePayment.getFo_id());		
		
		List<Expense> expenseList = expenseService.getExpenseListByFoid(expense);
		
		double fuelExp = 0.0;
		
		for(int i = 0; i < expenseList.size(); i++){
			
			if(expenseList.get(i).getExpenseType().getAccount_number().equalsIgnoreCase("7003")){
				
				fuelExp = expenseList.get(i).getExpense_amount();
			}
		}
		
		///given coupon amount
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
		double coupon_trnasfer_value = 0.0, coupon_amount_sum = total_coupon_amount + previously_transfered_coupon_value;
		
		if(coupon_amount_sum > fuelExp){
			
			coupon_trnasfer_value = coupon_amount_sum - fuelExp;
			
		}
		
		///>>>============ used to manage coupon transfer ============<<<///
		
		List<FrightOrder> frightOrderList = frightOrderService.getActiveFrightOrderList(advancePayment);
				
		List<AdvancePayment> advancePaymentList = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
		
		advancePayment.setAp_id(advancePaymentList.get(0).getAp_id());
		
		for(int i = 0; i < advancePaymentList.size(); i++){
			
			if(advancePayment.getAp_id() < advancePaymentList.get(i).getAp_id()){
				
				advancePayment.setAp_id(advancePaymentList.get(i).getAp_id());
			}
		}
		
		///>>> deducting the coupon value from the cash value <<<///
		advancePayment.setReturn_amount(advancePayment.getReturn_amount() - coupon_trnasfer_value);
		///>>> deducting the coupon value from the cash value <<<///
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("frightOrderList", frightOrderList);
		
		ModelAndView model = new ModelAndView("expense/RemainingBalanceTransferForm");
		
		model.addObject("map", map);
		model.addObject("coupon_transfer_value", coupon_trnasfer_value);
		
		return model;
	}
	
	@RequestMapping(value="/saveRemainingBalanceTransfer", method=RequestMethod.GET)
	public ModelAndView saveRemainingBalanceTransfer(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = advancePaymentService.saveRemainingBalanceTransfer(advancePayment);
		
		if(rslt){
			
			///*** receipt form
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			List<FrightOrder> frightOrderInfo = frightOrderService.getFrightOrder(advancePayment.getFo_id());
			List<AdvancePayment> parentFoid = advancePaymentService.getAdvancePaymentById(advancePayment);
			List<FrightOrder> parentFrightOrderInfo = frightOrderService.getFrightOrder(parentFoid.get(0).getFo_id());
			
			CheckFileExistance.checkAndCreateFile(SysConstant.CERT_PDF_PATH);
			
			String fileName = "Advance_Payment_Transfer_Remaining_Balance" + "_" + timeStamp + ".pdf";
			
			byte[] byteFile = AttachmentPDF_TransferRemainingBalance.generatePDFDocument(fileName, frightOrderInfo, parentFrightOrderInfo, advancePayment.getReturn_amount(), advancePayment.getCoupon_transfer_amount());
			
			org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_13);
			
			ModelAndView model = new ModelAndView("expense/message/transfer_success_message");
			
			model.addObject("fileId", f.getF_id());
			model.addObject("file", f.getFile());
			model.addObject("fileName", f.getFilename());
			
			return model;
			
		} else {
			
			ModelAndView model = new ModelAndView("expense/message/transfer_success_message");
			
			return model;
		}
		
		
	}
	
	@RequestMapping(value="/getAdditionalExpensePaymentForm", method=RequestMethod.GET)
	public ModelAndView getAdditionalExpensePaymentForm(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<AdvancePayment> advancePaymentList = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
		
		advancePayment.setAp_id(advancePaymentList.get(0).getAp_id());
		
		for(int i = 0; i < advancePaymentList.size(); i++){
			
			if(advancePayment.getAp_id() < advancePaymentList.get(i).getAp_id()){
				
				advancePayment.setAp_id(advancePaymentList.get(i).getAp_id());
			}
		}
		
		ModelAndView model = new ModelAndView("expense/additionalPaymentForm");
		
		return model;
	} 
	
	@RequestMapping(value="/saveAdditionalExpenseAmount", method=RequestMethod.GET)
	public ModelAndView saveAdditionalExpenseAmount(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = advancePaymentService.saveAdditionalExpenseAmount(advancePayment);
		
		if(rslt){
			
			///*** receipt form
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			List<FrightOrder> frightOrderInfo = frightOrderService.getFrightOrder(advancePayment.getFo_id());
			
			CheckFileExistance.checkAndCreateFile(SysConstant.CERT_PDF_PATH);
			
			String fileName = "Advance_Payment_Remaining_Balance_Return" + "_" + timeStamp + ".pdf";
			
			byte[] byteFile = AttachmentPDF_AdditionalPayment.generatePDFDocument(fileName, frightOrderInfo, advancePayment.getAdditional_amount());
			
			org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_13);
			
			ModelAndView model = new ModelAndView("expense/message/additional_success_message");
			
			model.addObject("fileId", f.getF_id());
			model.addObject("file", f.getFile());
			model.addObject("fileName", f.getFilename());
			
			return model;
			
		} else {
			
			ModelAndView model = new ModelAndView("expense/message/additional_failur_message");
			
			return model;
		}
	}
	
	/*
	 * common method to upload file and return download link
	 */
	public org.fidel.tms.model.Files fileUploadAndDownloadLinkReturn(String fileName, byte[] byteFile, String report_note){
		
		org.fidel.tms.model.Files fl = new org.fidel.tms.model.Files();
		fl.setFile(byteFile);
		fl.setFilename(fileName);
		fl.setNotes(report_note);
		fl.setType(SysConstant.REPORT_TYPE_1);
		
		boolean rslt = filesService.save(fl);
		
		if(rslt){
				
			org.fidel.tms.model.Files f = filesService.findByName(fileName);
			
			return f;
		}
		return null;
	}
	
	@RequestMapping(value="/editFrightOrderAdvancePaymentInfo", method=RequestMethod.GET)
	public ModelAndView editFrightOrderAdvancePaymentInfo(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
		
		if(getAdvancePayment.size() > 0){
			
			advancePayment.setAp_id(getAdvancePayment.get(0).getAp_id());
			advancePayment.setFo_id(getAdvancePayment.get(0).getFo_id());			
		}
		
		ModelAndView modelandview = new ModelAndView("admin/securePage/advancePaymentUpdateForm");
		
		modelandview.addObject("getAdvancePayment", getAdvancePayment);
		
		return modelandview;
	}
	
	@RequestMapping(value="/updateFrightOrderAdvancePaymentSecurePage", method=RequestMethod.GET)
	public ModelAndView updateFrightOrderAdvancePaymentSecurePage(@ModelAttribute("advancePayment") AdvancePayment advancePayment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		boolean rslt = false;
		
		List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePayment(advancePayment.getFo_id(), advancePayment.getAp_id());
		
		if(getAdvancePayment.size() == 0){
			
			rslt = advancePaymentService.insertAdvancePayment(advancePayment);
			
		} else {
		
			rslt = advancePaymentService.updateAdvancePayment(advancePayment);
		}
		
		ModelAndView modelandview = new ModelAndView("admin/securePage/advancePaymentUpdateForm");
		
		if(rslt){
			
			List<AdvancePayment> advancePaymentList = advancePaymentService.getAdvancePaymentByFOId(advancePayment.getFo_id());
			
			if(advancePaymentList.size() > 0){
				
				advancePayment.setAp_id(advancePaymentList.get(0).getAp_id());
			}
			
			modelandview.addObject("getAdvancePayment", advancePaymentList);
			
			modelandview.addObject("successMessage", "The advance payment is successfully updated.");
			
		} else {
			
			modelandview.addObject("getAdvancePayment", advancePayment);
			
			modelandview.addObject("errorMessage", "The advance payment is not updated.");
		}
		
		return modelandview;
	}
}
