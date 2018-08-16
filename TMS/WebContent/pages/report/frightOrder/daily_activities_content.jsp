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

	<div class="errorMessage"> <c:out value="${errorMessage}"/> </div>
	<div class="successMessage"> <c:out value="${successMessage}"/> </div>

	<c:set var="rowNum" value="1"/>
	
	<table width="100%">
		<tr>
			<td class="reportHeaderTitle" colspan="15">TILAHUN MESAFENT FRIGHT TRANSPORT</td>
		</tr>
		<tr>
			<td class="reportTitle" colspan="15"> 
				Daily Truck Status Report,&nbsp; 
				<jsp:useBean id="now" class="java.util.Date"/>
				<fmt:formatDate value="${now}" pattern="dd-MMM-yyyy"/>
			</td>
		</tr>
		<tr>
			<td>
			
				<table id="dailyactivity" class="table table-striped table-bordered" width="100%" cellspacing="0">
					<thead>
					<tr class="tableHeader" style="line-height: 20px;">
						<td> No. </td>
						<td> <div style="line-height: 16px;">Truck<br/> Side No.</div> </td>
						<td> <div style="line-height: 16px;">Truck<br/> Plate No.</div> </td>
						<td> <div style="line-height: 16px;">Loading<br/> Date</div> </td>
						<td> <div style="line-height: 16px;">No. of<br/> Days</div> </td>
						<td> <div style="line-height: 16px;">Loading<br/> Type</div> </td>
						<td> FON </td>
						<td> <div style="line-height: 16px;">Origin<br/> Place</div> </td>
						<td> Destination </td>
						<td> Quintal </td>
						<td> Price </td>
			<!-- 			<td> Total Payment </td> -->
						<td> <div style="line-height: 16px;">Client<br/> Org.</div> </td>
			<!-- 			<td> Payment Status </td> -->
						<td> <div style="line-height: 16px;">Advance<br/> Payment</div> </td>
						<td> <div style="line-height: 16px;">Current<br/> Status</div> </td>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="dailyActivityList" items="${dailyActivityList}">
						<tr>
							<td align="center"> ${rowNum} </td>
							<td> ${dailyActivityList.truckInformation.side_no} </td>
							<td> ${dailyActivityList.truckInformation.truck_plate_no} </td>
							<td> ${dailyActivityList.date_from} </td>
							<td align="center"> ${dailyActivityList.no_of_days} </td>
							<td> ${dailyActivityList.frightOrderTripDetail.loading_type} </td>
							<td> ${dailyActivityList.fon} </td>
							<td> ${dailyActivityList.frightOrderTripDetail.origin_place} </td>
							<td> ${dailyActivityList.frightOrderTripDetail.destination_place} </td>
							<td> ${dailyActivityList.frightOrderTripDetail.loading_quantity} </td>
							<td> ${dailyActivityList.frightOrderTripDetail.price} </td>
			<%-- 				<td> ${dailyActivityList.frightOrderTripDetail.loading_quantity * dailyActivityList.frightOrderTripDetail.price} </td> --%>
							<td> ${dailyActivityList.frightOrderTripDetail.client_organization} </td>
			<!-- 				<td>  </td> -->
							<td> 
								${dailyActivityList.advancePayment.advance_payment_amount}
								<c:set var="days" value="${dailyActivityList.no_of_days}"/>
								<c:if test="${days == 0}">*</c:if>
							</td>
							<td align="center"> 
								<c:set var="fotd_status" value="${dailyActivityList.frightOrderTripDetail.fotd_status}"/>  
								<c:choose>
									<c:when test="${fotd_status == 'Active'}">On the way</c:when>
									<c:otherwise>
										${dailyActivityList.frightOrderTripDetail.destination_place}
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<c:set var="rowNum" value="${rowNum + 1}"/>
					</c:forEach>
					</tbody>
				</table>
				
			</td>
		</tr>
	</table>

	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#dailyactivity').DataTable();
		} );
	</script>
	
</body>
</html>