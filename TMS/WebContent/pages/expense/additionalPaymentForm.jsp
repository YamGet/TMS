<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="panel panel-default" style="width: 50%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Additional Expense Payment Form
			</h4>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="additional_amount">Additional Expense Amount</label>
				<input id="additional_amount" value="${advancePayment.additional_amount}" type="text" class="form-control" readonly="readonly">				
			</div>
			
			<button type="submit" class="btn btn-default" onclick="saveAdditionalExpenseAmount('${loader}', '${advancePayment.ap_id}', '${advancePayment.fo_id}')">Save</button>
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>