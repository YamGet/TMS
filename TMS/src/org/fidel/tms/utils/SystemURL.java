package org.fidel.tms.utils;

public class SystemURL {
	
	public static final String ADMIN = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Open Admin Template', '/TMS/admin/getAdminTemplate', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Database Backup', '/TMS/admin/takeDatabaseBackup', 6, 'Active'); ";
	
	public static final String ADVANCE_PAYMENT = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Insert Fright Order Advance Payment', '/TMS/advancePayment/insertFrightOrderAdvancePayment', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Fright Order Advance Payment', '/TMS/advancePayment/updateFrightOrderAdvancePayment', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Additional Advance Payment', '/TMS/advancePayment/saveAdditionalAdvancePayment', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get FO Advacne Payment Info Update Form', '/TMS/advancePayment/getFOAdvacnePaymentInfoUpdateForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Advance Payment By FoId_ForExp', '/TMS/advancePayment/getAdvancePaymentByFoId_ForExp', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Remaining Balance Return Form', '/TMS/advancePayment/getRemainingBalanceReturnForm', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Remaining Balance Return', '/TMS/advancePayment/saveRemainingBalanceReturn', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Remaining Balance Transfer Form', '/TMS/advancePayment/getRemainingBalanceTransferForm', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Remaining Balance Transfer', '/TMS/advancePayment/saveRemainingBalanceTransfer', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Additional Expense Payment Form', '/TMS/advancePayment/getAdditionalExpensePaymentForm', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Additional Expense Amount', '/TMS/advancePayment/saveAdditionalExpenseAmount', 4, 'Active'); ";
	
	public static final String ASSOCIATION = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Association Information', '/TMS/associations/getAssociationInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Association Update Form', '/TMS/associations/getAssociationUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Association Information', '/TMS/associations/updateAssociationInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Association Information', '/TMS/associations/saveAssociationInformation', 6, 'Active'); ";
	
	public static final String COUPON_REGISTRATION = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Coupon Registration Template', '/TMS/couponReg/getCouponRegistrationTemplate', 3, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Coupon', '/TMS/couponReg/saveCoupon', 3, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Coupon Update Form', '/TMS/couponReg/getCouponUpdateForm', 3, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Coupon', '/TMS/couponReg/updateCoupon', 3, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Coupon Type List', '/TMS/couponReg/getCouponTypeList', 3, 'Active'); ";
	
	public static final String DRIVERS = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Drivers Information', '/TMS/drivers/getDriversInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Drivers Information Update Form', '/TMS/drivers/getDriversInformationUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Drivers Information', '/TMS/drivers/saveDriversInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Drivers Information', '/TMS/drivers/updateDriversInformation', 6, 'Active'); ";
	
	public static final String EXPENSE = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Expense Template', '/TMS/expense/getExpenseTemplate', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Fright Order Expense Amount', '/TMS/expense/saveFrightOrderExpenseAmount', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Expense By FoId', '/TMS/expense/getExpenseByFoId', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Expense List By FoId', '/TMS/expense/getExpenseListByFoId', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Attachment PDF-Fright Order Registered Expens List', '/TMS/expense/attachmentPDF_FrightOrderRegisteredExpensList', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report - Generate Revenue Expense Per Truck', '/TMS/expense/rpt_generateRevenueExpensePerTruckReport', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report PDF-Generate Revenue Expense Per Truck', '/TMS/expense/rptPDF_generateRevenueExpensePerTruckReport', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Excel-Generate Revenue Expense Per Truck', '/TMS/expense/excel_generateRevenueExpensePerTruck', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report - Generate Revenue Expense Per Fon', '/TMS/expense/rpt_generateRevenueExpensePerFonReport', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report PDF-Generate Revenue Expense Per Fon', '/TMS/expense/rptPDF_generateRevenueExpensePerFonReport', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Excel-Generate Revenue Expense Per Fon', '/TMS/expense/excel_generateRevenueExpensePerFonReport', 5, 'Active'); ";
	
	public static final String EXPENSE_TYPE = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Expense Type Information', '/TMS/expenseType/getExpenseTypeInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Expense Type Information', '/TMS/expenseType/saveExpenseTypeInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Expense Type Information Update Form', '/TMS/expenseType/getExpenseTypeInformationUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Expense Type Information', '/TMS/expenseType/updateExpenseTypeInformation', 6, 'Active'); ";
	
	public static final String FRIGHT_ORDER = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fright Order Template', '/TMS/frightOrder/getFrightOrderTemplate', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fright Order Add Form', '/TMS/frightOrder/getFrightOrderAddForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Check Related Information', '/TMS/frightOrder/checkRelatedInformation', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Insert New Fright Order', '/TMS/frightOrder/insertNewFrightOrder', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update New Fright Order', '/TMS/frightOrder/updateNewFrightOrder', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Fright Order Close Form', '/TMS/frightOrder/frightOrderCloseForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Close Fright Order', '/TMS/frightOrder/closeFrightOrder', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Fright Order Detail Information', '/TMS/frightOrder/frightOrderDetailInformation', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Add Coupon Form', '/TMS/frightOrder/getAddCouponForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Assign Coupon', '/TMS/frightOrder/assignCoupon', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Assign Not Given Coupon', '/TMS/frightOrder/assignNotGivenCoupon', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Fright Order Addition Advance Payment Form', '/TMS/frightOrder/frightOrderAdditionAdvancePaymentForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Incomplete Fright Order List', '/TMS/frightOrder/getIncompleteFrightOrderList', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Add Fright Order Detail Information', '/TMS/frightOrder/addFrightOrderDetailInformation', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fright Order Information Update Form', '/TMS/frightOrder/getFrightOrderInformationUpdateForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fright Order Trip Detail Info Update Form', '/TMS/frightOrder/getFrightOrderTripDetailInfoUpdateForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fright Order Advacne Payment Info Update Form', '/TMS/frightOrder/getFrightOrderAdvacnePaymentInfoUpdateForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Edit Fright Order Commission', '/TMS/frightOrder/editFrightOrderCommission', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('FO Search Result Information', '/TMS/frightOrder/foSearchResultInformation', 4, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Fright Order Price Update Form', '/TMS/frightOrder/frightOrderPriceUpdateForm', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report- Get Not Closed Fright Order', '/TMS/frightOrder/rpt_getNotClosedFrightOrder', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report PDF - Generate Not Closed Fright Order', '/TMS/frightOrder/rptPDF_generateNotClosedFrightOrder', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Excel - Generate Not Closed Fright Order', '/TMS/frightOrder/excel_generateNotClosedFrightOrder', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report - Get Trucks Daily Activities', '/TMS/frightOrder/rpt_getTrucksDailyActivities', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report PDF - Get Trucks Daily Activities', '/TMS/frightOrder/rptPDF_getTrucksDailyActivities', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Excel - Get Trucks Daily Activities', '/TMS/frightOrder/excel_getTrucksDailyActivities', 5, 'Active');";
	
	public static final String FRIGHT_ORDER_TRIP_DETAIL = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Fright Order Detail Information', '/TMS/foDetail/saveFrightOrderDetailInformation', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Fright Order Detail Information', '/TMS/foDetail/updateFrightOrderDetailInformation', 1, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Payment Appointment Date', '/TMS/foDetail/savePaymentAppointmentDate', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Fright Order Price', '/TMS/foDetail/updateFrightOrderPrice', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Fright Order Price', '/TMS/frightOrder/getFrightOrderTripDetailSecureUpdateForm', 6, 'Active');";
	
	public static final String FUEL_CARD = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fuel Card Information', '/TMS/fuelcard/getFuelCardInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Fuel Card', '/TMS/fuelcard/saveFuelCard', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fuel Card Update Form', '/TMS/fuelcard/getFuelCardUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Fuel Card', '/TMS/fuelcard/updateFuelCard', 6, 'Active'); ";
	
	public static final String SECURED_DATA = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Secured Data', '/TMS/admin/getSecuredDataFromDBTemplate', 6, 'Active');"
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Closed Frieght Order List', '/TMS/frightOrder/getClosedFrightOrderList', 6, 'Active');"
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Fright Order Closing Information', '/TMS/frightOrder/updateClosedFrightOrder', 6, 'Active');"
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get FO Registered Expense List By FoId', '/TMS/expense/getFORegExpenseListByFoId', 6, 'Active');"
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get FO Registered Expense Update Form', '/TMS/expense/getFORegExpenseUpdateForm', 6, 'Active');"
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update FO Expense Amount', '/TMS/frightOrder/updateFOExpenseAmount', 6, 'Active');"
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Fright Order Closing Secure Update Form', '/TMS/foDetail/getFrightOrderClosingSecureUpdateForm', 6, 'Active');"
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Document Download Link List', '/TMS/files/getDocumentDownloadLinkList', 6, 'Active');"; ///////>>>>> new url
	
	public static final String PAYMENT = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Payment Template', '/TMS/payment/getPaymentTemplate', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Not Processed Fright Order List', '/TMS/payment/getNotProcessedFrightOrderList', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Show Detail Not Processed Fright Order Information', '/TMS/payment/showDetailNotProcessedFrightOrderInformation', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Commission Edit Form', '/TMS/payment/getCommissionEditForm', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Print Payment Request Form For Not Processed Fright Order', '/TMS/payment/printPaymentRequestFormForNotProcessedFrightOrder', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Not Processed Fright Order List', '/TMS/payment/getNotProcessedFrightOrderList', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Show Detail Not Processed Fright Order Information', '/TMS/payment/showDetailNotProcessedFrightOrderInformation', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Print Payment Request Form', '/TMS/payment/printPaymentRequestForm', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get InProcess Fright Order List', '/TMS/payment/getInProcessFrightOrderList', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Payment Appointment Add Form', '/TMS/payment/getPaymentAppointmentAddForm', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Payment For InProcess Fright Order Form', '/TMS/payment/getPaymentForInProcessFrightOrderForm', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Add Selected Fright Order Id', '/TMS/payment/addSelectedFrightOrderId', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Fright Order Payment', '/TMS/payment/saveFrightOrderPayment', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report - Generate Paymnet Not Collected After Payment Request', '/TMS/payment/rpt_generatePaymnetNotCollectedAfterPaymentRequest', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report PDF - Generate Paymnet Not Collected After Payment Request', '/TMS/payment/rptPDF_generatePaymnetNotCollectedAfterPaymentRequest', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Excel - Generate Paymnet Not Collected After Payment Request', '/TMS/payment/excel_generatePaymnetNotCollectedAfterPaymentRequest', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report - Generate Paymnet Not Collected Before Payment Request', '/TMS/payment/rpt_generatePaymnetNotCollectedBeforePaymentRequest', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report PDF - Generate Paymnet Not Collected Before Payment Request', '/TMS/payment/rptPDF_generatePaymnetNotCollectedBeforePaymentRequest', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Excel - Generate Paymnet Not Collected Before Payment Request', '/TMS/payment/excel_generatePaymnetNotCollectedBeforePaymentRequest', 5, 'Active'); "
			
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Collected Payment Filter Form', '/TMS/payment/getCollectedPaymentFilterForm', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Collected Payment Report', '/TMS/payment/getCollectedPaymentReport', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report PDF - Collected Payment Report', '/TMS/payment/rptPDF_getCollectedPaymentReport', 2, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Excel - Collected Payment Report', '/TMS/payment/excel_getCollectedPaymentReport', 2, 'Active'); ";
	
	public static final String REPORT = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Report Template', '/TMS/report/getReportTemplate', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Filter Form Per Truck', '/TMS/report/reportFilterFormPerTruck', 5, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Report Filter Form Per Fon', '/TMS/report/reportFilterFormPerFon', 5, 'Active'); ";
	
	public static final String TRAILS = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Trails Information', '/TMS/trails/getTrailsInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Trails Information Update Form', '/TMS/trails/getTrailsInformationUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Trail Information', '/TMS/trails/saveTrailInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Trail Information', '/TMS/trails/updateTrailInformation', 6, 'Active'); ";
	
	public static final String TRUCKS = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Trucks Information', '/TMS/trucks/getTrucksInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Trucks Information Update Form', '/TMS/trucks/getTrucksInformationUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Truck Information', '/TMS/trucks/saveTruckInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Truck Information', '/TMS/trucks/updateTruckInformation', 6, 'Active'); ";
	
	public static final String SYS_USERS = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Users Information', '/TMS/user/getUsersInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Users Information', '/TMS/user/saveUsersInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Users Information Update Form', '/TMS/user/getUsersInformationUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Users Information', '/TMS/user/updateUsersInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Change User Profile', '/TMS/user/changeUserProfile', 6, 'Active'); ";
	
	public static final String USERS_ROLE = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Users Role Information', '/TMS/usersRole/getUsersRoleInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Users Role Information', '/TMS/usersRole/saveUsersRoleInformation', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Users Role Update Form', '/TMS/usersRole/getUsersRoleUpdateForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Users Role Information', '/TMS/usersRole/updateUsersRoleInformation', 6, 'Active'); ";
	
	public static final String USERS_ROLE_MODULE_RELATIONSHIP = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Relate Users Role With Module Form', '/TMS/userRoleModuleRel/relateUsersRoleWithModuleForm', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Add System Module', '/TMS/userRoleModuleRel/addSystemModule', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Save Users Role Relation With Module', '/TMS/userRoleModuleRel/saveUsersRoleRelationWithModule', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Update Users Role Relation With Module', '/TMS/userRoleModuleRel/updateUsersRoleRelationWithModule', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Unrelate Users Role With Module Form', '/TMS/userRoleModuleRel/unrelateUsersRoleWithModuleForm', 6, 'Active'); ";
	
	public static final String USERS_AUTHENTICATION = "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Users Role Authentication Template', '/TMS/usersAuth/getUsersRoleAuthenticationTemplate', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Users Role Module List', '/TMS/usersAuth/getUsersRoleModuleList', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get System URL List Per Module', '/TMS/usersAuth/getSystemURLListPerModule', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Related URL', '/TMS/usersAuth/getRelatedURL', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Get Unrelated URL', '/TMS/usersAuth/getUnrelatedURL', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Relate URL', '/TMS/usersAuth/relateURL', 6, 'Active'); "
			+ "INSERT INTO systemurl(description, url, sm_id, su_status) values('Unrelate URL', '/TMS/usersAuth/unrelateURL', 6, 'Active'); ";
	
	public static String getAllSystemUrl(){
		
		return ADMIN + ADVANCE_PAYMENT + ASSOCIATION + COUPON_REGISTRATION + DRIVERS + EXPENSE + EXPENSE_TYPE + FRIGHT_ORDER + FRIGHT_ORDER_TRIP_DETAIL + FUEL_CARD + PAYMENT + REPORT + TRAILS + TRUCKS + SYS_USERS + USERS_ROLE + USERS_ROLE_MODULE_RELATIONSHIP + USERS_AUTHENTICATION;
		
	}
}
