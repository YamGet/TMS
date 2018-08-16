<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>TMS</title>

<c:url value="/resources/bootstrap/css/bootstrap.min.css" var="bcssURL"/>
<c:url value="/resources/css/sys_css.css" var="cssURL"/>
<link href="${bcssURL}"  rel="stylesheet" type="text/css">
<link href="${cssURL}"  rel="stylesheet" type="text/css">

<c:url value="/resources/images/4amen.ico" var="iconURL"/>
<link href="${iconURL}" rel="icon" type="image/ico">

</head>
<body style="margin: 0px;">

<jsp:include page="header.jsp"/>

<c:url value="/resources/images/loader.gif" var="loader"/>
<c:url value="/resources/images/right.jpeg" var="pic"/>

<div align="center">
	<div class="appbody" style="min-height: 520px; width: 90%" align="left">	
	
		<div class="sidebar-nav">
			<div class="navbar navbar-default" role="navigation">
			
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle Navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- <a class="navbar-brand" href="#"></a> -->
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="padding-top: 4px;">
					<ul class="nav nav-pills">
						<li id="frightOrder"><a href="#" onclick="FrightOrder('${loader}')"> <img alt="loginPic" class="img-circle" src="${pic}" width="20px;"> Freight Order </a></li>
						<li id="payment"><a href="#" onclick="Payment('${loader}')"> <img alt="loginPic" class="img-circle" src="${pic}" width="20px;"> Payment </a></li>
						<li id="expense"><a href="#" onclick="Expense('${loader}')"> <img alt="loginPic" class="img-circle" src="${pic}" width="20px;"> Expense </a></li>
						<li id="fuelCard"><a href="#" onclick="FuelCard('${loader}')"> <img alt="loginPic" class="img-circle" src="${pic}" width="20px;"> Fuel Card </a></li>
						<li id="couponRegistration"><a href="#" onclick="CouponRegistration('${loader}')"> <img alt="loginPic" class="img-circle" src="${pic}" width="20px;"> Coupon Registration </a></li>
						<li id="report"><a href="#" onclick="Report('${loader}')"> <img alt="loginPic" class="img-circle" src="${pic}" width="20px;"> Report </a></li>
						<li id="admin"><a href="#" onclick="Admin('${loader}')"> <img alt="loginPic" class="img-circle" src="${pic}" width="20px;"> Admin </a></li>
						<li>&nbsp;</li>
						<li>&nbsp;</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div id="bodycontent">
		
			<div>
				<jsp:include page="dashboard_info.jsp"/>
			</div>
		
		</div>
	
	</div>
</div>

<jsp:include page="footer.jsp"/>

<jsp:include page="/pages/footer_resource.jsp"/>

<script type="text/javascript">
// 	$(document).ready(function(){
		
// 		alert("test");
// 	});
// 	$('.dropdown-toggle').dropdown()
</script>
</body>
</html>