<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

</head>
<body style="margin: 0px;">

<div id="fullcontent">

<jsp:include page="header.jsp"/>

<c:url value="/resources/images/loader.gif" var="loader"/>

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
						<li id="frightOrder"><a href="#" onclick="FrightOrder('${loader}')"> Fright Order </a></li>
						<li id="payment"><a href="#" onclick="Payment('${loader}')"> Payment </a></li>
						<li id="expense"><a href="#" onclick="Expense('${loader}')"> Expense </a></li>
						<li id="fuelCard"><a href="#" onclick="FuelCard('${loader}')"> Fuel Card </a></li>
						<li id="couponRegistration"><a href="#" onclick="CouponRegistration('${loader}')"> Coupon Registration </a></li>
						<li id="report"><a href="#" onclick="Report('${loader}')"> Report </a></li>
						<li id="admin"><a href="#" onclick="Admin('${loader}')"> Admin </a></li>
						<li>&nbsp;</li>
						<li>&nbsp;</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div id="bodycontent">
		
			<div style="width: 100%;" align="center">
			
				

			
				<div class="panel panel-default" style="width: 340px;">
					<div class="panel-heading">
						<h4 class="panel-title">
							Change User Profile
						</h4>
					</div>
					<div class="panel-body">
					
						<div id="errorMessage" class="errorMessage"> ${errorMessage} </div>	
										
						<div class="form-group">
							<label for="username">Username</label>
							<form:input id="username" path="user.user_name" type="text" class="form-control"/>				
						</div>
						<div class="form-group">
							<label for="oldpassword">Old Password</label>
							<form:input id="oldpassword" path="user.password" type="password" class="form-control"/>											
						</div>
						<div class="form-group">
							<label for="password">New Password</label>
							<form:input id="password" path="user.password" type="password" class="form-control"/>
							<span id="password_strength"></span>			
						</div>
						<div class="form-group">
							<label for="repassword">Re-enter New Password</label>
							<form:input id="repassword" path="user.password" type="password" class="form-control"/>				
						</div>
																
						<button type="submit" class="btn btn-default" onclick="changeUsernamePassword('${loader}')">Update</button>
									
					</div>
				</div>

			</div>
		
		</div>
	
	</div>
</div>

<jsp:include page="footer.jsp"/>

<jsp:include page="/pages/footer_resource.jsp"/>

<script type="text/javascript">
$(function(){
	$("#password").bind("keyup", function(){
		
		if($(this).val().length == 0){
			$("#password_strength").html("");
			return;
		}
		
		var regex = new Array();		
		regex.push("[A-Z]");
		regex.push("[a-z]");
		regex.push("[0-9]");
		regex.push("[$@!%*#?&]");
		
		var passed = 0;
		
		for(var i = 0; i < regex.length; i++){
			if(new RegExp(regex[i]).test($(this).val())){
				passed++;
			}
		}
		
		if(passed > 2 && password.length > 8){
			passed++;
		}
		
		var color = "";
		var strength = "";
		
		switch(passed){
		
		case 0:
		case 1: strength = "Weak";
				color = "red";
				break;
		case 2: strength = "Good";
				color = "darkorange";
				break;
		case 3: 
		case 4: strength = "Strong";
				color = "green";
				break;
		case 5: strength = "Very Strong";
				color = "darkgreen";
				break;
		}
		
		$("#password_strength").html(strength);
		$("#password_strength").css("color", color);
	})
});
</script>

</div>

</body>
</html>