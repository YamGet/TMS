package org.fidel.tms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fidel.tms.excel.Excel_CollectedPayment;
import org.fidel.tms.excel.Excel_PaymentNotCollectedAfterPaymentRequest;
import org.fidel.tms.excel.Excel_PaymentNotCollectedBeforePaymentRequest;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.Payment;
import org.fidel.tms.report.ReportPDF_PaymentNotCollectedBeforePaymentRequest;
import org.fidel.tms.report.AttachmentPDF_PaymentRequestForm;
import org.fidel.tms.report.ReportPDF_CollectedPayment;
import org.fidel.tms.report.ReportPDF_PaymentNotCollectedAfterPaymentRequest;
import org.fidel.tms.service.FilesService;
import org.fidel.tms.service.FrightOrderService;
import org.fidel.tms.service.PaymentService;
import org.fidel.tms.utils.CheckFileExistance;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.OpenFile;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("payment")
public class PaymentController extends HttpServlet {
	
	@Autowired
	PaymentService paymentService;
	@Autowired
	FrightOrderService frightOrderService;
	@Autowired
	FilesService filesService;
	
	
	private List<FrightOrder> unprocessedFrightOrderForSearch;
	
	private List<FrightOrder> inprocessedFrightOrderForSearch;
	
	@RequestMapping(value="/getPaymentTemplate", method=RequestMethod.POST)
	public ModelAndView getPaymentTemplate(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> unprocessedFrightOrder = paymentService.getUnprocessedPaymentList();
		
		unprocessedFrightOrderForSearch = unprocessedFrightOrder;
		
		ModelAndView model = new ModelAndView("payment/paymentTemplate");
		
		model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/getNotProcessedFrightOrderList", method=RequestMethod.GET)
	public ModelAndView getNotProcessedFrightOrderList(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> unprocessedFrightOrder = paymentService.getUnprocessedPaymentList();
		
		unprocessedFrightOrderForSearch = unprocessedFrightOrder;
		
		ModelAndView model = new ModelAndView("payment/notProcessedFrightOrder");
		
		model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/getFilteredNotProcessedFrightOrderList", method=RequestMethod.GET)
	public ModelAndView getFilteredNotProcessedFrightOrderList(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		List<FrightOrder> unprocessedFrightOrder = paymentService.getFilteredUnprocessedPaymentList(payment.getSearch_value(), unprocessedFrightOrderForSearch);
		
		ModelAndView model = new ModelAndView("payment/notProcessedFrightOrder");
		
		model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/showDetailNotProcessedFrightOrderInformation", method=RequestMethod.GET)
	public ModelAndView showDetailNotProcessedFrightOrderInformation(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("payment/frightOrderPaymentRequestDetail");
		
		return model;
	} 
	
	@RequestMapping(value="/getCommissionEditForm", method=RequestMethod.GET)
	public ModelAndView getCommissionEditForm(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("payment/frightOrderCommissionEditForm");
		
		return model;
	}
	
	@RequestMapping(value="/printPaymentRequestFormForNotProcessedFrightOrder", method=RequestMethod.GET)
	public ModelAndView printPaymentRequestFormForNotProcessedFrightOrder(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> getFrightOrderInfo = paymentService.getFrightOrderInfo(payment.getFo_id());
		
		ModelAndView model = new ModelAndView("payment/frightOrderPaymentRequestPrint");
		
		model.addObject("getFrightOrderInfo", getFrightOrderInfo);
		model.addObject("coupon_status", payment.getCoupon_status());
		
		return model;
	}
	
	
	
	@RequestMapping(value="/printPaymentRequestForm", method=RequestMethod.GET)
	public ModelAndView printPaymentRequestForm(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = paymentService.updateFrightOrderPaymentRequestDate(payment.getFo_id());
		
		if(rslt){
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			
			List<FrightOrder> frightOrderInfo = paymentService.getFrightOrderInfo(payment.getFo_id());
			
			CheckFileExistance.checkAndCreateFile(SysConstant.CERT_PDF_PATH);
			
			String fileName = "Payment_Requested_But_Not_Collected" + "_" + timeStamp + ".pdf";
			
			byte[] byteFile = AttachmentPDF_PaymentRequestForm.generatePDFDocument(fileName, frightOrderInfo, payment.getCoupon_status());
			
			List<FrightOrder> unprocessedFrightOrder = paymentService.getUnprocessedPaymentList();
			
			org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_11);
			
			ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

			model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
			model.addObject("fileId", f.getF_id());
			model.addObject("file", f.getFile());
			model.addObject("fileName", f.getFilename());
			
			return model;
		
		} else {
			
			List<FrightOrder> getFrightOrderInfo = paymentService.getFrightOrderInfo(payment.getFo_id());
			
			ModelAndView model = new ModelAndView("payment/frightOrderPaymentRequestPrint");
			
			model.addObject("getFrightOrderInfo", getFrightOrderInfo);
			
			return model;
		}
	}
	
	@RequestMapping(value="/getInProcessFrightOrderList", method=RequestMethod.GET)
	public ModelAndView getInProcessFrightOrderList(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
		
		inprocessedFrightOrderForSearch = inprocessedFrightOrder;
		
		ModelAndView model = new ModelAndView("payment/inProcessFrightOrder");
		
		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		
		return model;
	} 
	
	@RequestMapping(value="/getFilteredInprocessedFrightOrderList", method=RequestMethod.GET)
	public ModelAndView getFilteredInprocessedFrightOrderList(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getFilteredInprocessedPaymentList(payment.getSearch_value(), inprocessedFrightOrderForSearch);
		
		ModelAndView model = new ModelAndView("payment/inProcessFrightOrder");
		
		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/getPaymentAppointmentAddForm", method=RequestMethod.GET)
	public ModelAndView getPaymentAppointmentAddForm(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> getFrightOrderInfo = paymentService.getFrightOrderInfo(payment.getFo_id());
		
		ModelAndView model = new ModelAndView("payment/frightOrderPaymentAppointmentAddForm");
		
		model.addObject("getFrightOrderInfo", getFrightOrderInfo);
		
		return model;
	}
	
	public List<Integer> foid = new ArrayList<Integer>();
	
	@RequestMapping(value="/getPaymentForInProcessFrightOrderForm", method=RequestMethod.GET)
	public ModelAndView insertPaymentForInProcessFrightOrder(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		foid.clear();
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
		
		ModelAndView model = new ModelAndView("payment/frightOrderPaymentForm");
		
		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		
		return model;
	} 
	
	@RequestMapping(value="/addSelectedFrightOrderId", method=RequestMethod.GET)
	public ModelAndView addSelectedFrightOrderId(@ModelAttribute("payment") Payment payment, BindingResult result){
				
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		int counter = 0;
		
		for(int i = 0; i < foid.size(); i++){
			if(foid.get(i) == payment.getFo_id()){
				foid.remove(i);
				counter++;
			}
		}
		
		if(counter == 0){
			foid.add(payment.getFo_id());
		}
		
		counter = 0;
		
		ModelAndView model = new ModelAndView("payment/frightOrderPaymentForm");
		
		return model;
		
	}
	
	@RequestMapping(value="/saveFrightOrderPayment", method=RequestMethod.GET)
	public ModelAndView saveFrightOrderPayment(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean checkPaymentAmount = paymentService.checkPaymentAmount(foid, payment.getPayment_amount(), payment.getCoupon_status());
		
		if(!checkPaymentAmount){
			
			List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
			
			ModelAndView model = new ModelAndView("payment/frightOrderPaymentForm");
			
			model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
			
			return model;
		}
		
		boolean rslt = paymentService.saveFrightOrderPayment(foid, payment);
		
		if(rslt){
		
			List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
			
			ModelAndView model = new ModelAndView("payment/inProcessFrightOrder");
			
			model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
			
			return model;
			
		} else {
			
			List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
			
			ModelAndView model = new ModelAndView("payment/frightOrderPaymentForm");
			
			model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
			
			return model;
			
		}
	}
	
	@RequestMapping(value="/rpt_generatePaymnetNotCollectedAfterPaymentRequest", method=RequestMethod.GET)
	public ModelAndView rpt_generatePaymnetNotCollectedAfterPaymentRequest(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentList();
		
		ModelAndView model = new ModelAndView("report/payment/payment_not_collected_after_request");
		
		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/filterPaymentNotCollectedAfterRequest", method=RequestMethod.GET)
	public ModelAndView filterPaymentNotCollectedAfterRequest(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		ModelAndView model = new ModelAndView("report/payment/payment_not_collected_after_request_content");
		
		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/rptPDF_generatePaymnetNotCollectedAfterPaymentRequest", method=RequestMethod.GET)
	public ModelAndView rptPDF_generatePaymnetNotCollectedAfterPaymentRequest(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentListByTruckType(frightOrder.getTruckInformation().getTruck_type());
				
		String fileName = "Paymnet_Not_Collected_After_Payment_Request" + "_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_PaymentNotCollectedAfterPaymentRequest.generatePDFDocument(fileName, inprocessedFrightOrder);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_6);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="/excel_generatePaymnetNotCollectedAfterPaymentRequest", method=RequestMethod.GET)
	public ModelAndView excel_generatePaymnetNotCollectedAfterPaymentRequest(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> inprocessedFrightOrder = paymentService.getInprocessPaymentListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		String fileName = "Paymnet_Not_Collected_After_Payment_Request" + "_" + timeStamp + ".xls";
				
		byte[] byteFile = Excel_PaymentNotCollectedAfterPaymentRequest.excelRequiredInfo(fileName, inprocessedFrightOrder);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_6);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("inprocessedFrightOrder", inprocessedFrightOrder);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="/rpt_generatePaymnetNotCollectedBeforePaymentRequest", method=RequestMethod.GET)
	public ModelAndView rpt_generatePaymnetNotCollectedBeforePaymentRequest(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> unprocessedFrightOrder = paymentService.getUnprocessedPaymentList();		
		
		ModelAndView model = new ModelAndView("report/payment/payment_not_collected_before_request");
		
		model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/filterPaymentNotCollectedBeforeRequest", method=RequestMethod.GET)
	public ModelAndView filterPaymentNotCollectedBeforeRequest(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<FrightOrder> unprocessedFrightOrder = paymentService.getUnprocessedPaymentListByTruckType(frightOrder.getTruckInformation().getTruck_type());		
		
		ModelAndView model = new ModelAndView("report/payment/payment_not_collected_before_request_content");
		
		model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
		
		return model;
	}
	
	@RequestMapping(value="/rptPDF_generatePaymnetNotCollectedBeforePaymentRequest", method=RequestMethod.GET)
	public ModelAndView rptPDF_generatePaymnetNotCollectedBeforePaymentRequest(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> unprocessedFrightOrder = paymentService.getUnprocessedPaymentListByTruckType(frightOrder.getTruckInformation().getTruck_type());

		String fileName = "Paymnet_Not_Collected_Before_Payment_Request" + "_" + timeStamp + ".pdf";
				
		byte[] byteFile = ReportPDF_PaymentNotCollectedBeforePaymentRequest.generatePDFDocument(fileName, unprocessedFrightOrder);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_7);
				
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="/excel_generatePaymnetNotCollectedBeforePaymentRequest", method=RequestMethod.GET)
	public ModelAndView excel_generatePaymnetNotCollectedBeforePaymentRequest(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> unprocessedFrightOrder = paymentService.getUnprocessedPaymentListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		String fileName = "Paymnet_Not_Collected_Before_Payment_Request" + "_" + timeStamp + ".xls";
				
		byte[] byteFile = Excel_PaymentNotCollectedBeforePaymentRequest.excelRequiredInfo(fileName, unprocessedFrightOrder);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_7);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("unprocessedFrightOrder", unprocessedFrightOrder);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="/getCollectedPaymentFilterForm", method=RequestMethod.GET)
	public ModelAndView getCollectedPaymentFilterForm(@ModelAttribute("payment") Payment payment, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_filter_form");
		
		return model;
	}
	
	@RequestMapping(value="/getCollectedPaymentReport", method=RequestMethod.GET)
	public ModelAndView getCollectedPaymentReport(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> collectedPyamentList = paymentService.getCollectedPaymentReport(frightOrder);
		
		///>>> reportFilterFormForPaymentCollected.jsp - previous jsp page to be called
		ModelAndView model = new ModelAndView("report/payment/collected_payment_content");
		
		model.addObject("collectedPyamentList", collectedPyamentList);
		
		return model;
	}
	
	@RequestMapping(value="/rptPDF_getCollectedPaymentReport", method=RequestMethod.GET)
	public ModelAndView rptPDF_getCollectedPaymentReport(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result, HttpServletResponse response, HttpServletRequest request){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> collectedPyamentList = paymentService.getCollectedPaymentReport(frightOrder);
		
		String fName = "Collected_Paymnet_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_CollectedPayment.generatePDFDocument(fName, collectedPyamentList, request, response, fName);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fName, byteFile, SysConstant.REPORT_NOTE_1);
				
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("collectedPyamentList", collectedPyamentList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="/excel_getCollectedPaymentReport", method=RequestMethod.GET)
	public ModelAndView excel_getCollectedPaymentReport(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> collectedPyamentList = paymentService.getCollectedPaymentReport(frightOrder);
		
		String fileName = "Collected_Paymnet_" + timeStamp + ".xls";
				
		byte[] byteFile = Excel_CollectedPayment.excelRequiredInfo(fileName, collectedPyamentList);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn_Excel(fileName, byteFile, SysConstant.REPORT_NOTE_1);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("collectedPyamentList", collectedPyamentList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	/*
	 * common method to upload file and return download link
	 */
	public org.fidel.tms.model.Files fileUploadAndDownloadLinkReturn(String fileName, byte[] byteFile, String report_note){
		
		org.fidel.tms.model.Files fl = new org.fidel.tms.model.Files();
		fl.setFile(byteFile);
		fl.setFilename(fileName);
		fl.setNotes(SysConstant.REPORT_NOTE_1);
		fl.setType(SysConstant.REPORT_TYPE_1);
		
		boolean rslt = filesService.save(fl);
		
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

	public List<FrightOrder> getUnprocessedFrightOrderForSearch() {
		return unprocessedFrightOrderForSearch;
	}

	public void setUnprocessedFrightOrderForSearch(List<FrightOrder> unprocessedFrightOrderForSearch) {
		this.unprocessedFrightOrderForSearch = unprocessedFrightOrderForSearch;
	}

	public List<FrightOrder> getInprocessedFrightOrderForSearch() {
		return inprocessedFrightOrderForSearch;
	}

	public void setInprocessedFrightOrderForSearch(List<FrightOrder> inprocessedFrightOrderForSearch) {
		this.inprocessedFrightOrderForSearch = inprocessedFrightOrderForSearch;
	}
	
}
