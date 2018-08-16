<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<table cellpadding="0" cellspacing="0" width="100%" border="0">
		<tr>
			<td valign="top" width="60%" align="left">
			
				<table id="couponlist" class="table table-striped table-bordered" cellpadding="0" cellspacing="1" width="100%">  
					<thead> 
						<tr style="text-align: center;"> 
							<th> Oil Company </th> 
							<th> Coupon Amount </th>
							<th> Coupon Serial No. </th>
							<th> Coupon Status </th>
						</tr> 
					</thead>
					<tbody>
						<c:forEach var="couponList" items="${couponList}">						 
							<tr>
								<td> 
									<c:set var="c_id" value="${couponList.c_id}"/>
									<c:set var="oil_company" value="${couponList.oil_company}"/>
									<c:set var="amount" value="${couponList.amount}"/>
									<c:set var="c_serial_no" value="${couponList.c_serial_no}"/>
								
									<div class="onclick" onclick="getCouponUpdateForm('${loader}', '${c_id}', '${oil_company}', '${amount}', '${c_serial_no}')">
										${couponList.oil_company} 
									</div>
								</td>
								<td> ${couponList.amount} </td>
								<td> ${couponList.c_serial_no} </td>
								<td> ${couponList.c_status} </td> 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
			</td>
			<td width="10%">&nbsp;</td>
			<td valign="top">
			
				<div id="couponForm">
					<jsp:include page="couponRegistrationSaveForm.jsp"/>
				</div>
			
			</td>
		</tr>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#couponlist').DataTable();
		} );
	</script>
	
</body>
</html>