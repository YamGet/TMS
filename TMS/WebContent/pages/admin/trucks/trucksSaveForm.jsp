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
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				Truck Information Add Form
			</h4>
		</div>
		<div class="panel-body">
				<div class="errorMessage">${errorMessage} </div>
<%-- 			<form method="post" action="">				 --%>
				<div class="form-group">
					<label for="shanci_no"> Chanssis Number <span class="required"> * </span> </label>
					<form:input id="shanci_no" path="trucks.shanci_no" class="form-control" />			
				</div>
				<div class="form-group">
					<label for="truck_plate_no"> Truck Plate Number <span class="required"> * </span> </label>
					<form:input id="truck_plate_no" path="trucks.truck_plate_no" class="form-control" />		
				</div>
				<div class="form-group">
					<label for="side_no"> Side Number </label>
					<form:input id="side_no" path="trucks.side_no" class="form-control" />				
				</div>
				<div class="form-group">
					<label for="truck_model"> Truck Model <span class="required"> * </span> </label>
					<form:input id="truck_model" path="trucks.truck_model" class="form-control" />				
				</div>
				<div class="form-group">
					<label for="loading_capacity"> Loading Capacity (Quintal) <span class="required"> * </span> </label>
					<form:input id="loading_capacity" path="trucks.loading_capacity" class="form-control" />				
				</div>
				<div class="form-group">
					<label for="truck_owner"> Truck Owner <span class="required"> * </span> </label>
					<form:input id="truck_owner" path="trucks.truck_owner" class="form-control" value="Ato Tilahun Mesafent"/>				
				</div>
				<div class="form-group">
					<label for="truck_type"> Truck Carrying Type <span class="required"> * </span> </label>
					<select id="truck_type" class="form-control">
						<option value="-">-</option>
						<option value="DRY">DRY</option>
						<option value="FLUID">FLUID</option>
					</select>				
				</div>
							
				<button type="submit" class="btn btn-default" onclick="saveTruckInformation('${loader}')">Save</button>
<%-- 			</form>	 --%>
						
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>