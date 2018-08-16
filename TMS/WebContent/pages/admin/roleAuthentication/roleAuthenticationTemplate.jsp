<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>

</head>
<body>
	
	<c:url value="/resources/images/loader_2.gif" var="loader"/>
	
	<table>
		<tr>
			<td>
				<label for="usersRole">Users Role</label>
			</td>
			<td style="padding-left: 10px;">
				<form:select id="ur_id" path="map" cssClass="form-control" onchange="getUsersRoleModuleList('${loader}', this.value)">
					<form:option value="-" ></form:option>
					<form:options itemLabel="userrole_name" itemValue="ur_id" items="${map.usersRoleList}"/>
				</form:select>
			</td>
			<td width="20px;">&nbsp;</td>
			<td>
				<label for="module">Module</label>
			</td>
			<td style="padding-left: 10px;" id="moduleList">
				<form:select id="ur_id" path="map" cssClass="form-control" style="width: 150px;">
					<form:option value="-" ></form:option>
				</form:select>
			</td>
		</tr>
	</table>
	
	<div style="padding-top: 10px;"></div>
	
	<div id="moduleURLList">
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>