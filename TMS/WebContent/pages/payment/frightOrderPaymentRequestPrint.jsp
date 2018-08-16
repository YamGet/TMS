<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="panel panel-default" style="width: 45%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Payment Request Information
			</h4>
		</div>					
		<table class="table">
			<tr><td> Owner Name - </td><td> ${getFrightOrderInfo[0].truckInformation.truck_owner} </td></tr>
			<tr><td> Truck Plate No - </td><td> ${getFrightOrderInfo[0].truckInformation.truck_plate_no} </td></tr>
			<tr><td> Trail Plate No - </td><td> ${getFrightOrderInfo[0].trailInformation.trail_plate_no} </td></tr>
			<tr><td> Loading Date - </td><td> ${getFrightOrderInfo[0].date_from} </td></tr>
			<tr><td> Freight Order Number - </td><td>  ${getFrightOrderInfo[0].fon} </td></tr>
			
			<tr><td> Dispatcher Document Ref. Number - </td><td>  ${getFrightOrderInfo[0].frightOrderTripDetail.dispatch_doc_ref_no} </td></tr>
			<tr><td> Delivery Document Ref. Number - </td><td>  ${getFrightOrderInfo[0].frightOrderTripDetail.delivery_doc_ref_no} </td></tr>
			<tr><td> Origin Place - </td><td>  ${getFrightOrderInfo[0].frightOrderTripDetail.origin_place} </td></tr>
			<tr><td> Destination Place - </td><td>  ${getFrightOrderInfo[0].frightOrderTripDetail.destination_place} </td></tr>
			<tr><td> Client Organization - </td><td>  ${getFrightOrderInfo[0].frightOrderTripDetail.client_organization} </td></tr>
			<tr><td> Delivered Quantity - </td><td>  ${getFrightOrderInfo[0].frightOrderTripDetail.delivered_quantity} </td></tr>			
			<tr><td> Price - </td><td>  ${getFrightOrderInfo[0].frightOrderTripDetail.price} </td></tr>
			
			<tr><td> Total Price - </td><td>  
				<c:set var="total_price" value="${getFrightOrderInfo[0].frightOrderTripDetail.delivered_quantity * getFrightOrderInfo[0].frightOrderTripDetail.price}"/>
				<c:out value="${total_price}"></c:out>
			</td></tr>
			
			<c:choose>
				<c:when test="${coupon_status == \"yes\"}">
					<tr><td> Coupon - </td><td> ( ${getFrightOrderInfo[0].total_coupon_amount} ) </td></tr>
				</c:when>				 
			</c:choose>
						
			<tr><td> Commission(${getFrightOrderInfo[0].commission * 100}%) - </td><td>
				<c:set var="comm" value="${total_price * getFrightOrderInfo[0].commission}"></c:set>				
				( <fmt:formatNumber value="${comm}" maxFractionDigits="2"/> ) &nbsp; <div onclick="getCommissionEditForm('${loader}', '${getFrightOrderInfo[0].fo_id}')" style="color: #5885c9; cursor: pointer;"> Edit Commission </div> 
			</td></tr>
			<tr><td> Net Difference - </td><td>
			
				<c:choose>
				<c:when test="${coupon_status == \"yes\"}">
					<c:set var="netDiff" value="${total_price - comm - getFrightOrderInfo[0].total_coupon_amount}"/>
				</c:when>
				<c:otherwise>
					<c:set var="netDiff" value="${total_price - (total_price * getFrightOrderInfo[0].commission)}"/> 
				</c:otherwise> 
				</c:choose>
				
				<fmt:formatNumber value="${netDiff}" maxFractionDigits="2"/>
			</td></tr>
			<tr><td> Payment Appointment Date - </td><td>  _____________  </td></tr>						
		</table>
		
		<div style="padding: 10px;">
			<button type="submit" class="btn btn-default" onclick="printPaymentRequestForm('${loader}', '${getFrightOrderInfo[0].fo_id}', '${coupon_status}')">Print</button>
		</div>
		
		<div style="padding: 10px;">
			<c:url value="/resources/files/not.pdf" var="fileURL"/>			
		</div>
		
	</div>

</body>
</html>