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
				Fuel Card Update Form
			</h4>
		</div>
		<div class="panel-body">
			<div class="errorMessage">${errorMessage} </div>

				<div class="form-group">
					<label for="fc_company">Oil Company</label>
					<form:select path="fuelcard.fc_company" id="fc_company" class="form-control">
						<form:options itemLabel="company_name" itemValue="company_name" items="${fuelCompanyList}"/>
					</form:select>				
				</div>
				<div class="form-group">
					<label for="fc_no">Fuel Card Number</label>
					<form:input id="fc_no" path="fuelcard.fc_no" cssClass="form-control"/>				
				</div>
				<div class="form-group">
					<label for="fc_status">Fuel Card Status</label>
					<form:select id="fc_status" path="fuelcard.fc_status" class="form-control">
						<form:option value="Active"> Active </form:option>
						<form:option value="Inactive"> Inactive </form:option>
					</form:select>
				</div>
				
				<button type="submit" class="btn btn-default" onclick="updateFuelCard('${loader}', '${fuelcard.fc_id}')">Update</button>
<%-- 			</form>	 --%>
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>