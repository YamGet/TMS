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
				<h4 class="panel-title">Freight Order Information</h4>  
			</td><td align="right"> 
				<c:set var="fo_id" value="${frightOrderList[0].fo_id}"/>
				<span class="onclick" onclick="getFrightOrderInformationUpdateForm('${loader}', '${fo_id}')">Edit</span> 
			</td></tr></table>
			
		</div>
		<table class="table">
			<c:forEach var="frightOrderList" items="${frightOrderList}">
			<tr> <td> Association - </td><td> ${frightOrderList.associations.association_name} </td></tr>
			<tr> <td> FON - </td><td> ${frightOrderList.fon} </td></tr>
			<tr> <td> Truck Plate No - </td><td> ${frightOrderList.truckInformation.truck_plate_no} </td></tr>
			<tr> <td> Trail Plate No - </td><td> ${frightOrderList.trailInformation.trail_plate_no} </td></tr>
			<tr> <td> Driver - </td><td> ${frightOrderList.drivers.fname} ${frightOrderList.drivers.mname} </td></tr>
			<tr> <td> Date From - </td><td> ${frightOrderList.date_from} </td></tr>
			<tr> <td> Date to - </td><td> ${frightOrderList.date_to} </td></tr>						
			</c:forEach>
		</table>
	</div>

</body>
</html>