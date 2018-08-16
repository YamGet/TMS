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

	
		<div class="panel panel-default" style="width: 400px;;">
			<div class="panel-heading">
				<h4 class="panel-title">
					Association Registration Form
				</h4>
			</div>
			<div class="panel-body">
					<div class="errorMessage">${errorMessage} </div>
<%-- 				<form action="">				 --%>
					<div class="form-group">
						<label for="association_name">Association Name <span class="required" >*</span> </label>
						<input id="association_name" type="text" class="form-control">				
					</div>
					
					<button type="submit" class="btn btn-default" onclick="saveAssociation('${loader}')">Save</button>
<%-- 				</form>	 --%>
				
			</div>
		</div>
	
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>