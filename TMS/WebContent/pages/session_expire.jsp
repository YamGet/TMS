<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="/pages/header_resource.jsp"/>

</head>
<body>

	<div style="width: 100%" align="center">
		<div class="alert alert-info" style="width: 30%;">
			<c:url value="/resources/images/msg_information.jpeg" var="info"/>
			<img alt="Info"  src="${info}" width="60px;"><br/><br/>
			The session expire. Please login again.<br/><br/>
			<a href="login.html">Move to Login Page</a>			
		</div>
	</div>
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>