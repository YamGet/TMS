<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>
	
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="errorMessage"> <c:out value="${errorMessage}"/> </div>
	<div class="successMessage"> <c:out value="${successMessage}"/> </div>
	
	<c:set var="rowNum" value="1"/>
	<c:set var="total_collected" value="0"/>
	
	<table  cellpadding="0" cellspacing="1" width="100%"> 
		<tr>
			<td class="reportHeaderTitle" colspan="9">TILAHUN MESAFENT FREIGHT TRANSPORT</td>
		</tr>
		<tr>
			<td class="reportTitle" colspan="9"> Collected Payment </td>
		</tr>
		<tr>
		<td>
		<table width="100%">
		<tr class="tableHeader" align="center">
			<th width="5%"> No. </th>
			<th width="20%"> <div style="line-height: 16px;" align="center">Client<br/>Organization</div> </th>
			<th width="10%"> <div style="line-height: 16px;" align="center">Freight<br/>Order No.</div> </th> 
			<th width="10%"> <div style="line-height: 16px;" align="center">Truck<br/>Plate No.</div> </th>
			<th width="5%"> <div style="line-height: 16px;" align="center">Price</div> </th>
			<th width="10%"> <div style="line-height: 16px;" align="center">Delivered<br/>Quantity</div> </th>
			<th width="5%"> <div style="line-height: 16px;" align="center">Commission</div> </th>
			<th width="5%"> <div style="line-height: 16px;" align="center">CRSI Number</div> </th>
			<th width="10%"> <div style="line-height: 16px;" align="center">Payment<br/>Amount</div> </th>						
			<th width="10%"> <div style="line-height: 16px;" align="center">Payment<br/>Date</div> </th>
			<th width="5%"> <div style="line-height: 16px;" align="center">Place<br/>of Origin</div> </th>
			<th width="10%"> <div style="line-height: 16px;" align="center">Destination<br/>Place</div> </th>				
		</tr>
		<c:forEach var="collectedPyamentList" items="${collectedPyamentList}">
			<tbody> 
				<tr <c:choose><c:when test="${rowNum%2 == 0}">style="line-height: 35px; background-color: #e5e5e5;"</c:when><c:otherwise>style="line-height: 35px;"</c:otherwise> </c:choose>>
					<td> ${rowNum} </td>
					<td style="color: #3d6e9f; cursor: pointer;"> <span title="Payment Type: ${collectedPyamentList.payment.payment_type}, Payment Document Reference No.: ${collectedPyamentList.payment.payment_doc_ref_no}, CRSI No.: ${collectedPyamentList.frightOrderTripDetail.crv_number}"> ${collectedPyamentList.frightOrderTripDetail.client_organization} </span>  </td>
					<td> ${collectedPyamentList.fon}  </td>
					<td> ${collectedPyamentList.truckInformation.truck_plate_no} </td>
					<td align="right"> ${collectedPyamentList.frightOrderTripDetail.price} </td>
					<td align="center"> ${collectedPyamentList.frightOrderTripDetail.delivered_quantity} </td>
					<td align="center"> ${collectedPyamentList.commission} </td>
					<td align="center"> ${collectedPyamentList.frightOrderTripDetail.crv_number} </td>
					<td align="right" style="padding-right: 15px;"> 
						<fmt:formatNumber pattern="##,###.##" value="${collectedPyamentList.payment.payment_amount}" var="payment_amount"/>
						<c:out value="${payment_amount}"/> 
					</td>
					<td align="center"> ${collectedPyamentList.payment.payment_date} </td>
					<td align="center"> ${collectedPyamentList.frightOrderTripDetail.origin_place} </td>
					<td align="center"> ${collectedPyamentList.frightOrderTripDetail.destination_place} </td>
					<c:set var="total_collected" value="${total_collected + collectedPyamentList.payment.payment_amount}"/>					
				</tr>
			</tbody>
			<c:set var="rowNum" value="${rowNum + 1}"/>
		</c:forEach>
		<tr style="border-top: thin; border-top-color: black; border-top-style: solid; border-top-width: thin;">
			<td colspan="8" align="right"> Total Collected </td>
			<td align="right" style="padding-right: 15px; border-bottom: thin; border-bottom-color: black; border-bottom-style: double; border-bottom-width: thick;"> 
				<fmt:formatNumber pattern="##,###.##" value="${total_collected}" var="total_collected"/>
				<c:out value="${total_collected}"/> 
			</td>
			<td colspan="3"> </td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>