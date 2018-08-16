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

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="120px;" valign="top">
				
				<div class="btn-group-vertical" style="width: 100%">
					<button type="button" class="btn btn-default" onclick="getAssociationTemplate('${loader}')"> Association </button>
					<button type="button" class="btn btn-default" onclick="getDriversTemplate('${loader}')"> Drivers </button>
					<button type="button" class="btn btn-default" onclick="getTruckTemplate('${loader}')"> Truck </button>
					<button type="button" class="btn btn-default" onclick="getTrailsTemplate('${loader}')"> Trails </button>
					<button type="button" class="btn btn-default" onclick="getExpenseTypeTemplate('${loader}')"> Expense Type </button>
					<button type="button" class="btn btn-default" onclick="getFuelCompnayTemplate('${loader}')"> Fuel Company </button>
					<button type="button" class="btn btn-default" onclick="getFuelCardTemplate('${loader}')"> Fuel Card </button>
					<button type="button" class="btn btn-default" onclick="getUsersTemplate('${loader}')"> Users </button>
					<button type="button" class="btn btn-default" onclick="getUsersRoleTemplate('${loader}')"> Users Role </button>
					<button type="button" class="btn btn-default" onclick="getRolesAuthenticationTemplate('${loader}')"> Roles Authentication </button>
					<button type="button" class="btn btn-default" onclick="takeDatabaseBackup('${loader}')"> Backup Database </button>
					<button type="button" class="btn btn-default" onclick="getSecuredDataFromDBTemplate('${loader}')"> Secured Data </button>
				</div>
				
			</td>
			<td width="50px;">&nbsp;</td>
			<td id="adminMenuRslt" valign="top" width="800px;">
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>