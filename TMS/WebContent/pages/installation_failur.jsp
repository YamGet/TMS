<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TMS</title>

<c:url value="/resources/bootstrap/css/bootstrap.min.css" var="bcssURL"/>
<c:url value="/resources/css/sys_css.css" var="cssURL"/>
<link href="${bcssURL}"  rel="stylesheet" type="text/css">
<link href="${cssURL}"  rel="stylesheet" type="text/css">

</head>
<body style="margin: 0; padding-top: 150px;">
<div style="width: 100%;" align="center">
	<div class="alert alert-info" style="width: 400px; height: 200px; padding-top: 30px; font-size: 20px; text-align: center;">
		<c:url value="/resources/images/msg_information.jpeg" var="info"/>
		<img alt="warning" src="${info}" width="60px;"> <br/><br/> Installation failed. <br/> Please try again.
	</div>
</div>
</body>
</html>