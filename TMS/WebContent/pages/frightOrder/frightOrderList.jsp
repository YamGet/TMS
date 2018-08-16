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
	
	<table id="folist" class="table table-striped table-bordered" width="100%" cellspacing="0">  
		<thead> 
			<tr> 
				<th> Association Name </th>
				<th> Freight Order No. </th> 
				<th> Truck Plate No. </th>
				<th> Trailer Plate No. </th>
				<th> Driver Name </th>
				<th> Trip Type </th>
				<th> Date From </th>
				<th> Date To </th>
				<th> &nbsp; </th>
				<th> &nbsp; </th>
			</tr> 
		</thead>
		<tbody>
			<c:forEach var="frightOrderList" items="${frightOrderList}">			 
				<tr>
					<td> ${frightOrderList.associations.association_name} </td>
					<td> ${frightOrderList.fon} </td>
					<td> ${frightOrderList.truckInformation.truck_plate_no} </td>
					<td> ${frightOrderList.trailInformation.trail_plate_no} </td>
					<td> ${frightOrderList.drivers.fname} ${frightOrderList.drivers.mname}</td>
					<td> ${frightOrderList.trip} </td> 
					<td> ${frightOrderList.date_from} 
<%-- 					<fmt:formatDate value="${frightOrderList.date_from}" pattern="dd-MM-yyyy"/>  --%>
					</td>
					<td> ${frightOrderList.date_to} 
<%-- 					<fmt:formatDate value="${frightOrderList.date_to}" pattern="dd-MM-yyyy"/>  --%>
					</td>
					<td> 
						<a href="#" onclick="closeFrightOrderForm('${frightOrderList.fo_id}', '${loader}')" style="text-decoration: none;"> Close </a> 
					</td>
					<td> 
						<a href="#" onclick="showFrightOrderDetail('${frightOrderList.fo_id}', '${loader}')" style="text-decoration: none;"> Detail </a> 
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#folist').DataTable();
		});
	</script>

</body>
</html>