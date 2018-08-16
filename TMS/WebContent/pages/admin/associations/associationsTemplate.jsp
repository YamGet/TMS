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
			<td valign="top" width="50%" align="left">
			
				<table class="table table-hover" cellpadding="0" cellspacing="1" width="80%">  
					<thead> 
						<tr style="background-color: #e5e5e5; text-align: center;"> 
							<th> Association Name </th> 
							<th> Association Status </th>
						</tr> 
					</thead>
					<c:forEach var="associationList" items="${associationList}">
						<tbody> 
							<tr>
								<td>
								
									<c:set var="a_id" value="${associationList.a_id}" />
									<c:set var="association_name" value="${associationList.association_name}" />
									<c:set var="association_status" value="${associationList.association_status}" />
									
									<div onclick="getAssociationsUpdateForm('${loader}', '${a_id}', '${association_name}', '${association_status}')" class="onclick">
										${associationList.association_name}
									</div>
								</td>								
								<td> ${associationList.association_status} </td> 
							</tr>										
						</tbody>
					</c:forEach>
				</table>
			
			</td>
			<td width="10%">&nbsp;</td>
			<td valign="top">
			
				<div id="associationForms">
					
					<jsp:include page="associationsSaveForm.jsp"/>
					
				</div>
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>