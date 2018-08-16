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
	
	<div class="panel panel-default" style="width: 100%">
		<div class="panel-heading">						
			<table width="100%"><tr><td>
				<h4 class="panel-title"> Advance Payment </h4> 
			</td><td align="right"> 
<!-- 				<span class="onclick">Edit</span>  -->
			</td></tr></table>
		</div>
		<table class="table">
			<c:forEach var="adv_payment" items="${advancePaymentList}">
				<tr><td>Advance Payment - </td><td> ${adv_payment.advance_payment_amount} Birr </td></tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>