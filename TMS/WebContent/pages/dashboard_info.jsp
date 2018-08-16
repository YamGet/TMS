<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KK</title>

</head>
<body>

<div id="db_content" style="width: 100%; min-height: 400px">
	
</div>

<c:url value="/resources/js/jquery-1.6.1.js" var="JqJS"/>
<script type="text/javascript" src="${JqJS}"></script>
<c:url value="/resources/bootstrap/js/bootstrap.min.js" var="BsJS"/>
<script type="text/javascript" src="${BsJS}"></script>
<c:url value="/resources/js/sys_js.js" var="SysJS"/>
<script type="text/javascript" src="${SysJS}"></script>
<c:url value="/resources/js/highcharts_js/highcharts.js" var="HighChartJS"/>
<script type="text/javascript" src="${HighChartJS}"></script>
<c:url value="/resources/js/sys_dashboard_info.js" var="DbChartJS"/>
<script type="text/javascript" src="${DbChartJS}"></script>

<script type="text/javascript">
	$(document).ready(function(){		
		getCharts();		
	});	
	$(function () {
		alert("test")
		$("tspan:contains('Highcharts.com')").hide();
	});
</script>

</body>
</html>