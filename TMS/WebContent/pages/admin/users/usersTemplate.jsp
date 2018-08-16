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

	<table cellpadding="0" cellspacing="0" width="100%" border="0">
		<tr>
			<td valign="top" width="50%" align="left">
			
				<table class="table table-hover" cellpadding="0" cellspacing="1" width="80%">  
					<thead> 
						<tr style="background-color: #e5e5e5; text-align: center;"> 
							<th> System User </th> 
							<th> User Role </th>
							<th> User Status </th>
						</tr> 
					</thead>
					<c:forEach var="userList" items="${map.userList}">
						<tbody> 
							<tr>
								<td> <div onclick="getUserInfoUpdateForm('${loader}', '${userList.user_id}')" style="color: #3d6e9f; cursor: pointer;"> ${userList.fname} ${userList.mname} </div> </td>
								<td> ${userList.usersRole.userrole_name} </td> 
								<td> ${userList.user_status} </td>
							</tr>										
						</tbody>
					</c:forEach>
				</table>
			
			</td>
			<td width="10%">&nbsp;</td>
			<td valign="top" >
			
			<div id="usersForm">
				<div class="panel panel-default" style="width: 100%;">
					<div class="panel-heading">
						<h4 class="panel-title">
							Users Registration Form
						</h4>
					</div>
					<div class="panel-body">
						
<%-- 						<form method="post" action=""> --%>
							
							<div class="form-group">
								<label for="fname">First Name</label>
								<input id="fname" type="text" class="form-control">				
							</div>
							<div class="form-group">
								<label for="mname">Middle Name</label>
								<input id="mname" type="text" class="form-control">				
							</div>
							<div class="form-group">
								<label for="gname">Grandfather Name</label>
								<input id="gname" type="text" class="form-control">				
							</div>
							<div class="form-group">
								<label for="username">Username</label>
								<input id="username" type="text" class="form-control">				
							</div>
							<div class="form-group">
								<label for="password">Password</label>
								<input id="password" type="password" class="form-control" value="pass*123">				
							</div>
							<div class="form-group">
								<label for="fon">User Role</label>												
								<form:select id="ur_id" path="map" cssClass="form-control">
									<form:option value="-" ></form:option>
									<form:options itemLabel="userrole_name" itemValue="ur_id" items="${map.usersRoleList}"/>
								</form:select>
							</div>
							
							<button type="submit" class="btn btn-default" onclick="saveNewUser('${loader}')">Save</button>
<%-- 						</form>	 --%>
						
					</div>
				</div>
			</div>
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>