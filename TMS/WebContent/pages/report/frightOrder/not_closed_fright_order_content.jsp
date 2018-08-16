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
	
	<table cellpadding="0" cellspacing="1" width="100%">  
		<tr>
			<td class="reportHeaderTitle" colspan="11">TILAHUN MESAFENT FREIGHT TRANSPORT</td>
		</tr>
		<tr>
			<td class="reportTitle" colspan="11"> Not Closed Freight Order </td>
		</tr>
		<tr>
		<td>			
			
			<table id="notclosedfo" class="table table-striped table-bordered" width="100%" cellspacing="0">
				<thead>
					<tr class="longTableHeader" >
						<th width="5%"> No. </th>
						<th> <div style="line-height: 16px;">Client<br/> Organization</div> </th>
						<th> <div style="line-height: 16px;">Freight<br/> Order No.</div> </th> 
						<th> <div style="line-height: 16px;">Truck<br/> Plate No.</div> </th>
						<th> <div style="line-height: 16px;">Trailer<br/> Plate No.</div> </th>
						<th> <div style="line-height: 16px;">Place of<br/> Origin</div> </th>
						<th> Destination </th>
						<th> Quintal </th>
						<th> Price </th>
						<th> <div style="line-height: 16px;">Total<br/> Receivable</div> </th>
						<th> <div style="line-height: 16px;">Loading<br/> Date</div> </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="frightOrderList" items="${frightOrderList}">				 
						<tr>
							<td> ${rowNum} </td>
							<td style="line-height: 16px;"> ${frightOrderList.frightOrderTripDetail.client_organization} </td>
							<td> ${frightOrderList.fon} </td>
							<td> ${frightOrderList.truckInformation.truck_plate_no} </td>
							<td> ${frightOrderList.trailInformation.trail_plate_no} </td>
							
							<td> ${frightOrderList.frightOrderTripDetail.origin_place} </td>
							<td> ${frightOrderList.frightOrderTripDetail.destination_place} </td>
							<td> ${frightOrderList.frightOrderTripDetail.loading_quantity} </td>
							
							<td> ${frightOrderList.frightOrderTripDetail.price} </td>
							<td> ${frightOrderList.frightOrderTripDetail.price * frightOrderList.frightOrderTripDetail.loading_quantity} </td>
							<td> ${frightOrderList.date_from} </td>					
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
		    $('#notclosedfo').DataTable();
		});
	</script>

</body>
</html>