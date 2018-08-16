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
				Users Role Registration Form
			</h4>
		</div>
		<div class="panel-body">
				<div class="errorMessage">${errorMessage} </div>
<%-- 			<frm:form method="post" commandName="Drivers">				 --%>
				<div class="form-group">
					<label for="userrole_name">Users Role <span class="required" >*</span> </label>
					<form:input id="userrole_name" path="usersRole.userrole_name" class="form-control" />									
				</div>							
				
				<button type="submit" class="btn btn-default" onclick="saveUsersRole('${loader}')">Save</button>
<%-- 			</frm:form>	 --%>
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>