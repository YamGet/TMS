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
							<th> Users Role Name </th>
							<th> Users Role Status </th>
							<th> &nbsp; </th>
							<th> &nbsp; </th>
						</tr> 
					</thead>
					<c:forEach var="usersRoleList" items="${usersRoleList}">
						<tbody> 
							<tr>
								<td> 
									<c:set var="ur_id" value="${usersRoleList.ur_id}" />
									<c:set var="userrole_name" value="${usersRoleList.userrole_name}"/>
									<c:set var="userrole_status" value="${usersRoleList.userrole_status}"/>
									
									<div class="onclick" onclick="usersRoleUpdateForm('${loader}','${ur_id}', '${userrole_name}', '${userrole_status}')">
										${usersRoleList.userrole_name} 
									</div>
								</td>
								<td> ${usersRoleList.userrole_status} </td>
								<td> 
									<div class="onclick" onclick="relateUsersRoleWithModule('${loader}','${ur_id}', '${userrole_name}')">
										New Module 
									</div>
								</td>
								<td> 
									<div class="onclick" onclick="unrelateUsersRoleWithModule('${loader}','${ur_id}', '${userrole_name}')">
										Related Module 
									</div>
								</td>
							</tr>			
						</tbody>
					</c:forEach>
				</table>
			
			</td>
			<td width="5%">&nbsp;</td>
			<td valign="top">
			
				<div id="usersRoleForm">
					<jsp:include page="usersRoleSaveForm.jsp"/>
				</div>
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>