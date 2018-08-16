<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TMS</title>

	<c:url value="/resources/calendar/css/demos.css" var="CalCssURL"/>
	<c:url value="/resources/calendar/css/themes/ui-lightness/jquery.ui.all.css" var="JqCssURL"/>
	<link href="${CalCssURL}"  rel="stylesheet" type="text/css">
	<link href="${JqCssURL}"  rel="stylesheet" type="text/css">
	
	<c:url value="/resources/js/jquery-1.6.1.js" var="Jq161JS"/>
	<script type="text/javascript" src="${Jq161JS}"></script>
	<c:url value="/resources/calendar/js/jquery-1.5.1.js" var="Jq151JS"/>
	<script type="text/javascript" src="${Jq151JS}"></script>
	<c:url value="/resources/calendar/js/ui/jquery.ui.core.js" var="JqUiCoreJS"/>
	<script type="text/javascript" src="${JqUiCoreJS}"></script>
	<c:url value="/resources/calendar/js/ui/jquery.ui.datepicker.js" var="JqUiDPJS"/>
	<script type="text/javascript" src="${JqUiDPJS}"></script>
	
	<script>
            $(document).ready(function(){
            	
            	 $("#date_from").datepicker({
                     //minDate: 0, 
                     //maxDate: "+30D",
                     dateFormat: "yy-mm-dd",
                     numberOfMonths: 1,
                     onSelect: function(selected) {
                     	
                     	$("#date_to").datepicker("option", "minDate", selected);                        
                     }
                 });
                 $("#date_to").datepicker({
                     //minDate: 0,                    
                     //maxDate: $("#date_from").val(),
                     dateFormat: "yy-mm-dd",
                     numberOfMonths: 1,
                     onSelect: function(selected) {
                     	
                         $("#date_from").datepicker("option", "maxDate", selected);
                     }
                 });  
            });
	</script>

</head>
<body>
<div id="addFormProcess">

	<c:url value="/resources/images/loader.gif" var="loader"/>
	<c:url value="/resources/images/loader_2.gif" var="loader2"/>
	
	<div class="errorMessage" style="text-align: left;"> ${infoMessage} </div>
	
	<div class="panel panel-default" style="width: 35%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Registration Form
			</h4>
		</div>
		<div class="panel-body">
			
			
				<div class="form-group">
					<label for="associationName">Association Name</label>
					<form:select id="a_id" path="map" cssClass="form-control" >
						<form:option value="-" ></form:option>
						<form:options itemLabel="association_name" itemValue="a_id" items="${map.getAssociationList}"/>
					</form:select>		
				</div>
				<div class="form-group">
					<label for="fon">Freight Order Number</label>
					<input id="fon" type="text" class="form-control">				
				</div>
				<div class="form-group">
					<label for="TruckPlateNumber">Truck Plate Number</label>
					<form:select id="tri_id" path="map" cssClass="form-control" onchange="checkRelatedInformation(this.value, '${loader2}')">
						<form:option value="-" ></form:option>
						<form:options itemLabel="truck_plate_no" itemValue="tri_id" items="${map.getAvailableTrucks}"/>
					</form:select>				
				</div>
				<div class="form-group">
					<div id="filteredTrailList">
						<label for="TrialPlateNumber">Trailer Plate Number</label>
						<form:select id="tli_id" path="map" cssClass="form-control" >
							<form:option value="-" ></form:option>
							<%-- <form:options itemLabel="trail_plate_no" itemValue="tli_id" items="${map.getAvailableTrails}"/> --%>
						</form:select>
					</div>			
				</div>
				<div class="form-group">
					<label for="driverName">Driver Name</label>
					<form:select id="dr_id" path="map" cssClass="form-control"  >
						<form:option value="-" ></form:option>
						<form:options itemLabel="fullName" itemValue="dr_id" items="${map.getAvailableDrivers}"/>
					</form:select>				
				</div>
				<div class="form-group">
					<label for="trip">Trip Type</label>
					<select id="trip" class="form-control" disabled="disabled" >
						<option value="Single">Single</option>
						<option value="Round">Round</option>	
					</select>				
				</div>
				<div class="form-group">
					<label for="total_coupon_amount">Total Coupon Amount</label>
					<input id="total_coupon_amount" type="text" class="form-control">
				</div>
				<div class="form-group">
					<label for="commission">Commission(0.00)</label>
					<input id="commission" type="text" class="form-control" value="0.02">
				</div>
				<div class="form-group">
					<label for="date_from">Date From</label>
					<input id="date_from" type="text" class="form-control" placeholder="yyyy-MM-dd" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="date_to">Date To</label>
					<input id="date_to" type="text" class="form-control" placeholder="yyyy-MM-dd" readonly="readonly">				
				</div>			
				
				<button type="submit" class="btn btn-default" onclick="insertNewFrightOrder('${loader}')">Save</button>
				
		</div>
	</div>
</div>
</body>
</html>