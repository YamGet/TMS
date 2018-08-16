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
	<c:set var="rowNum" value="1"/>
	
	<div align="center">
	
		<table width="90%">
			<tr>
				<td class="reportHeaderTitle" colspan="5">TILAHUN MESAFENT FREIGHT TRANSPORT</td>
			</tr>
			<tr>
				<td colspan="5" class="reportTitle"> Coupon Consumption Report </td>
			</tr>
			<tr>
			<td>
				<div class="panel panel-default" style="width: 100%">
					<div class="panel-heading">
						<h6 class="panel-title">
							Coupon Remaining Balance
						</h6>
					</div>
					<div class="panel-body">
						<table width="100%"><tr><td>
							<div class="form-group">
								<label for="advPay">Total Coupon Amount - ${total_coupon_amount}</label>			
							</div>					
						</td><td>
							<div class="form-group">
								<label for="advPay">Total Consumption - ${total_coupon_consumption}</label>			
							</div>					
						</td><td>
							<div class="form-group">
								<label for="advPay">Remaining Balance - ${remain_balance}</label>			
							</div>					
						</td></tr></table>
					</div>
				</div>
				<table width="100%">
					<tr>
						<td class="tableHeader">No.</td>
						<td class="tableHeader">Truck Plate Number</td>
						<td class="tableHeader" align="right">Origin Place</td>
						<td class="tableHeader" align="right">Destination Place</td>
						<td class="tableHeader" align="right">Total Coupon Amount</td>
					</tr>
					<c:forEach var="consumedCoupon" items="${consumedCoupon}">
						<tr <c:choose><c:when test="${rowNum%2 == 0}">style="line-height: 35px; background-color: #e5e5e5;"</c:when><c:otherwise>style="line-height: 35px;"</c:otherwise> </c:choose>>
							<td align="center">${rowNum} - </td>
							<td >${consumedCoupon.truckInformation.truck_plate_no}</td>
							<td >${consumedCoupon.frightOrderTripDetail.origin_place}</td>
							<td >${consumedCoupon.frightOrderTripDetail.destination_place}</td>
							<td align="center">${consumedCoupon.couponRegistration.amount}</td>							
						</tr>
						<c:set var="rowNum" value="${rowNum + 1}"/>
					</c:forEach>
					<tr>
						<td colspan="4" align="right" style="line-height: 35px;">
							Total
						</td>
						<td align="center">
							<c:out value="${total_consumption}"/> 
						</td>
					</tr>					
				</table>
		
			</td>
			</tr>
		</table>
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>