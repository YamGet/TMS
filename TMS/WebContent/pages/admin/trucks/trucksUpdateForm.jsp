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
	
	<div class="panel panel-default" style="width: 100%;">
		<div class="panel-heading">
			<h4 class="panel-title">
				Truck Information Update Form
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
					<label for="side_no"> Side Number <span class="required"> * </span> </label>
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
					<form:input id="truck_owner" path="trucks.truck_owner" class="form-control" />				
				</div>
				<div class="form-group">
					<label for="truck_type"> Truck Carrying Type <span class="required"> * </span> </label>					
					<form:select id="truck_type" path="trucks.truck_type" class="form-control">
						<form:option value="DRY"> DRY </form:option>
						<form:option value="FLUID"> FLUID </form:option>
					</form:select>			
				</div>
				<div class="form-group">
					<label for="truck_status">Truck Status</label>
					<form:select id="truck_status" path="trucks.truck_status" class="form-control">
						<form:option value="Active"> Active </form:option>
						<form:option value="Inactive"> Inactive </form:option>
					</form:select>
				</div>
							
				<button type="submit" class="btn btn-default" onclick="updateTruckInformation('${loader}', '${trucks.tri_id}')">Update</button>
<%-- 			</form>	 --%>
						
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>