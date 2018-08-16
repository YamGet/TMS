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
	
		
	<div id="inProcessPaymentContent" style="padding-top: 5px;">
		<table><tr><td>
			<div>
				<button type="button" onclick="frightOrderPaymentForm('${loader}')" class="btn btn-default"> Add Freight Order Payment </button>
			</div>
		</td><td width="20px;">&nbsp;</td><td>
			<div>
				<c:url value="/resources/images/email.png" var="email"/>				
				<button type="button" onclick="emailFrightOrderPaymentForm('${loader}')" class="btn btn-default"> <img alt="email" src="${email}" style="width: 20px;"> Email In Process Freight Order Payment </button>
			</div>
		</td></table>
		
		<br/>
		
		<div id="priceEditForm">
		
		<div class="errorMessage">${errorMessage}</div>
		<div class="successMessage">${successMessage}</div>
		
		<table id="inprocessfo" class="table table-striped table-bordered" width="100%" cellspacing="0">  
			<thead> 
				<tr>
					<th> Client Organization </th>
					<th> Freight Order No. </th> 
					<th> Truck Plate No. </th>
					<th> No of Days </th>
					<th> Trip Type </th>
					<th> Date From </th>
					<th> Date To </th>
					<th> Payment Request Date </th>
					<th> Payment Appointment Date </th>
					<th> &nbsp; </th>
				</tr> 
			</thead>			
			<tbody>
				<c:forEach var="inprocessedFrightOrder" items="${inprocessedFrightOrder}">
					<tr>
						<td> ${inprocessedFrightOrder.frightOrderTripDetail.client_organization}  </td>
						<td> ${inprocessedFrightOrder.fon}  </td>
						<td> ${inprocessedFrightOrder.truckInformation.truck_plate_no} </td>
						<td> ${inprocessedFrightOrder.trailInformation.trail_plate_no} </td>
						<td> ${inprocessedFrightOrder.trip} </td> 
						<td> ${inprocessedFrightOrder.date_from} </td>
						<td> ${inprocessedFrightOrder.date_to} </td>
						<td> 
							<fmt:formatDate value="${inprocessedFrightOrder.frightOrderTripDetail.payment_request_date}" pattern="yyyy-MM-dd"/>  
						</td>
						<td> 
							<c:set var="app_date" value="${inprocessedFrightOrder.frightOrderTripDetail.payment_appointment_date}0"/>	
							<c:choose>
								<c:when test="${app_date == \"0\" }">
									<div onclick="getPaymentAppointmentAddForm('${loader}', '${inprocessedFrightOrder.fo_id}')" style="color: #5885c9; cursor: pointer;"> Add Appointment </div>
								</c:when>
								<c:otherwise>
									${inprocessedFrightOrder.frightOrderTripDetail.payment_appointment_date}
								</c:otherwise>
							</c:choose>														
						</td>
						<td align="center">
						
							<div onclick="frightOrderPriceUpdateForm('${loader}', '${inprocessedFrightOrder.fo_id}')" style="color: #5885c9; cursor: pointer;"> Edit </div>
							
						</td>
					</tr>				
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
		
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#inprocessfo').DataTable();
		} );
	</script>

</body>
</html>