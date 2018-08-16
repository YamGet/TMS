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
			
				<table id="trailListTbl" class="table table-striped table-bordered" cellpadding="0" cellspacing="1" width="100%">  
					<thead> 
						<tr> 
							<th> Trails Plate No. </th>
							<th> Loading Capacity </th>
							<th> Trail Status </th>
							<th> Trail Owner </th>
						</tr> 
					</thead>
					<tbody>
						<c:forEach var="trailsList" items="${trailsList}">						 
							<tr>
								<td> 
									<c:set var="tli_id" value="${trailsList.tli_id}" />
									<c:set var="trail_plate_no" value="${trailsList.trail_plate_no}"/>
									<c:set var="loading_capacity" value="${trailsList.loading_capacity}"/>
									<c:set var="trail_owner" value="${trailsList.trail_owner}"/>
									<c:set var="trail_type" value="${trailsList.trail_type}"/>
									<c:set var="trail_status" value="${trailsList.trail_status}" />
									
									<div class="onclick" onclick="trailsUpdateForm('${loader}','${tli_id}', '${trail_plate_no}', '${loading_capacity}', '${trail_owner}', '${trail_type}', '${trail_status}')">
										${trailsList.trail_plate_no} 
									</div>
								</td>
								<td> ${trailsList.loading_capacity} </td>
								<td> ${trailsList.trail_status} </td>
								<td> ${trailsList.trail_owner} </td>
							</tr>
						</c:forEach>			
					</tbody>
				</table>
			
			</td>
			<td width="5%">&nbsp;</td>
			<td valign="top">
			
				<div id="trailsForm">
					<jsp:include page="trailsSaveForm.jsp"/>
				</div>
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#trailListTbl').DataTable();
		});
	</script>

</body>
</html>

