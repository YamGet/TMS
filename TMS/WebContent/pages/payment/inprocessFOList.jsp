<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>
	
	<c:url value="/resources/calendar/css/demos.css" var="CalCssURL"/>
	<c:url value="/resources/calendar/css/themes/ui-lightness/jquery.ui.all.css" var="JqCssURL"/>
	<link href="${CalCssURL}"  rel="stylesheet" type="text/css">
	<link href="${JqCssURL}"  rel="stylesheet" type="text/css">
	
	<c:url value="/resources/js/jquery-1.12.3.js" var="JqJS123"/>
	<script type="text/javascript" src="${JqJS123}"></script>
	<c:url value="/resources/js/jquery.dataTables.min.js" var="JqJSDataTable"/>
	<script type="text/javascript" src="${JqJSDataTable}"></script>
	<c:url value="/resources/js/dataTables.bootstrap.min.js" var="JqJSDataTableBS"/>
	<script type="text/javascript" src="${JqJSDataTableBS}"></script>
	
	

</head>
<body>

	
	<table id="inprfo" class="table table-striped table-bordered" width="100%" cellspacing="0">  
		<thead> 
			<tr style="background-color: #e5e5e5; text-align: center;">
				<th> FON </th>
				<th> Net Payment </th>
				<th> Coupon Amount </th>							
			</tr> 
		</thead>			
		<tbody>
			<c:forEach var="inpFrightOrder" items="${inprocessedFrightOrder}">
				<c:set var="total_price" value="${inpFrightOrder.frightOrderTripDetail.delivered_quantity * inpFrightOrder.frightOrderTripDetail.price}"/>
				<c:set var="net_diff" value="${total_price - (total_price * inpFrightOrder.commission)}"/>
				<c:if test="${net_diff > 0.0}">
					<tr>
						<td> <input id="fon" type="checkbox" value="${inpFrightOrder.fo_id}" onchange="selectFrightOrder(this.value, '${net_diff}')"> ${inpFrightOrder.fon}  </td>
						<td> <fmt:formatNumber maxFractionDigits="2" value="${net_diff}"/>  </td>
						<td> ${inpFrightOrder.total_coupon_amount} </td>						
					</tr>
				</c:if>			
			</c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#inprfo').DataTable();
		} );
	</script>

</body>
</html>