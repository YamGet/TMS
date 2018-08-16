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
	
	<div>
		<table><tr><td>
			<button type="button" onclick="frightOrderAddForm('${loader}')" class="btn btn-default"> Add New Freight Order </button>
		</td><td>&nbsp;</td><td>
			<button id="incompFObtn" type="button" onclick="getIncompleteFrightOrderList('${loader}')" class="btn btn-default"> Incomplete Freight Order List </button>
		</td></tr></table>
	</div>
	
	<br/>	
	
<!-- 	<div style="width: 100%;" align="left">  -->
<!-- 		<div class="form-group" style="width: 250px;">			 -->
<%-- 			<input id="fon" type="text" class="form-control" onkeyup="searchFromList('${loader}', this.value)" placeholder="Search">				 --%>
<!-- 		</div> -->
<!-- 	</div> -->
	
	<div id="frightOrderForm">
		<jsp:include page="frightOrderList.jsp"/>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>