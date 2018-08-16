package org.fidel.tms.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fidel.tms.excel.Excel_NotClosedFrightOrderList;
import org.fidel.tms.excel.Excel_TransactionHistoryPerTruck;
import org.fidel.tms.excel.Excel_TruckDailyActivities;
import org.fidel.tms.model.AdvancePayment;
import org.fidel.tms.model.Associations;
import org.fidel.tms.model.CouponRegistration;
import org.fidel.tms.model.Drivers;
import org.fidel.tms.model.ExpenseType;
import org.fidel.tms.model.FrightOrder;
import org.fidel.tms.model.FrightOrderTripDetail;
import org.fidel.tms.model.Report;
import org.fidel.tms.model.TrailInformation;
import org.fidel.tms.model.TruckInformation;
import org.fidel.tms.service.AdvancePaymentService;
import org.fidel.tms.service.CouponRegistrationService;
import org.fidel.tms.service.ExpenseTypeService;
import org.fidel.tms.service.FilesService;
import org.fidel.tms.service.FrightOrderService;
import org.fidel.tms.service.FrightOrderTripDetailService;
import org.fidel.tms.service.PaymentService;
import org.fidel.tms.service.TrucksService;
import org.fidel.tms.utils.CheckFileExistance;
import org.fidel.tms.utils.CurrentURL;
import org.fidel.tms.utils.OpenFile;
import org.fidel.tms.report.ReportPDF_CouponDisseminationPerFON;
import org.fidel.tms.report.ReportPDF_LoadingUnloadingDiffernecePerTruck;
import org.fidel.tms.report.ReportPDF_NotClosedFrightOrderList;
import org.fidel.tms.report.ReportPDF_TransactionHistoryPerTruck;
import org.fidel.tms.report.ReportPDF_TruckDailyActivities;
import org.fidel.tms.utils.SessionManager;
import org.fidel.tms.utils.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("frightOrder")
public class FrightOrderController {
	
	@Autowired
	FrightOrderService frightOrderService;	
	@Autowired
	FrightOrderTripDetailService frightOrderTripDetailService;
	@Autowired
	AdvancePaymentService advancePaymentService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	ExpenseTypeService expenseTypeService;
	@Autowired
	CouponRegistrationService couponRegistrationService;
	@Autowired
	TrucksService truckService;
	@Autowired
	FilesService filesService;
	
	private List<FrightOrder> frightOrderListForSearch;
	
	private List<FrightOrder> closedFrightOrderListForSearch;
	
	@RequestMapping(value="/getFrightOrderTemplate", method=RequestMethod.GET)
	public ModelAndView getFrightOrderTemplate(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getCompleteFrightOrderList();
		
		frightOrderListForSearch = frightOrderList;
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderTemplate");
		
		modelandview.addObject("frightOrderList", frightOrderList);		
		
		return modelandview;
	}
	
	@RequestMapping(value="/searchFromFrightOrderList", method=RequestMethod.GET)
	public ModelAndView searchFromFrightOrderList(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.filterCompleteFrightOrderList(frightOrder.getSearch_value(), frightOrderListForSearch);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderList");
		
		modelandview.addObject("frightOrderList", frightOrderList);		
		
		return modelandview;
	}
	
	@RequestMapping(value="/getFrightOrderAddForm", method=RequestMethod.GET)
	public ModelAndView getFrightOrderAddForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<Associations> getAssociationList = frightOrderService.getAssociationList();
		List<TruckInformation> getAvailableTrucks = frightOrderService.getAvailableTrucks();
		List<TrailInformation> getAvailableTrails = frightOrderService.getAvailableTrails();
		List<Drivers> getAvailableDrivers = frightOrderService.getAvailableDrivers();
		
		Map<String, List> map = new HashMap<String, List>();
		map.put("getAssociationList", getAssociationList);
		map.put("getAvailableTrucks", getAvailableTrucks);
		map.put("getAvailableTrails", getAvailableTrails);
		map.put("getAvailableDrivers", getAvailableDrivers);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderAddForm");
		
		modelandview.addObject("map", map);		
		
