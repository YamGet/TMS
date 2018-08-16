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
	
	<c:set var="fo_id" value="${map.frightOrder[0].fo_id}"/>

	<div class="panel panel-default" style="width: 100%;">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Trip Detail Add Form <span style="color: RED;">(Required)</span>
			</h4>
		</div>
		<div class="panel-body">
		
			<div class="form-group">
				<label for="clientOrganization">Client Organization</label>
				<input id="clientOrganization" type="text" class="form-control">				
			</div>
			<div class="form-group">
				<label for="loadingType">Loading Type</label>
				<input id="loadingType" type="text" class="form-control">				
			</div>
			<div class="form-group">
				<label for="originPlace">Origin Place</label>
				<input id="originPlace" type="text" class="form-control">				
			</div>
			<div class="form-group">
				<label for="destinationPlace">Destination Place</label>
				<input id="destinationPlace" type="text" class="form-control">				
			</div>
			<div class="form-group">
				<label for="initialKm">Initial KM</label>
				<input id="initialKm" type="text" class="form-control">				
			</div>
			<div class="form-group">
				<label for="loadingQuantity">Loading Quantity(Quintal)</label>
				<input id="loadingQuantity" type="text" class="form-control">				
			</div>
			<div class="form-group">
				<label for="distance">Distance</label>
				<input id="distance" type="text" class="form-control">				
			</div>
			<div class="form-group">
				<label for="price">Price</label>
				<input id="price" type="text" class="form-control">				
			</div>
<!-- 						<div class="form-group"> -->
<!-- 							<label for="fon">Trip Type</label> -->
<!-- 							<select id="oilComp" class="form-control"> -->
<!-- 									<option value=" - ">-</option> -->
<!-- 									<option value="NOC">Forward</option> -->
<!-- 									<option value="TOTAL">Backward</option> -->
<!-- 							</select>				 -->
<!-- 						</div> -->
			
			<button type="submit" class="btn btn-default" onclick="insertFrightOrderDetailInformation('${loader}', '${fo_id}')">Save</button>
		
		</div>
	</div>

</body>
</html>