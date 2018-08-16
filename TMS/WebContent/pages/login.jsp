<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>TMS</title>
	
	<c:url value="/resources/bootstrap/css/bootstrap.min.css" var="bcssURL"/>
	<c:url value="/resources/css/sys_css.css" var="cssURL"/>
	<c:url value="/resources/images/4amen.ico" var="iconURL"/>
	<link href="${bcssURL}"  rel="stylesheet" type="text/css">
	<link href="${cssURL}"  rel="stylesheet" type="text/css">
	<link href="${iconURL}" rel="icon" type="image/ico">
	
	<style type="text/css">
	.errors{
		color: RED;
	}
	</style>

</head>
<body style="margin: 0px; padding-top: 40px;">

	<div style="width: 100%; text-align: center;" align="center">
	<c:url value="/resources/images/4amen.png" var="tmsLogo"/>
	<img alt="Logo" src="${tmsLogo}" style="width: 85px;">
	</div>
	
	<div class="header" style="width: 100%; text-align: center; color: #54b649">
		<!-- TILAHUN MESAFENT <br/> Fright Transport -->
		<span style="font-weight: bold;"> FIDEL </span> <br/> Transport Management System
	</div>
	
	<div style="width: 100%; background-color: #e5e5e5; padding-top: 10px; padding-bottom: 10px;" align="center">
		
		<div style="width: 350px; padding-top: 10px;">
		
			<c:if test="${not empty input_error}">
				<div class="alert alert-danger">${input_error}</div>
			</c:if>
			
			<form class="form-horizontal" action="/TMS/user/validateLoginForm.html" role="form" method="post">
				<div class="form-group">
					<span class="errors">${username_error}</span>
					<input type="text" class="form-control" id="username" name="user_name" placeholder="User Name">
<%-- 					<form:input path="user.user_name" class="form-control" id="username" />				 --%>
				</div>
				<div class="form-group">
					<span class="errors">${password_error}</span>
					<input type="password" class="form-control" id="password" name="password" placeholder="Password">
<%-- 					<form:password path="user.password" class="form-control" id="password" />				 --%>
				</div>
				<button type="submit" class="btn btn-default" style="width: 100px;">Login</button>
			</form>		
	
		</div>
	
	</div>
	
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

</body>
</html>