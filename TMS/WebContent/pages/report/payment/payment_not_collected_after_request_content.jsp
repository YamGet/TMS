<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	<c:set var="receivable_total" value="0"/>
	
	<div class="reportHeaderTitle"> TILAHUN MESAFENT FREIGHT TRANSPORT </div>
	
	<div class="reportTitle"> Payment Requested but Not Collected </div>
		
	<table id="afterRequest" class="table table-striped table-bordered" width="100%" cellspacing="0">
		<thead>
			<tr>
				<th width="3%" align="center"> No. </th>
				<th width="15%"> <div style="line-height: 16px;" align="center"> Client<br/>Organization </div> </th>
				<th width="10%"> <div style="line-height: 16px;" align="center"> Association<br/>Name</div> </th>
				<th width="10%"> <div style="line-height: 16px;" align="center">Freight Order<br/>Number</div> </th> 
				<th width="10%"> <div style="line-height: 16px;" align="center">Truck<br/>Plate Number</div> </th>
						
				<th width="5%"> <div align="center">Price</div> </th>
				<th width="9%"> <div style="line-height: 16px;" align="center">Delivered<br/>Quantity</div> </th>
				<th width="9%"> <div align="center">Commission</div> </th>
				<th width="10%"> <div align="center">Receivable</div> </th>
			
				<th width="13%"> <div style="line-height: 16px;" align="center">Payment Request<br/>Date</div> </th>
				<th width="5%"> <div style="line-height: 16px;" align="center">No. of<br/>Days</div> </th>					
			</tr>
		</thead>
		<tbody>
			<c:forEach var="inprocessedFrightOrder" items="${inprocessedFrightOrder}">
				<tr>
					<td align="center"> ${rowNum} </td>
					<td style="line-height: 16px;"> ${inprocessedFrightOrder.frightOrderTripDetail.client_organization}  </td>
					<td style="line-height: 16px;"> ${inprocessedFrightOrder.associations.association_name}  </td>
					<td align="center"> ${inprocessedFrightOrder.fon}  </td>
					<td> ${inprocessedFrightOrder.truckInformation.truck_plate_no} </td>
					
					<td align="right"> ${inprocessedFrightOrder.frightOrderTripDetail.price} </td>
					<td align="right"> ${inprocessedFrightOrder.frightOrderTripDetail.delivered_quantity} </td>
					<td align="right"> ${inprocessedFrightOrder.commission} </td>
					<td align="right">
						<c:set var="receivable" value="${(inprocessedFrightOrder.frightOrderTripDetail.price * inprocessedFrightOrder.frightOrderTripDetail.delivered_quantity) - (inprocessedFrightOrder.frightOrderTripDetail.price * inprocessedFrightOrder.frightOrderTripDetail.delivered_quantity * inprocessedFrightOrder.commission)}"/>
						
						<fmt:formatNumber pattern="##,###.##" value="${receivable}" var="rec_amount"/>
						
						<c:out value="${rec_amount}"/>
						
						<c:set var="receivable_total" value="${receivable_total + receivable}"/>
					</td>
					<td align="center"> 
						<fmt:formatDate value="${inprocessedFrightOrder.frightOrderTripDetail.payment_request_date}" pattern="yyyy-MM-dd"/> 
					</td>
					<td align="center"> ${inprocessedFrightOrder.no_of_days} </td>						
				</tr>			
				<c:set var="rowNum" value="${rowNum + 1}"/>
			</c:forEach>
			<tr>
				<td align="center">  ${rowNum} </td>
				<td> &nbsp; </td>
				<td> &nbsp; </td>
				<td> &nbsp; </td>
				<td> &nbsp; </td>
				<td> &nbsp; </td>
				<td> &nbsp; </td>
				<td align="right"> Total Receivable </td>
				<td align="right" style="border-bottom: thin; border-bottom-color: black; border-bottom-style: double; border-bottom-width: thick;"> 
					<fmt:formatNumber pattern="##,###.##" value="${receivable_total}" var="rec_total"/>
					<c:out value="${rec_total}"/>
				</td>
				<td> &nbsp; </td>
				<td> &nbsp; </td>
			</tr>
		</tbody>
	</table>
			
	<jsp:include page="/pages/footer_resource.jsp"/>

	<script type="text/javascript">
		$(document).ready(function() {
		    $('#afterRequest').DataTable();
		} );
	</script>
	
</body>
</html>