		return modelandview;
	}
	
	@RequestMapping(value="/checkRelatedInformation", method=RequestMethod.POST)
	public ModelAndView checkRelatedInformation(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		List<TrailInformation> getAvailableTrails = frightOrderService.getAvailableTrailsBySelectedTruckCarryingType(frightOrder.getTri_id());
		
		Map<String, List> map = new HashMap<String, List>();
		map.put("getAvailableTrails", getAvailableTrails);		
		
		ModelAndView modelandview = new ModelAndView("frightOrder/trailList");
		
		modelandview.addObject("map", map);		
		
		return modelandview;
	}
	
	@RequestMapping(value="/insertNewFrightOrder", method=RequestMethod.POST)
	public ModelAndView insertNewFrightOrder(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
				
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = null;
		
		boolean rslt = frightOrderService.insertNewFrightOrder(frightOrder);
		
		if(rslt){
			
			frightOrderService.insertTrackReserve(frightOrder);
			frightOrderService.insertTrailReserve(frightOrder);
			
			///*** need to be changed for proper filter ***///
			List<FrightOrder> frightOrderList = frightOrderService.getLastFrightOrderInserted();
			List<CouponRegistration> getAssignedCoupon = couponRegistrationService.getRelatedCouponList(frightOrderList.get(0).getFo_id());
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("frightOrder", frightOrderList);
			map.put("getAssignedCoupon", getAssignedCoupon);
			
			modelandview = new ModelAndView("frightOrder/frightOrderAdditionalInformation");
			
			modelandview.addObject("frightOrder", frightOrder);
			modelandview.addObject("map", map);
			
		} else {
			
			modelandview = getFrightOrderAddForm(frightOrder, result);
			modelandview.addObject("infoMessage", "The fright order number is already exist.");
		}
		
		return modelandview;
	}	
	
	@RequestMapping(value="/updateNewFrightOrder", method=RequestMethod.POST)
	public ModelAndView updateNewFrightOrder(@ModelAttribute("frightOrder") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = null;
		
		boolean rslt = frightOrderService.updateFrightOrder(frightOrder);
		
		if(rslt){
			
			if(frightOrder.getTri_id() != frightOrder.getOld_tri_id()){
			
				frightOrderService.updateTrackReserve(frightOrder);
			}
			
			if(frightOrder.getTli_id() != frightOrder.getOld_tli_id()){
				
				frightOrderService.updateTrailReserve(frightOrder);
			}
						
			List<FrightOrder> frightOrderList = frightOrderService.getFrightOrder(frightOrder.getFo_id());
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("frightOrder", frightOrderList);
						
			modelandview = new ModelAndView("frightOrder/frightOrderDetail/frightOrderInformation");
			modelandview.addObject("frightOrderList", frightOrderList);
						
		} else {
			
			modelandview = getFrightOrderInformationUpdateForm(frightOrder, result);
			modelandview.addObject("infoMessage", "The fright order number is already exist.");
		}
		
		return modelandview;
	}	
	
	@RequestMapping(value="/frightOrderCloseForm", method=RequestMethod.GET)
	public ModelAndView frightOrderCloseForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderCloseForm");
		
		return modelandview;
	}
	
	@RequestMapping(value="/closeFrightOrder", method=RequestMethod.GET)
	public ModelAndView closeFrightOrder(@ModelAttribute("frightOrderTripDetail") FrightOrderTripDetail frightOrderTripDetail, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> foList = frightOrderService.getFrightOrder(frightOrderTripDetail.getFo_id());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			
			Date foDate = sdf.parse(foList.get(0).getDate_from());
			Date deliveryDate = sdf.parse(frightOrderTripDetail.getDelivery_date());
			
			if(deliveryDate.before(foDate)){
				
				ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderCloseForm");
				
				modelandview.addObject("errorMessage", "The delivery date can't be prior to the date of fright order opened/registered.");
				
				return modelandview;
			}
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		boolean rslt = frightOrderTripDetailService.closeFrightOrder(frightOrderTripDetail);
		
		if(rslt){
			
			frightOrderTripDetailService.openTruckTrailAvailiability(frightOrderTripDetail);
		
			List<FrightOrder> frightOrderList = frightOrderService.getCompleteFrightOrderList();
			
			ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderTemplate");
			
			modelandview.addObject("frightOrderList", frightOrderList);		
			
			return modelandview;
			
		} else {
			
			ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderCloseForm");
			
			modelandview.addObject("errorMessage", "Fright order is not closed.");
			
			return modelandview;			
		}
	}
	
	@RequestMapping(value="/updateClosedFrightOrder", method=RequestMethod.GET)
	public ModelAndView updateClosedFrightOrder(@ModelAttribute("frightOrderTripDetail") FrightOrderTripDetail frightOrderTripDetail, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> foList = frightOrderService.getFrightOrder(frightOrderTripDetail.getFo_id());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			
			Date foDate = sdf.parse(foList.get(0).getDate_from());
			Date deliveryDate = sdf.parse(frightOrderTripDetail.getDelivery_date());
			
			if(deliveryDate.before(foDate)){
				
				ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderCloseForm");
				
				modelandview.addObject("errorMessage", "The delivery date can't be prior to the date of fright order opened/registered.");
				
				return modelandview;
			}
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		boolean rslt = frightOrderTripDetailService.closeFrightOrder(frightOrderTripDetail);
		
		if(rslt){
			
			List<FrightOrder> closedFrightOrderList = frightOrderService.getClosedFrightOrderList();
			
			closedFrightOrderListForSearch = closedFrightOrderList;
			
			ModelAndView modelandview = new ModelAndView("admin/securePage/closed_freight_order_list_template");
		
			modelandview.addObject("closedFrightOrderList", closedFrightOrderList);
			
			return modelandview;
			
		} else {
			
			ModelAndView modelandview = new ModelAndView("admin/securePage/freight_order_closing_information");
			
			modelandview.addObject("errorMessage", "Fright order closing information is not updated.");
			
			return modelandview;			
		}
	}
	
	@RequestMapping(value="/frightOrderDetailInformation", method=RequestMethod.GET)
	public ModelAndView frightOrderDetailInformation(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frOrder = frightOrderService.getFrightOrder(frightOrder.getFo_id());
		List<FrightOrderTripDetail> foTripDetail = frightOrderTripDetailService.getFrightOrderTripDetail(frightOrder.getFo_id());
		List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePaymentByFOId(frightOrder.getFo_id());
		List<CouponRegistration> getAssignedCoupon = couponRegistrationService.getRelatedCouponList(frightOrder.getFo_id());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("frightOrder", frOrder);
		model.put("foTripDetail", foTripDetail);
		model.put("getAdvancePayment", getAdvancePayment);
		model.put("getAssignedCoupon", getAssignedCoupon);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderDetailInformation");
		
		modelandview.addObject("model", model);
		
		if(getAssignedCoupon.size() == 0){
			modelandview.addObject("infoMessage", "No coupon assigned.");
		}
		
		return modelandview;
	} 
	
	@RequestMapping(value="/getAddCouponForm", method=RequestMethod.GET)
	public ModelAndView getAddCouponForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<CouponRegistration> getAssignedCoupon = couponRegistrationService.getRelatedCouponList(frightOrder.getFo_id());
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("getAssignedCoupon", getAssignedCoupon);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderAddCouponForm");
		
		modelandview.addObject("map", model);
		
		return modelandview;
	}
	
	@RequestMapping(value="/assignCoupon", method=RequestMethod.GET)
	public ModelAndView assignCoupon(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
	
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = frightOrderService.assignCouponDissemination(frightOrder);
		
		List<CouponRegistration> getAssignedCoupon = couponRegistrationService.getRelatedCouponList(frightOrder.getFo_id());
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("getAssignedCoupon", getAssignedCoupon);
		
		ModelAndView modelandview = new ModelAndView();
		
		modelandview = new ModelAndView("frightOrder/frightOrderSelectedCouponTypeList");
		
		modelandview.addObject("map", model);
		
		if(!rslt){
			
			modelandview.addObject("assignMessage", "Coupon is not avialable or there is already assigned coupon.");
		}
		
		return modelandview;
	}
	
	@RequestMapping(value="/assignNotGivenCoupon", method=RequestMethod.GET)
	public ModelAndView assignNotGivenCoupon(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
	
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = frightOrderService.assignNotGivenCouponDissemination(frightOrder);
		
		List<CouponRegistration> getAssignedCoupon = couponRegistrationService.getRelatedCouponList(frightOrder.getFo_id());
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("getAssignedCoupon", getAssignedCoupon);
		
		ModelAndView modelandview = new ModelAndView();
		
		modelandview = new ModelAndView("frightOrder/frightOrderSelectedCouponTypeList");
		
		modelandview.addObject("map", model);
		
		if(!rslt){
			
			modelandview.addObject("assignMessage", "Coupon is not avialable or there is already assigned coupon.");
		}
		
		return modelandview;
	}
	
	@RequestMapping(value="/frightOrderAdditionAdvancePaymentForm", method=RequestMethod.GET)
	public ModelAndView frightOrderAdditionAdvancePaymentForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderAdditionAdvancePaymentForm");
		
		return modelandview;
	}	
	
	@RequestMapping(value="/getIncompleteFrightOrderList", method=RequestMethod.GET)
	public ModelAndView getIncompleteFrightOrderList(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getIncompleteFrightOrderList();
				
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderIncompleteList");
		
		modelandview.addObject("frightOrderList", frightOrderList);	
		
		return modelandview;
	} 
	
	@RequestMapping(value="/addFrightOrderDetailInformation", method=RequestMethod.GET)
	public ModelAndView addFrightOrderDetailInformation(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getFrightOrder(frightOrder.getFo_id());		
		List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePaymentByFOId(frightOrder.getFo_id());
		List<CouponRegistration> getAssignedCoupon = couponRegistrationService.getRelatedCouponList(frightOrder.getFo_id());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("frightOrder", frightOrderList);
		model.put("getAdvancePayment", getAdvancePayment);
		model.put("getAssignedCoupon", getAssignedCoupon);
				
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderAdditionalInformation");
		
		modelandview.addObject("map", model);
						
		return modelandview;
	}  
	
	@RequestMapping(value="/getFrightOrderInformationUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFrightOrderInformationUpdateForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> rslt = frightOrderService.getFrightOrder(frightOrder.getFo_id());
		frightOrder.setA_id(rslt.get(0).getA_id());
		frightOrder.setFon(rslt.get(0).getFon());
		frightOrder.setTri_id(rslt.get(0).getTri_id());
		frightOrder.setOld_tri_id(rslt.get(0).getTri_id());
		frightOrder.setTli_id(rslt.get(0).getTli_id());
		frightOrder.setOld_tli_id(rslt.get(0).getTli_id());
		frightOrder.setDr_id(rslt.get(0).getDr_id());
		frightOrder.setTotal_coupon_amount(rslt.get(0).getTotal_coupon_amount());
		frightOrder.setCommission(rslt.get(0).getCommission());
		frightOrder.setDate_from(rslt.get(0).getDate_from());
		frightOrder.setDate_to(rslt.get(0).getDate_to());
		
		List<Associations> getAssociationList = frightOrderService.getAssociationList();
		List<TruckInformation> getAvailableTrucks = frightOrderService.getAvailableTrucksForUpdate(frightOrder.getTri_id());
		List<TrailInformation> getAvailableTrails = frightOrderService.getAvailableTrailsForUpdate(frightOrder.getTli_id());
		List<Drivers> getAvailableDrivers = frightOrderService.getAvailableDriversForUpdate(frightOrder.getDr_id());
		
		Map<String, List> map = new HashMap<String, List>();
		map.put("getAssociationList", getAssociationList);
		map.put("getAvailableTrucks", getAvailableTrucks);
		map.put("getAvailableTrails", getAvailableTrails);
		map.put("getAvailableDrivers", getAvailableDrivers);
			
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderUpdateForm");
		
		modelandview.addObject("map", map);
		
		return modelandview;
	}
	
	@RequestMapping(value="/getFrightOrderTripDetailInfoUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFrightOrderTripDetailInfoUpdateForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrderTripDetail> rslt = frightOrderTripDetailService.getFrightOrderTripDetailById(frightOrder.getFrightOrderTripDetail().getFotd_id());
		
		FrightOrderTripDetail frightOrderTripDetail = new FrightOrderTripDetail();
		frightOrderTripDetail.setFotd_id(frightOrder.getFrightOrderTripDetail().getFotd_id());
		frightOrderTripDetail.setClient_organization(rslt.get(0).getClient_organization());
		frightOrderTripDetail.setLoading_type(rslt.get(0).getLoading_type());
		frightOrderTripDetail.setOrigin_place(rslt.get(0).getOrigin_place());
		frightOrderTripDetail.setDestination_place(rslt.get(0).getDestination_place());
		frightOrderTripDetail.setInitial_km(rslt.get(0).getInitial_km());
		frightOrderTripDetail.setLoading_quantity(rslt.get(0).getLoading_quantity());
		frightOrderTripDetail.setDistance(rslt.get(0).getDistance());
		frightOrderTripDetail.setPrice(rslt.get(0).getPrice());
		frightOrder.setFrightOrderTripDetail(frightOrderTripDetail);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderForm/frightOrderTripDetailUpdateForm");
		
		return modelandview;
	}
	
	@RequestMapping(value="/getFrightOrderTripDetailSecureUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFrightOrderTripDetailSecureUpdateForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrderTripDetail> fotd_rslt = frightOrderTripDetailService.getFrightOrderTripDetail(frightOrder.getFo_id());
		
		List<FrightOrderTripDetail> rslt = frightOrderTripDetailService.getFrightOrderTripDetailById(fotd_rslt.get(0).getFotd_id());
		
		FrightOrderTripDetail frightOrderTripDetail = new FrightOrderTripDetail();
		frightOrderTripDetail.setFotd_id(fotd_rslt.get(0).getFotd_id());
		frightOrderTripDetail.setClient_organization(rslt.get(0).getClient_organization());
		frightOrderTripDetail.setLoading_type(rslt.get(0).getLoading_type());
		frightOrderTripDetail.setOrigin_place(rslt.get(0).getOrigin_place());
		frightOrderTripDetail.setDestination_place(rslt.get(0).getDestination_place());
		frightOrderTripDetail.setLoading_quantity(rslt.get(0).getLoading_quantity());
		frightOrderTripDetail.setDistance(rslt.get(0).getDistance());
		frightOrderTripDetail.setPrice(rslt.get(0).getPrice());
		frightOrder.setFrightOrderTripDetail(frightOrderTripDetail);
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderForm/frightOrderTripDetailUpdateForm");
		
		return modelandview;
	}
	
	@RequestMapping(value="/getFrightOrderAdvacnePaymentInfoUpdateForm", method=RequestMethod.GET)
	public ModelAndView getFrightOrderAdvacnePaymentInfoUpdateForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<AdvancePayment> getAdvancePayment = advancePaymentService.getAdvancePaymentByFOId(frightOrder.getFo_id());
		
		ModelAndView modelandview = new ModelAndView("frightOrder/frightOrderForm/advancePaymentUpdateForm");
		
		modelandview.addObject("advancePayment", getAdvancePayment);
		modelandview.addObject("frightOrder", frightOrder);
		
		return modelandview;
	}
	
	@RequestMapping(value="/editFrightOrderCommission", method=RequestMethod.GET)
	public ModelAndView editFrightOrderCommission(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		boolean rslt = frightOrderService.updateFrightOrderCommission(frightOrder);
		
		if(rslt){
		
			List<FrightOrder> getFrightOrderInfo = paymentService.getFrightOrderInfo(frightOrder.getFo_id());
			
			ModelAndView model = new ModelAndView("payment/frightOrderPaymentRequestPrint");
			
			model.addObject("getFrightOrderInfo", getFrightOrderInfo);
			
			return model;
		
		} else {
			
			ModelAndView model = new ModelAndView("payment/frightOrderCommissionEditForm");
			
			return model;
		}
	}
	
	@RequestMapping(value="foSearchResultInformation", method=RequestMethod.GET)
	public ModelAndView getSearchResultInformation(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getFrightOrderInfoByFon(frightOrder.getFon());
		List<ExpenseType> expenseTypeList = expenseTypeService.getUnrelatedExpenseType(frightOrder.getFon());
		
		Map<String, List> map = new HashMap<String, List>();
		map.put("frightOrderList", frightOrderList);
		map.put("expenseTypeList", expenseTypeList);
		
		if(frightOrderList.size() == 0){
			
			ModelAndView model = new ModelAndView("expense/noSearchResult");
			
			return model;
			
		} else {
		
			ModelAndView model = new ModelAndView("expense/expenseSearchResult");
			
			model.addObject("map", map);
			
			return model;
		}
	}
	
	@RequestMapping(value="/frightOrderPriceUpdateForm", method=RequestMethod.GET)
	public ModelAndView frightOrderPriceUpdateForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getFrightOrder(frightOrder.getFo_id());
		List<FrightOrderTripDetail> foDetailList = frightOrderTripDetailService.getFrightOrderTripDetail(frightOrder.getFo_id());
		
		FrightOrderTripDetail fotd = new FrightOrderTripDetail();		
		fotd.setPrice(foDetailList.get(0).getPrice());
		fotd.setCrv_number(foDetailList.get(0).getCrv_number());
		frightOrder.setFrightOrderTripDetail(fotd);
		frightOrder.setCommission(frightOrderList.get(0).getCommission());
				
		ModelAndView model = new ModelAndView("payment/frightOrderPriceUpdateForm");
		
		return model;
	}
	
	@RequestMapping(value="rpt_getNotClosedFrightOrder", method=RequestMethod.GET)
	public ModelAndView rpt_getNotClosedFrightOrder(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getCompleteFrightOrderList();
		
		ModelAndView model = new ModelAndView("report/frightOrder/not_closed_fright_order");
		
		model.addObject("frightOrderList", frightOrderList);
		
		return model;
	} 
	
	@RequestMapping(value="rpt_filterNotClosedFrightOrder", method=RequestMethod.GET)
	public ModelAndView rpt_filterNotClosedFrightOrder(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<FrightOrder> frightOrderList = frightOrderService.getCompleteFrightOrderListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		ModelAndView model = new ModelAndView("report/frightOrder/not_closed_fright_order_content");
		
		model.addObject("frightOrderList", frightOrderList);
		
		return model;
	} 
	
	@RequestMapping(value="rptPDF_generateNotClosedFrightOrder", method=RequestMethod.GET)
	public ModelAndView rptPDF_generateNotClosedFrightOrder(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> frightOrderList = frightOrderService.getCompleteFrightOrderListByTruckType(frightOrder.getTruckInformation().getTruck_type());
				
		String fileName ="Not_Closed_Fright_Order_Report_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_NotClosedFrightOrderList.generatePDFDocument(fileName, frightOrderList);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_2);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("frightOrderList", frightOrderList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
				
		return model;
	} 
	
	@RequestMapping(value="excel_generateNotClosedFrightOrder", method=RequestMethod.GET)
	public ModelAndView excel_generateNotClosedFrightOrder(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> frightOrderList = frightOrderService.getCompleteFrightOrderListByTruckType(frightOrder.getTruckInformation().getTruck_type());
				
		String fileName = "Not_Closed_Fright_Order_Report_" + timeStamp + ".xls";
				
		byte[] byteFile = Excel_NotClosedFrightOrderList.excelRequiredInfo(fileName, frightOrderList);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn_Excel(fileName, byteFile, SysConstant.REPORT_NOTE_2);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("frightOrderList", frightOrderList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	} 
	
	@RequestMapping(value="rpt_getTrucksDailyActivities", method=RequestMethod.GET)
	public ModelAndView rpt_getTrucksDailyActivities(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> dailyActivityList = frightOrderService.getTruckDailyActivityList();
		
		ModelAndView model = new ModelAndView("report/frightOrder/daily_activities");
		
		model.addObject("dailyActivityList", dailyActivityList);
		
		return model;
	} 
	
	@RequestMapping(value="rpt_filterDailyActivities", method=RequestMethod.GET)
	public ModelAndView rpt_filterDailyActivities(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<FrightOrder> dailyActivityList = frightOrderService.getTruckDailyActivityListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		ModelAndView model = new ModelAndView("report/frightOrder/daily_activities_content");
		
		model.addObject("dailyActivityList", dailyActivityList);
		
		return model;
	}
	
	@RequestMapping(value="rptPDF_getTrucksDailyActivities", method=RequestMethod.GET)
	public ModelAndView rptPDF_getTrucksDailyActivities(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> dailyActivityList = frightOrderService.getTruckDailyActivityListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		String fileName = "Truck_Daily_Activity" + "_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_TruckDailyActivities.generatePDFDocument(fileName, dailyActivityList, frightOrder.getTruckInformation().getTruck_type());
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_3);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("dailyActivityList", dailyActivityList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="excel_getTrucksDailyActivities", method=RequestMethod.GET)
	public ModelAndView excel_getTrucksDailyActivities(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		List<FrightOrder> dailyActivityList = frightOrderService.getTruckDailyActivityListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		String fileName = "Truck_Daily_Activity" + "_" + timeStamp + ".xls";
				
		byte[] byteFile = Excel_TruckDailyActivities.excelRequiredInfo(fileName, dailyActivityList);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn_Excel(fileName, byteFile, SysConstant.REPORT_NOTE_3);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("dailyActivityList", dailyActivityList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="rpt_getTransactionListPerTruckTemplate", method=RequestMethod.GET)
	public ModelAndView rpt_getTransactionListPerTruckTemplate(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<TruckInformation> truckList = truckService.getAllTrucks();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("truckList", truckList);
		
		ModelAndView model = new ModelAndView("report/frightOrder/truckTransaction_truckList");
		
		model.addObject("map", map);
		
		return model;
	}
	
	@RequestMapping(value="rpt_filterTruckByTruckType", method=RequestMethod.GET)
	public ModelAndView rpt_filterTruckByTruckType(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<TruckInformation> truckList = truckService.getTrucksListByTruckType(frightOrder.getTruckInformation().getTruck_type());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("truckList", truckList);
		
		ModelAndView model = new ModelAndView("report/frightOrder/trucks_list");
		
		model.addObject("map", map);
		
		return model;
		
	}
	
	
	List<FrightOrder> truckTransactionList;
	
	@RequestMapping(value="rpt_getTransactionListPerTruck", method=RequestMethod.GET)
	public ModelAndView rpt_getTransactionListPerTruck(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		truckTransactionList = frightOrderService.getTransactionListPerTruck(frightOrder);
		
		ModelAndView model = new ModelAndView("report/frightOrder/truckTransaction_transactionList");
		
		model.addObject("truckTransactionList", truckTransactionList);
		
		return model;
	}
	
	@RequestMapping(value="rptPDF_getTransactionHistoryPerTruck", method=RequestMethod.GET)
	public ModelAndView rptPDF_getTransactionHistoryPerTruck(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		String fileName = "Transaction_History_Per_Truck_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_TransactionHistoryPerTruck.generatePDFDocument(fileName, truckTransactionList);
				
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_5);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("truckTransactionList", truckTransactionList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	
	@RequestMapping(value="excel_getTransactionHistoryPerTruck", method=RequestMethod.GET)
	public ModelAndView excel_getTransactionHistoryPerTruck(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		String fileName = "Transaction_History_Per_Truck_" + timeStamp + ".xls";
		
		byte[] byteFile = Excel_TransactionHistoryPerTruck.excelRequiredInfo(fileName, truckTransactionList);
		
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn_Excel(fileName, byteFile, SysConstant.REPORT_NOTE_5);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");

		model.addObject("truckTransactionList", truckTransactionList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;
	}
	

	@RequestMapping(value="/getCouponDissiminationPrintForm", method=RequestMethod.GET)
	public ModelAndView getCouponDissiminationPrintForm(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		String fileName = "Coupon_dissemination_Per_FON_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_CouponDisseminationPerFON.generatePDFDocument(fileName);
				
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_15);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("truckTransactionList", truckTransactionList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;		
	}
	
	
	/*
	 * common method to upload file and return download link >>>> for PDF
	 */
	public org.fidel.tms.model.Files fileUploadAndDownloadLinkReturn(String fileName, byte[] byteFile, String report_not){
		
		org.fidel.tms.model.Files fl = new org.fidel.tms.model.Files();
		fl.setFile(byteFile);
		fl.setFilename(fileName);
		fl.setNotes(report_not);
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
	public org.fidel.tms.model.Files fileUploadAndDownloadLinkReturn_Excel(String fileName, byte[] byteFile, String report_not){
		
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
	
	@RequestMapping(value="/getClosedFrightOrderList", method=RequestMethod.GET)
	public ModelAndView getClosedFrightOrderList(@ModelAttribute("report") Report report, BindingResult result) {
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
			
			return SessionManager.authenticationDenied();
		}
		
		List<FrightOrder> closedFrightOrderList = frightOrderService.getClosedFrightOrderList();
		
		closedFrightOrderListForSearch = closedFrightOrderList;
		
		ModelAndView modelandview = new ModelAndView("admin/securePage/closed_freight_order_list_template");
	
		modelandview.addObject("closedFrightOrderList", closedFrightOrderList);
		
		return modelandview;
	}
	
	@RequestMapping(value="/searchFromClosedFrightOrderList", method=RequestMethod.GET)
	public ModelAndView searchFromClosedFrightOrderList(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		List<FrightOrder> closedFrightOrderList = frightOrderService.filterCompleteFrightOrderList(frightOrder.getSearch_value(), closedFrightOrderListForSearch);
		
		ModelAndView modelandview = new ModelAndView("admin/securePage/closed_freight_order_list");
		
		modelandview.addObject("closedFrightOrderList", closedFrightOrderList);
		
		return modelandview;		
	}
	
	@RequestMapping(value="/rpt_getLoadingUnloadingDifferenceTemplate", method=RequestMethod.GET)
	public ModelAndView rpt_getLoadingUnloadingDifferenceTemplate(@ModelAttribute("report") Report report, BindingResult result) {
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<TruckInformation> truckList = truckService.getAllTrucks();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("truckList", truckList);
		
		ModelAndView modelandview = new ModelAndView("report/frightOrder/loadingUnloadingDifference");
	
		modelandview.addObject("map", map);
		
		return modelandview;
	}
	
	@RequestMapping(value="rpt_getLoadingUnloadingDifferencePerTruck", method=RequestMethod.GET)
	public ModelAndView rpt_getLoadingUnloadingDifferencePerTruck(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		ModelAndView model = new ModelAndView("report/frightOrder/loadingUnloadingDifferenceContent");
		
		model.addObject("truckLoadingUnloadingDifferenceList", frightOrderService.getLoadingUnloadingDifferencePerTruck(frightOrder));
		
		return model;
	}
	
	@RequestMapping(value="rptPDF_generateLoadingUnloadingDifferencePerTruck", method=RequestMethod.GET)
	public ModelAndView rptPDF_generateLoadingUnloadingDifferencePerTruck(@ModelAttribute("fright") FrightOrder frightOrder, BindingResult result){
		
		if(SessionManager.sessionExpire()){
			
			return SessionManager.redirectToLogin();
		}
		
//		if(!SessionManager.checkAuthenticatedURL(CurrentURL.getCurrentURL())){
//			
//			return SessionManager.authenticationDenied();
//		}
		
		List<FrightOrder> getLoadingUnloadingDifferencePerTruck = frightOrderService.getLoadingUnloadingDifferencePerTruck(frightOrder);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		String fileName = "Loading_Unloading_Diff_Per_Track_" + timeStamp + ".pdf";
		
		byte[] byteFile = ReportPDF_LoadingUnloadingDiffernecePerTruck.generatePDFDocument(fileName, getLoadingUnloadingDifferencePerTruck);
				
		org.fidel.tms.model.Files f = fileUploadAndDownloadLinkReturn(fileName, byteFile, SysConstant.REPORT_NOTE_16);
		
		ModelAndView model = new ModelAndView("report/payment/collected_payment_inline_pdf");
		
		model.addObject("truckTransactionList", truckTransactionList);
		model.addObject("fileId", f.getF_id());
		model.addObject("file", f.getFile());
		model.addObject("fileName", f.getFilename());
		
		return model;		
	}
	
	
	public List<FrightOrder> getFrightOrderListForSearch() {
		return frightOrderListForSearch;
	}

	public void setFrightOrderListForSearch(List<FrightOrder> frightOrderListForSearch) {
		this.frightOrderListForSearch = frightOrderListForSearch;
	}

	public List<FrightOrder> getTruckTransactionList() {
		return truckTransactionList;
	}

	public void setTruckTransactionList(List<FrightOrder> truckTransactionList) {
		this.truckTransactionList = truckTransactionList;
	}

	public List<FrightOrder> getClosedFrightOrderListForSearch() {
		return closedFrightOrderListForSearch;
	}

	public void setClosedFrightOrderListForSearch(List<FrightOrder> closedFrightOrderListForSearch) {
		this.closedFrightOrderListForSearch = closedFrightOrderListForSearch;
	}
	
	
}
