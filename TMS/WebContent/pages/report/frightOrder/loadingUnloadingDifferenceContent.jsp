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
	
		<div class="btn btn-default" onclick="rptPDF_generateLoadingUnloadingDifferencePerTruck('${loader}')">
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
							<td> FON </td>
							<td> Driver Name </td>
							<td> <div style="line-height: 16px;">Loading<br/> Date</div> </td>
							<td> <div style="line-height: 16px;">Loading<br/> Type</div> </td>
							<td> <div style="line-height: 16px;">Origin<br/> Place</div> </td>
							<td> Destination </td>
							<td> <div style="line-height: 16px;">Loading<br/> Quantity</div> </td>
							<td> <div style="line-height: 16px;">Delivered<br/> Quantity</div> </td>
							<td> Difference </td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="truckLULList" items="${truckLoadingUnloadingDifferenceList}">
							<tr>
								<td align="center"> ${rowNum} </td>
								<td> ${truckLULList.fon} </td>
								<td> ${truckLULList.drivers.fname} ${truckLULList.drivers.mname} </td>
								<td> ${truckLULList.date_from} </td>
								<td> ${truckLULList.frightOrderTripDetail.loading_type} </td>								
								<td> ${truckLULList.frightOrderTripDetail.origin_place} </td>
								<td> ${truckLULList.frightOrderTripDetail.destination_place} </td>
								<td> ${truckLULList.frightOrderTripDetail.loading_quantity} </td>
								<td> ${truckLULList.frightOrderTripDetail.delivered_quantity} </td>
								<td align="right" style="padding-right: 10px;"> ${truckLULList.frightOrderTripDetail.loading_quantity - truckLULList.frightOrderTripDetail.delivered_quantity} </td>								
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