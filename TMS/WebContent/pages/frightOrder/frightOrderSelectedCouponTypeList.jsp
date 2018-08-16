<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>

</head>
<body>

<div class="informationMessage"> <span>${assignMessage}</span> </div>

<table class="table">
	<c:forEach var="assCoupon" items="${map.getAssignedCoupon}">
		<tr> <td> * ${assCoupon.amount} Coupon - </td><td> Serial No. ${assCoupon.c_serial_no} </td></tr>
	</c:forEach>											
</table>

</body>
</html>