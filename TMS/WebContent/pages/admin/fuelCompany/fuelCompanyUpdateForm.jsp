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
				Fuel Compnay Update Form
			</h4>
		</div>
		<div class="panel-body">
			<div class="errorMessage">${errorMessage} </div>
		
			<div class="form-group">
				<label for="company_name">Fuel Company Name*</label>
				<form:input id="company_name" path="fuelcompany.company_name" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="contact_person_name">Contact Person Name</label>
				<form:input id="contact_person_name" path="fuelcompany.contact_person_name" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="contact_person_phone">Contact Person Phone</label>
				<form:input id="contact_person_phone" path="fuelcompany.contact_person_phone" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="fc_status">Fuel Company Status</label>
				<form:select id="fc_status" path="fuelcompany.fc_status" class="form-control">
					<form:option value="Active"> Active </form:option>
					<form:option value="Inactive"> Inactive </form:option>
				</form:select>
			</div>
			
			<button type="submit" class="btn btn-default" onclick="updateFuelCompany('${loader}', '${fuelcompany.fc_id}')">Update</button>
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>