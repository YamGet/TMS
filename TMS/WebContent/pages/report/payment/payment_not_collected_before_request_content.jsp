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
	
	<c:set var="receivable_total" value="0"/>
	
	<c:set var="rowNum" value="1"/>
	<table  cellpadding="0" cellspacing="1" width="100%" border="0"> 
		<tr>
			<td class="reportHeaderTitle" colspan="11">TILAHUN MESAFENT FREIGHT TRANSPORT</td>
		</tr>
		<tr>
			<td class="reportTitle" colspan="11"> Freight Order Not Requested for Payment </td>
		</tr>
		<tr>
		<td>
		<table width="100%">		
		<tr class="tableHeader">
			<th width="5%"> <div style="line-height: 16px;" align="center">No.</div> </th>
			<th width="20%"> <div style="line-height: 16px;" align="center">Client<br/> Organization</div> </th>
			<th width="15%"> <div style="line-height: 16px;" align="center">Association <br/> Name</div> </th>
			<th width="10%"> <div style="line-height: 16px;" align="center">Freight<br/> Order No.</div> </th> 
			<th width="10%"> <div style="line-height: 16px;" align="center">Truck<br/> Plate No.</div> </th>
			
			<th width="5%"> <div style="line-height: 16px;" align="center">Price</div> </th>
			<th width="10%" align="center"> <div style="line-height: 16px;" align="center">Delivered<br/> Quantity</div> </th>
			<th width="5%"> <div style="line-height: 16px;" align="center">Commission</div> </th>
			<th width="5%"> <div style="line-height: 16px;" align="center">Receivable</div> </th>			
<!-- 			<th> Date From </th> -->
<!-- 			<th> Date To </th> -->
			<th width="10%"> <div style="line-height: 16px;" align="center">FO <br/> Close Date</div> </th>
			<th width="5%"> <div style="line-height: 16px;" align="center">No. of<br/> Days</div></th>		
		</tr>
		<c:forEach var="unprocessedFrightOrder" items="${unprocessedFrightOrder}">
			<tbody> 
				<tr <c:choose><c:when test="${rowNum%2 == 0}">style="line-height: 35px; background-color: #e5e5e5;"</c:when><c:otherwise>style="line-height: 35px;"</c:otherwise> </c:choose>>
					<td> ${rowNum} </td>
					<td style="line-height: 16px;"> ${unprocessedFrightOrder.frightOrderTripDetail.client_organization}  </td>
					<td> ${unprocessedFrightOrder.associations.association_name}  </td>
					<td align="center"> ${unprocessedFrightOrder.fon}  </td>
					<td> ${unprocessedFrightOrder.truckInformation.truck_plate_no} </td>
					
					<td align="right"> ${unprocessedFrightOrder.frightOrderTripDetail.price} </td>
					<td align="right"> ${unprocessedFrightOrder.frightOrderTripDetail.delivered_quantity} </td>
					<td align="center"> ${unprocessedFrightOrder.commission} </td>
					<td align="right"> 
											  
						<c:set var="receivable" value="${(unprocessedFrightOrder.frightOrderTripDetail.price * unprocessedFrightOrder.frightOrderTripDetail.delivered_quantity) - (unprocessedFrightOrder.frightOrderTripDetail.price * unprocessedFrightOrder.frightOrderTripDetail.delivered_quantity * unprocessedFrightOrder.commission)}"/>
						
						<c:out value="${receivable}"/>
						
						<c:set var="receivable_total" value="${receivable_total + receivable}"/>
						
					</td>
					
<%-- 					<td> ${unprocessedFrightOrder.date_from} </td> --%>
<%-- 					<td> ${unprocessedFrightOrder.date_to} </td> --%>
					<td align="center"> ${unprocessedFrightOrder.frightOrderTripDetail.close_date} </td>
					<td align="center"> ${unprocessedFrightOrder.no_of_days} </td>
				</tr>
			</tbody>
			<c:set var="rowNum" value="${rowNum + 1}"/>
		</c:forEach>
		<tr style="border-top: thin; border-top-color: black; border-top-style: solid; border-top-width: thin;">
			<td colspan="8" align="right"> Total Receivable </td>
			<td align="right" style="border-bottom: thin; border-bottom-color: black; border-bottom-style: double; border-bottom-width: thick;"> 
				<fmt:formatNumber pattern="##,###.##" value="${receivable_total}" var="rec_total"/>
				<c:out value="${rec_total}"/> 
			</td>
			<td> </td><td> </td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>