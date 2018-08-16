<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>
	
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div id="paymentForm">
	
<!-- 		<div style="width: 100%;" align="left">  -->
<!-- 			<div class="form-group" style="width: 250px;">			 -->
<%-- 				<input id="searchFoKey" type="text" class="form-control" onkeyup="searchClosedFrightOrderFromList('${loader}', this.value)" placeholder="Search">				 --%>
<!-- 			</div> -->
<!-- 		</div> -->
		
		<div id="closedFOSearchRslt" style="padding-top: 0px;">
	
			<table id="closedFO" class="table table-striped table-bordered" width="100%" cellspacing="0">  
				<thead> 
					<tr> 
						<th> Client Organization </th>
						<th> Freight Order No. </th> 
						<th> Truck Plate No. </th>
						<th> Trailer Plate No. </th>
						<th> Driver Name </th>						
						<th> FO Info </th>
						<th> FO Detail </th>
						<th> FO Closing Info </th>
						<th> FO Expense </th>
						<th> FO Adv. Payment </th>
					</tr> 
				</thead>
				<tbody>
					<c:forEach var="closedFrightOrderList" items="${closedFrightOrderList}">					 
						<tr>
							<td> ${closedFrightOrderList.frightOrderTripDetail.client_organization} </td>
							<td> ${closedFrightOrderList.fon} </td>
							<td> ${closedFrightOrderList.truckInformation.truck_plate_no} </td>
							<td> ${closedFrightOrderList.trailInformation.trail_plate_no} </td>
							<td> ${closedFrightOrderList.drivers.fname} ${frightOrderList.drivers.mname}</td>
							<td align="center">
								<a href="#" onclick="editFrightOrderInfo('${loader}', '${closedFrightOrderList.fo_id}')" style="text-decoration: none;"> Update </a> 
							</td>
							<td align="center">
								<a href="#" onclick="editFrightOrderDetailInfo('${loader}', '${closedFrightOrderList.fo_id}')" style="text-decoration: none;"> Update </a> 
							</td>
							<td align="center">
								<a href="#" onclick="editFrightOrderClosingInfo('${loader}', '${closedFrightOrderList.fo_id}')" style="text-decoration: none;"> Update </a> 
							</td>
							<td align="center">
								<a href="#" onclick="editFrightOrderRegisteredExpenseInfo('${loader}', '${closedFrightOrderList.fo_id}')" style="text-decoration: none;"> Update </a> 
							</td>
							<td align="center">
								<a href="#" onclick="editFrightOrderAdvancePaymentInfo('${loader}', '${closedFrightOrderList.fo_id}')" style="text-decoration: none;"> Update </a> 
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function(){
		    $('#closedFO').DataTable();
		});
	</script>

</body>
</html>