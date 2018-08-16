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
			
				<table id="driversList" class="table table-striped table-bordered" cellpadding="0" cellspacing="1" width="100%">  
					<thead> 
						<tr style="text-align: center;"> 
							<th> Drivers Name </th> 
							<th> Driving License No. </th>
							<th> Local Phone </th>
							<th> Abroad Phone </th>
							<th> Drivers Status </th>
						</tr> 
					</thead>
					<tbody>
						<c:forEach var="driversList" items="${driversList}">						 
							<tr>
								<td> 
								
									<c:set var="dr_id" value="${driversList.dr_id}" />
									<c:set var="fname" value="${driversList.fname}" />
									<c:set var="mname" value="${driversList.mname}" />
									<c:set var="gname" value="${driversList.gname}" />
									<c:set var="driving_license_no" value="${driversList.driving_license_no}" />
									<c:set var="local_phone" value="${driversList.local_phone}" />
									<c:set var="abroad_phone" value="${driversList.abroad_phone}" />
									<c:set var="dr_status" value="${driversList.dr_status}" />
									
									<div onclick="driversInformationUpdateForm('${loader}', '${dr_id}', '${fname}', '${gname}', '${mname}', '${driving_license_no}', '${local_phone}', '${abroad_phone}', '${dr_status}')" class="onclick" title="${driversList.fname} ${driversList.mname} ${driversList.gname}"> 
										${driversList.fname}
									</div>
								</td>								
								<td> ${driversList.driving_license_no} </td>
								<td> ${driversList.local_phone} </td>
								<td> ${driversList.abroad_phone} </td>
								<td> ${driversList.dr_status} </td> 
							</tr>	
						</c:forEach>
					</tbody>
				</table>
			
			</td>
			<td width="5%">&nbsp;</td>
			<td valign="top">
			
			<div id="driversForm">
				
				<jsp:include page="driversSaveForm.jsp" />
				
			</div>
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#driversList').DataTable();
		});
	</script>

</body>
</html>