<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

	<c:url value="/resources/images/loader.gif" var="loader"/>
	<c:url value="/resources/images/loader_2.gif" var="loader2"/>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			
				<div class="form-group">
					
					<div style="width: 100%; text-align: right; padding-bottom: 10px;"> * Notice: Date considers Loading Date </div>
					
					<table><tr><td style="padding-top: 4px;">
	
						<select id="truck_type" onchange="filterTruckByTruckType(this.value, '${loader2}')" class="form-control">
							<option value="BOTH">BOTH TRUCKS TYPE</option>
							<option value="DRY">TRUCKS-DRY</option>
							<option value="FLUID">TRUCKS-FLUID</option>
						</select>
						
					</td><td>&nbsp;</td><td>
					
						Truck Plate Number
						
					</td><td>&nbsp;</td><td>
						
						<div id="filteredTruckList">
							<form:select id="tri_id" path="map" cssClass="form-control">
								<form:option value="-" ></form:option>
								<form:options itemLabel="truck_plate_no" itemValue="tri_id" items="${map.truckList}"/>
							</form:select>
						</div>
						
					</td><td style="width: 10px;">&nbsp;</td>
					<td> Date From </td> <td style="padding-left: 10px;"> <input id="date_from" type="text" class="form-control" placeholder="dd-MM-yyyy" style="width: 105px;"> </td>
					<td style="width: 10px;">&nbsp;</td>
					<td> Date To </td> <td style="padding-left: 10px;"> <input id="date_to" type="text" class="form-control" placeholder="dd-MM-yyyy" style="width: 105px;"> </td>
					<td style="padding-left: 10px;"> 
						<input type="checkbox" value="All" onchange="generateLifeTimeTransactionHistoryPerTruck(this.value)"> All						
					</td>
					<td style="padding-left: 10px;"> 
						<input id="extype" type="text" style="display: none; width: 10px;" value="0">					
					</td>
					<td style="padding-left: 10px;">					
						<button type="submit" class="btn btn-default" onclick="getTransactionHistoryPerTruck('${loader}')">Generate</button>
					</td>					
					</tr></table>		
				</div>
			
			</td>
		</tr>
		<tr>
			<td class="topLine">
				
				<div id="reportSearchResult" style="padding-top: 20px;">  </div>
			
			</td>
		</tr>
	</table>

	
	
</body>
</html>