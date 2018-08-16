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
	
	<div id="notProcessFOContent" style="padding-top: 15px;">
	
		<table id="unprocessfo" class="table table-striped table-bordered" width="100%" cellspacing="0">  
			<thead> 
				<tr> 
					<th> Client Organization </th>
					<th> Freight Order No. </th> 
					<th> Truck Plate No. </th>
					<th> Trailer Plate No. </th>
					<th> Trip Type </th>
					<th> Date From </th>
					<th> Date To </th>
					<th> Coupon Status </th>
					<th> &nbsp; </th>
				</tr> 
			</thead>
			<tbody>
				<c:forEach var="unprocessedFrightOrder" items="${unprocessedFrightOrder}">				 
					<tr>
						<td> ${unprocessedFrightOrder.frightOrderTripDetail.client_organization}  </td>
						<td> ${unprocessedFrightOrder.fon}  </td>
						<td> ${unprocessedFrightOrder.truckInformation.truck_plate_no} </td>
						<td> ${unprocessedFrightOrder.trailInformation.trail_plate_no} </td>
						<td> ${unprocessedFrightOrder.trip} </td> 
						<td> ${unprocessedFrightOrder.date_from} </td>
						<td> ${unprocessedFrightOrder.date_to} </td>
	<!-- 					<td>  -->
	<%-- 						<a href="#" onclick="showDetailPaymentInformation('${loader}')" style="text-decoration: none;"> Show Detail </a>  --%>
	<!-- 					</td> -->
						<td align="center">
							<input type="checkbox" onclick="deductCouponValue()"> Deducted
						</td>
						<td> 
							<a href="#" onclick="printPaymentInformation('${loader}', '${unprocessedFrightOrder.fo_id}')" style="text-decoration: none;"> Print </a> 
						</td>
					</tr>				
				</c:forEach>
			</tbody>
		</table>
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#unprocessfo').DataTable();
		} );
	</script>
	
</body>
</html>