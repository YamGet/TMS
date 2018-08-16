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
			
				<table id="fuelCardListTbl" class="table table-striped table-bordered">  
					<thead> 
						<tr> 
							<th> Fuel Card Number </th>
							<th> Oil Company </th>
							<th> Fuel Card Status </th>
						</tr> 
					</thead>
					<c:forEach var="fuelCardList" items="${fuelCardList}">
						<tbody> 
							<tr>
								<td> 
								
									<c:set var="fc_id" value="${fuelCardList.fc_id}"/>
									<c:set var="fc_no" value="${fuelCardList.fc_no}"/>
									<c:set var="fc_company" value="${fuelCardList.fc_company}"/>
									<c:set var="fc_status" value="${fuelCardList.fc_status}"/>
								
									<div class="onclick" onclick="getFuelCardUpdateForm('${loader}','${fc_id}', '${fc_no}', '${fc_company}', '${fc_status}')">
										${fuelCardList.fc_no} 
									</div>
								</td>								
								<td> ${fuelCardList.fc_company} </td>
								<td> ${fuelCardList.fc_status} </td> 
							</tr>										
						</tbody>
					</c:forEach>
				</table>
			
			</td>
			<td width="10%">&nbsp;</td>
			<td valign="top">
			
				<div id="fuelCardForm">
					<jsp:include page="fuelCardSaveForm.jsp"/>
				</div>
			
			</td>
		</tr>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#fuelCardListTbl').DataTable();
		});
	</script>
</body>
</html>