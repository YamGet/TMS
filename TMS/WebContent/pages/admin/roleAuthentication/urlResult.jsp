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
	
	<c:set var="m_id" value="${usersAuth.usersRoleModuleRelation.m_id}"/>
	<c:set var="ur_id" value="${usersAuth.usersRoleModuleRelation.ur_id}"/>
	
	<ul class="nav nav-tabs">
		<li id="relatedURL" class="active" onclick="getRelatedURL('${loader}', '${m_id}', '${ur_id}')"> <a href="#"> Related URLs </a> </li>
		<li id="unrelatedURL" onclick="getUnrelatedURL('${loader}', '${m_id}', '${ur_id}')"> <a href="#"> Unrelated URLs </a> </li>
	</ul>
	
	<div id="roleAuthContent" style="padding-top: 15px;">
	
		<jsp:include page="relatedURL.jsp"/>
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>