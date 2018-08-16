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
	
	<ul class="nav nav-tabs">
		<li id="UnProcessFO" class="" onclick="getClosedFrightOrderList('${loader}')"> <a href="#"> Closed Freight Order </a> </li>		
		<li id="doclink" onclick="getDocumentDownloadLinkList('${loader}')"> <a href="#"> Document Download Link </a> </li>
	</ul>
	<br/>
	<div id="securedDataContent">
	
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>