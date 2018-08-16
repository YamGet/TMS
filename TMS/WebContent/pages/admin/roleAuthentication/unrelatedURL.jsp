<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>

</head>
<body>
	
	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<c:set var="m_id" value="${usersAuth.usersRoleModuleRelation.m_id}"/>
	<c:set var="ur_id" value="${usersAuth.usersRoleModuleRelation.ur_id}"/>
	
	<div class="successMessage"> <c:out value="${successMessage}"/> </div>
	<div class="errorMessage"> <c:out value="${errorMessage}"/> </div>
	
	<table class="table table-hover">
		<tr style="background-color: #e5e5e5; text-align: center;">
			<td>Description</td>
			<td>URL</td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="unrelatedUrl" items="${unrelatedUrl}">
			<tr>
				<td> ${unrelatedUrl.description} </td>
				<td> ${unrelatedUrl.url} </td>
				<td> <span style="color: #2c73e9; cursor: pointer; text-align: center;" onclick="relateURL('${loader}', '${unrelatedUrl.usersRoleModuleRelation.urmr_id}', '${unrelatedUrl.su_id}', '${m_id}', '${ur_id}')"> Add </span> </td>
			</tr>
		</c:forEach>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>