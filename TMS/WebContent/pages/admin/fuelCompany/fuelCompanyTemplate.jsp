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
			<td valign="top" width="65%" align="left">
			
				<table id="companylist" class="table table-striped table-bordered">  
					<thead> 
						<tr> 
							<th> Company Name </th>
							<th> Contact Person Name </th>
							<th> Contact Person Phone </th>
							<th> Company Status </th>
						</tr> 
					</thead>
					<tbody>
						<c:forEach var="getFuelCompanyList" items="${getFuelCompanyList}">
							<tr>
								<td> 
								
									<c:set var="fc_id" value="${getFuelCompanyList.fc_id}"/>
									<c:set var="company_name" value="${getFuelCompanyList.company_name}"/>
									<c:set var="contact_person_name" value="${getFuelCompanyList.contact_person_name}"/>
									<c:set var="contact_person_phone" value="${getFuelCompanyList.contact_person_phone}"/>
									<c:set var="fc_status" value="${getFuelCompanyList.fc_status}"/>
								
									<div class="onclick" onclick="getFuelCompanyUpdateForm('${loader}','${fc_id}', '${company_name}', '${contact_person_name}', '${contact_person_phone}', '${fc_status}')">
										${company_name} 
									</div>
								</td>								
								<td> ${contact_person_name} </td>
								<td> ${contact_person_phone} </td>
								<td> ${fc_status} </td> 
							</tr>
						</c:forEach>										
					</tbody>					
				</table>
			
			</td>
			<td width="5%">&nbsp;</td>
			<td valign="top">
			
				<div id="fuelCompanyForm">
					<jsp:include page="fuelCompanySaveForm.jsp"/>
				</div>
			
			</td>
		</tr>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#companylist').DataTable();
		});
	</script>
	
</body>
</html>