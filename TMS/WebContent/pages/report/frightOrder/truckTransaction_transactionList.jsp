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
	
	<table><tr><td style="padding-top: 4px;">
	
		<div class="btn btn-default" onclick="rptPDF_generateTransactionHistoryPerTruck('${loader}')">
			<table align="center"><tr><td>			
				<c:url value="/resources/images/pdf.gif" var="PDF"/>
				<img alt="PDF" src="${PDF}" height="25px">
			</td><td valign="middle">
				PDF
			</td></tr></table>
		</div>
	
	</td><td>&nbsp;</td><td style="padding-top: 4px;">
	
		<div class="btn btn-default" onclick="excel_generateTransactionHistoryPerTruck('${loader}')">
			<table align="center"><tr><td>			
				<c:url value="/resources/images/excel_icon.jpg" var="excel"/>
				<img alt="excel" src="${excel}" height="25px">
			</td><td valign="middle">
				Excel
			</td></tr></table>
		</div>
	
	</td></tr></table>
	
	<div class="errorMessage"> <c:out value="${errorMessage}"/> </div>
	<div class="successMessage"> <c:out value="${successMessage}"/> </div>

	<c:set var="rowNum" value="1"/>
	
	<table width="100%">
		<tr>
			<td class="reportHeaderTitle" colspan="15">TILAHUN MESAFENT FRIGHT TRANSPORT</td>
		</tr>
		<tr>
			<td class="reportTitle" colspan="15"> 
				<c:out value="${truckTransactionList[0].truckInformation.truck_plate_no}"></c:out> Truck Transaction History
			</td>
		</tr>
		<tr>
			<td>
				<table id="tracktransaction" class="table table-striped table-bordered" width="100%" cellspacing="0">
					<thead>
						<tr class="tableHeader" style="line-height: 30px;">
							<td> No. </td>
							<td> <div style="line-height: 16px;">Trailer<br/>Plate No. </div> </td>
							<td> <div style="line-height: 16px;">Loading<br/> Date</div> </td>
							<td> <div style="line-height: 16px;">Loading<br/> Type</div> </td>
							<td> FON </td>
							<td> <div style="line-height: 16px;">Origin<br/> Place</div> </td>
							<td> Destination </td>
							<td> Quintal </td>
							<td> Price </td>
							<td> <div style="line-height: 16px;">Client<br/> Org.</div> </td>
							<td> <div style="line-height: 16px;">Advance<br/> Payment</div> </td>
							<td> <div style="line-height: 16px;">Coupon<br/> Amount</div> </td>
							<td> <div style="line-height: 16px;">Current<br/> Status</div> </td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="truckTransactionList" items="${truckTransactionList}">
							<tr>
								<td align="center"> ${rowNum} </td>
								<td> ${truckTransactionList.trailInformation.trail_plate_no} </td>
								<td> ${truckTransactionList.date_from} </td>
								<td> ${truckTransactionList.frightOrderTripDetail.loading_type} </td>
								<td> ${truckTransactionList.fon} </td>
								<td> ${truckTransactionList.frightOrderTripDetail.origin_place} </td>
								<td> ${truckTransactionList.frightOrderTripDetail.destination_place} </td>
								<td> ${truckTransactionList.frightOrderTripDetail.delivered_quantity} </td>
								<td align="right" style="padding-right: 10px;"> ${truckTransactionList.frightOrderTripDetail.price} </td>
								<td align="center"> ${truckTransactionList.frightOrderTripDetail.client_organization} </td>
								<td align="right"> ${truckTransactionList.advancePayment.advance_payment_amount} </td>
								<td align="right"> ${truckTransactionList.total_coupon_amount} </td>
								<td align="center"> 
									<c:set var="fotd_status" value="${truckTransactionList.frightOrderTripDetail.fotd_status}"/>  
									<c:choose>
										<c:when test="${fotd_status == 'Active'}">On the way</c:when>
										<c:otherwise>
											${truckTransactionList.frightOrderTripDetail.destination_place}
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
	
	<jsp:include page="/pages/footer_resource.jsp"/>

	<script type="text/javascript">
		$(document).ready(function() {
		    $('#tracktransaction').DataTable();
		} );
	</script>
	
</body>
</html>