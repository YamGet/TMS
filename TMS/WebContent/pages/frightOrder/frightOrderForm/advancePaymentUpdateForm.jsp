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
			<h4 class="panel-title">
				Advance Payment
			</h4>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="advPay">Advance Payment Amount</label>
				<input id="advPay" type="text" class="form-control" value="${getAdvancePayment[0].advance_payment_amount}">				
			</div>					
			
			<button type="submit" class="btn btn-default" onclick="updateFrightOrderAdvancePayment('${loader}', '${advancePayment.ap_id}', '${advancePayment.fo_id}')">Update</button>
		
		</div>
	</div>
	
</body>
</html>