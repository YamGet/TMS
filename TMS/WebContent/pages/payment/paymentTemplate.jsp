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
		<li id="UnProcessFO" class="active" onclick="getUnprocessFrightOrder('${loader}')"> <a href="#"> Unprocessed Freight Order </a> </li>
		<li id="InProcessFO" onclick="getInprocessFrightOrder('${loader}')"> <a href="#"> In Process Freight Order </a> </li>
	</ul>
	<br/>
	<div id="paymentForm">
	
<!-- 		<div style="width: 100%;" align="left">  -->
<!-- 			<div class="form-group" style="width: 250px;">			 -->
<%-- 				<input id="searchFoKey" type="text" class="form-control" onkeyup="searchFrightOrderFromList('${loader}', this.value)" placeholder="Search">				 --%>
<!-- 			</div> -->
<!-- 		</div> -->
			
		<div id="paymentContent" style="padding-top: 0px;">
	
			<jsp:include page="notProcessedFrightOrder.jsp"/>
		
		</div>
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#unprocessfo').DataTable();
		} );
	</script>

</body>
</html>