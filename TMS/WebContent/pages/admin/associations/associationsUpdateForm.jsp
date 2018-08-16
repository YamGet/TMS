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
					Association Update Form
				</h4>
			</div>
			<div class="panel-body">
					<div class="errorMessage">${errorMessage} </div>
<%-- 				<form action="">									 --%>
					<div class="form-group">
						<label for="association_name">Association Name</label>
						<input id="association_name" type="text" class="form-control" value="${associations.association_name}">				
					</div>									
					<div class="form-group">
						<label for="association_status">Association Status</label>
						<select id="association_status" class="form-control">
							<option value="Active" <c:if test="${associations.association_status == 'Active'}" > selected="selected" </c:if>>Active</option>
							<option value="Inactive" <c:if test="${associations.association_status == 'Inactive'}" > selected="selected" </c:if>>Inactive</option>
						</select>
					</div>
					
					<button type="submit" class="btn btn-default" onclick="updateAssociation('${loader}', '${associations.a_id}')">Update</button>
<%-- 				</form>	 --%>
				
			</div>
		</div>
	
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>