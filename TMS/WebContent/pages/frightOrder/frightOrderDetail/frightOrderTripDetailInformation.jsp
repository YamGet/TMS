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

	<div class="panel panel-default" style="width: 100%">
		<div class="panel-heading">						
			<table width="100%"><tr><td>
				<h4 class="panel-title"> Order Trip Detail Information </h4> 
			</td><td align="right"> 
				<c:set var="fotd_id" value="${foDetail.fotd_id}"/>
				<span class="onclick" onclick="getFrightOrderTripDetailInfoUpdateForm('${loader}', '${fotd_id}')">Edit</span>
			</td></tr></table>
		</div>
		<table class="table">
			<tr> <td> Client - </td><td> ${foDetail.client_organization} </td></tr>
			<tr> <td> Loading Type - </td><td> ${foDetail.loading_type} </td></tr>
			<tr> <td> Origin - </td><td> ${foDetail.origin_place} </td></tr>
			<tr> <td> Destination - </td><td> ${foDetail.destination_place} </td></tr>
			<tr> <td> Initial KM Reading - </td><td> ${foDetail.initial_km} </td></tr>
			<tr> <td> Quantity - </td><td> ${foDetail.loading_quantity} </td></tr>
			<tr> <td> Distance - </td><td> ${foDetail.distance} </td></tr>
			<tr> <td> Price - </td><td> ${foDetail.price} </td></tr>						
		</table>
	</div>

</body>
</html>