<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>
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
				Drivers Registration Form
			</h4>
		</div>
		<div class="panel-body">
				<div class="errorMessage">${errorMessage} </div>
<%-- 			<frm:form method="post" commandName="Drivers">				 --%>
				<div class="form-group">
					<label for="fname">First Name <span class="required" >*</span> </label>
					<frm:input id="fname" path="drivers.fname" class="form-control" />									
				</div>
				<div class="form-group">
					<label for="mname">Middle Name <span class="required" >*</span> </label>
					<frm:input id="mname" path="drivers.mname" class="form-control" />				
				</div>
				<div class="form-group">
					<label for="gname">Grand Father Name <span class="required" >*</span> </label>
					<frm:input id="gname" path="drivers.gname" class="form-control" />				
				</div>
				<div class="form-group">
					<label for="driving_license_no">Driving License Number <span class="required" >*</span> </label>
					<frm:input id="driving_license_no" path="drivers.driving_license_no" class="form-control" />				
				</div>
				<div class="form-group">
					<label for="local_phone">Local Phone <span class="required" >*</span> </label>
					<frm:input id="local_phone" path="drivers.local_phone" class="form-control" />			
				</div>
				<div class="form-group">
					<label for="abroad_phone">Abroad Phone</label>
					<frm:input id="abroad_phone" path="drivers.abroad_phone" class="form-control" />				
				</div>				
				
				<button type="submit" class="btn btn-default" onclick="saveDriversInformation('${loader}')">Save</button>
<%-- 			</frm:form>	 --%>
			
		</div>
	</div>
				
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>