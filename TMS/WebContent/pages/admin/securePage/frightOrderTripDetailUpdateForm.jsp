<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>

</head>
<body>
	
	<div id="frightOrderTripDetailInfoUpdate">
	
	<c:url value="/resources/images/loader.gif" var="loader"/>

	<div class="panel panel-default" style="width: 100%;">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Trip Detail Update Form
			</h4>
		</div>
		<div class="panel-body">
		
			<c:set var="fotd_id" value="${fright.frightOrderTripDetail.fotd_id}"/>
			
			<div class="form-group">
				<label for="clientOrganization">Client Organization</label>
				<form:input id="clientOrganization" path="fright.frightOrderTripDetail.client_organization" cssClass="form-control"/>				
			</div>
			<div class="form-group">
				<label for="loadingType">Loading Type</label>
				<form:input id="loadingType" path="fright.frightOrderTripDetail.loading_type" cssClass="form-control"/>				
			</div>
			<div class="form-group">
				<label for="originPlace">Origin Place</label>
				<form:input id="originPlace" path="fright.frightOrderTripDetail.origin_place" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="destinationPlace">Destination Place</label>
				<form:input id="destinationPlace" path="fright.frightOrderTripDetail.destination_place" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="loadingQuantity">Loading Quantity</label>
				<form:input id="loadingQuantity" path="fright.frightOrderTripDetail.loading_quantity" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="distance">Distance</label>
				<form:input id="distance" path="fright.frightOrderTripDetail.distance" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="price">Price</label>
				<form:input id="price" path="fright.frightOrderTripDetail.price" class="form-control"/>				
			</div>
<!-- 						<div class="form-group"> -->
<!-- 							<label for="fon">Trip Type</label> -->
<!-- 							<select id="oilComp" class="form-control"> -->
<!-- 									<option value=" - ">-</option> -->
<!-- 									<option value="NOC">Forward</option> -->
<!-- 									<option value="TOTAL">Backward</option> -->
<!-- 							</select>				 -->
<!-- 						</div> -->
			
			<button type="submit" class="btn btn-default" onclick="updateFrightOrderDetailInformation('${loader}', '${fotd_id}')">Update</button>
		
		</div>
	</div>
	
	</div>

</body>
</html>