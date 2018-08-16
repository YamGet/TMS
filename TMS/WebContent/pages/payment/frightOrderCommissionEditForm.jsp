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
	
	<c:set var="fo_id" value="${payment.fo_id}"/>
	
	<div class="panel panel-default" style="width: 35%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Commission Edit Form
			</h4>
		</div>
		<div class="panel-body">
		
			<div class="form-group">
				<label for="foCommission">Commission Amount</label>
				<input id="foCommission" type="text" class="form-control">
			</div>
			
			<button type="submit" class="btn btn-default" onclick="editFrightOrderCommission('${loader}', '${fo_id}')">Save</button>
			
		</div>
	</div>
	
</body>
</html>