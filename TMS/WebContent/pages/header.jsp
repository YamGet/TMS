<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TMS</title>

<c:url value="/resources/bootstrap/css/bootstrap.min.css" var="bcssURL"/>
<c:url value="/resources/css/sys_css.css" var="cssURL"/>
<c:url value="/resources/images/tms.ico" var="iconURL"/>
<link href="${bcssURL}" rel="stylesheet" type="text/css">
<link href="${cssURL}" rel="stylesheet" type="text/css">
<link href="${iconURL}" rel="icon" type="image/ico">

</head>
<body>

<div style="width: 100%; height: 70px; padding-bottom: 10px; padding-top: 2px; background-color: #54b649; color: #fff;" align="center">
	<table style="width: 90%" border="0">
		<tr>
			<td width="5%">
				<c:url value="/resources/images/4amen.png" var="TMLogo"/>
				<img alt="Logo" src="${TMLogo}" style="width: 65px;">
			</td>
			<td align="left" style="padding-left: 10px;">
				<table border="0"><tr><td>
					<span style="font-weight: bold; font-size: 22px;"> 
					FIDEL Transport Management System 
					</span>
				</td></tr><tr><td align="center">
					<span style="font-weight: normal; font-size: 14px;"> 
					TILAHUN MESAFENT FRIGHT TRANSPORT
					</span>
				</td></tr></table>
			</td>
			<td>&nbsp;</td>
			<td align="right">
				<c:url value="/resources/images/male_default.jpeg" var="pic"/>
				Login as - <%= session.getAttribute("loggedInUser") %> &nbsp;&nbsp;  <img alt="loginPic" src="${pic}" class="img-circle" width="30px;">
			</td>
			<td style="padding-top: 5px; width: 400px;">
				<div style="color: #fff;">
					<c:set var="logouturl" value="login.html"></c:set>
					<ul class="header-nav">
						<li> <a href="index.html"> Home </a> </li>
						<li> <a href="changeProfile.html"> Change Profile </a> </li>
						<li> <a href="helpPage.html" target="_blank"> Help </a> </li>
						<li> <a href="logout.html"> Logout </a> </li>
					</ul>
				</div>
			</td>
		</tr>
	</table>
</div>

</body>
</html>