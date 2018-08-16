<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>

	<table cellpadding="0" cellspacing="0" width="100%" border="0">
		<tr>
			<td valign="top" width="60%" align="left">
			
				<table id="truckListTbl" class="table table-striped table-bordered" cellpadding="0" cellspacing="1" width="80%">  
					<thead> 
						<tr> 
							<th> Chanssis No. </th> 
							<th> Plate No. </th>
							<th> Truck <br/> Model </th>
							<th> Loading <br/> Capacity </th>
							<th> Truck <br/> Status </th>
							<th> Truck <br/> Owner </th>
						</tr> 
					</thead>
					<tbody>
						<c:forEach var="trucksList" items="${trucksList}">						 
							<tr>
								<td>
									<c:set var="tri_id" value="${trucksList.tri_id}"/>
									<c:set var="shanci_no" value="${trucksList.shanci_no}"/>
									<c:set var="truck_plate_no" value="${trucksList.truck_plate_no}"/>
									<c:set var="side_no" value="${trucksList.side_no}"/>
									<c:set var="truck_model" value="${trucksList.truck_model}"/>
									<c:set var="loading_capacity" value="${trucksList.loading_capacity}"/>
									<c:set var="truck_owner" value="${trucksList.truck_owner}"/>
									<c:set var="truck_type" value="${trucksList.truck_type}"/>
									<c:set var="truck_status" value="${trucksList.truck_status}"/>
								
									<div onclick="truckInformationUpdateForm('${loader}', '${tri_id}', '${shanci_no}', '${truck_plate_no}', '${side_no}', '${truck_model}', '${loading_capacity}', '${truck_owner}', '${truck_type}', '${truck_status}')" class="onclick" > 
										${trucksList.shanci_no}
									</div>								 
								</td>								
								<td> ${trucksList.truck_plate_no} </td>
								<td> ${trucksList.truck_model} </td>								
								<td> ${trucksList.loading_capacity} </td>
								<td> ${trucksList.truck_status} </td>
								<td> ${trucksList.truck_owner} </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
			</td>
			<td width="5%">&nbsp;</td>
			<td valign="top">
			
				<div id="truckForm">
					<jsp:include page="trucksSaveForm.jsp" />
				</div>
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#truckListTbl').DataTable();
		});
	</script>

</body>
</html>

