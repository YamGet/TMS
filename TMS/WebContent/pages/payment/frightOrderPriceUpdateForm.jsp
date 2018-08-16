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
	
	<div class="panel panel-default" style="width: 35%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Price Add Form
			</h4>
		</div>
		<div class="panel-body">			
				
				<div class="form-group">
					<label for="price">Price</label>
					<form:input id="price" path="fright.frightOrderTripDetail.price" class="form-control"/>				
				</div>
				<div class="form-group">
					<label for="commission">Commission</label>
					<form:input id="commission" path="fright.commission" class="form-control"/>				
				</div>
				<div class="form-group">
					<label for="price">CRSI Number</label>
					<form:input id="crv_number" path="fright.frightOrderTripDetail.crv_number" class="form-control"/>				
				</div>							
				
				<button type="submit" class="btn btn-default" onclick="updateFrightOrderPrice('${loader}', '${fright.fo_id}')">Save</button>

		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>