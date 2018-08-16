<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>	
</head>
<body>	

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<c:set var="fo_id" value="${map.frightOrder[0].fo_id}"/>
	
	<div>
		<table><tr><td> 
			<div class="form-group">
				<label for="fon">Freight Order Number</label>
				<input id="fon" type="text" class="form-control" value="${map.frightOrder[0].fon}" disabled>				
			</div>
		 </td><td>&nbsp;</td><td>  
		 	<div class="form-group">
				<label for="fon">Driver</label>
				<input id="fon" type="text" class="form-control" value="${map.frightOrder[0].drivers.fname} ${map.frightOrder[0].drivers.mname}" disabled>				
			</div>
		 </td><td>&nbsp;</td><td>  
		 	<div class="form-group">
				<label for="fon">Truck - Trail Plate No.</label>
				<input id="fon" type="text" class="form-control" value="${map.frightOrder[0].truckInformation.truck_plate_no} - ${map.frightOrder[0].trailInformation.trail_plate_no}" disabled>				
			</div>
		 </td><td>&nbsp;</td><td>  
		 	<div class="form-group">
				<label for="fon">Total Coupon Amount</label>
				<input id="fon" type="text" class="form-control" value="${map.frightOrder[0].total_coupon_amount}" disabled>				
			</div>
		 </td><td>&nbsp;</td><td>
		 	<div class="form-group">
				<label for="fon">Date From</label>
				<input id="fon" type="text" class="form-control" value="${map.frightOrder[0].date_from}" disabled>				
			</div>
		 </td><td>&nbsp;</td><td>
		 	<div class="form-group">
				<label for="fon">Date To</label>
				<input id="fon" type="text" class="form-control" value="${map.frightOrder[0].date_to}" disabled>				
			</div>
		 </td></tr></table>
	</div>
	<hr/>
	<table width="100%">
		<tr>
			<td valign="top" width="30%" id="foDetailInfo">
								
				<jsp:include page="frightOrderForm/frightOrderTripDetailAddForm.jsp"/>
				
			</td>
			<td width="3%">&nbsp;</td>
			<td valign="top" width="30%">
								
				<jsp:include page="frightOrderForm/couponDisseminationForm.jsp"/>
				
			</td>
			<td width="3%">&nbsp;</td>
			<td valign="top" width="30%" id="foAdvancePayment">
				
				<jsp:include page="frightOrderForm/advancePaymentForm.jsp"/>
				
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>