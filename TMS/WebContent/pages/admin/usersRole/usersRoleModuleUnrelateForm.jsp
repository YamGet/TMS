<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="panel panel-default" style="width: 100%;">
		<div class="panel-heading">
			<h4 class="panel-title">
				Related Module List
			</h4>
		</div>
		<div class="panel-body">
				
			<div class="errorMessage">${errorMessage} </div>
			<div class="successMessage">${successMessage}</div>
			<br/>
			<div>Users Role Name - ${usersRoleModuleRelation.usersRole.userrole_name}</div>
			<hr width="100%"/>
			<c:forEach var="relatedSystemModuleList" items="${relatedSystemModuleList}">
				<div class="form-group">
					<table><tr><td>
						<input type="checkbox" onclick="removeSystemModuleFromRelation('${relatedSystemModuleList[0]}')"/>
					</td><td width="5px"> </td><td>
						<label for="userrole_name"> ${relatedSystemModuleList[1]} </label>
					</td></tr></table>															
				</div>
			</c:forEach>											
			
			<hr width="100%"/>
			
			<button type="submit" class="btn btn-default" onclick="updateUsersRoleRelationWithModule('${loader}', '${usersRoleModuleRelation.ur_id}', '${usersRoleModuleRelation.usersRole.userrole_name}')">Un-relate</button>
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>