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
	
	<c:set var="user_id" value="${user.user_id}"/> 
	
	<div class="panel panel-default" style="width: 100%;">
		<div class="panel-heading">
			<h4 class="panel-title">
				Users Information Update Form
			</h4>
		</div>
		<div class="panel-body">
								
			<div class="form-group">
				<label for="fname">First Name</label>
				<form:input id="fname" path="user.fname" type="text" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="mname">Middle Name</label>
				<form:input id="mname" path="user.mname" type="text" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="gname">Grandfather Name</label>
				<form:input id="gname" path="user.gname" type="text" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="username">Username</label>
				<form:input id="username" path="user.user_name" type="text" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<form:input id="password" path="user.password" type="password" class="form-control"/>				
			</div>
			<div class="form-group">
				<label for="fon">User Role</label>												
				<form:select id="ur_id" path="user.ur_id" cssClass="form-control">
					<form:options itemLabel="userrole_name" itemValue="ur_id" items="${map.usersRoleList}"/>
				</form:select>
			</div>
			<div class="form-group">
				<label for="user_status">User Status</label>
				<form:select id="user_status" path="user.user_status" class="form-control">
					<form:option value="Active"> Active </form:option>
					<form:option value="Inactive"> Inactive </form:option>
				</form:select>
			</div>
							
			<button type="submit" class="btn btn-default" onclick="updateUserInfo('${loader}', '${user_id}')">Update</button>
						
		</div>
	</div>

</body>
</html>