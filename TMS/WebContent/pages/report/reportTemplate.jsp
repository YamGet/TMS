<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>
	
	<script type="text/javascript">
		$(function(){
			$("#menu").menu();
		})
	</script>
	<style type="text/css">
	
		#divMenu, ul, li, li, li{
			margin: 0;
			padding: 0;
		}
		
		#divMenu{
			width: 150px;
			height: 27px;
		}
		
		#divMenu ul{
			line-height: 35px;
		}
		
		#divMenu li{
			list-style: none;
			position: relative;
			background: #f8f8f8;			
			
 			border-bottom-color: #fff;
 			border-bottom-style: solid; 
 			border-bottom-width: thin; 
			
			vertical-align: middle;
			z-index: 10000;				
		}
		
		#divMenu li li{
			list-style: none;
			position: relative;
			background: #f3f3f3;
			left: 150px;
			top: -27px;
		}
		
		#divMenu ul li a{
			list-style: none;
			height: 35px;
 			min-width: 280px;			 
			display: block;
			text-decoration: none;
			color: #000;
			padding-left: 5px;
		}
		
		#divMenu ul ul{
			position: absolute;
			visibility: hidden;
			top: 27px;
		}
		
		#divMenu ul li:hover ul{
			visibility: visible;
		}
		
		#divMenu ul li:hover ul li a:hover{
			background-color: #f8f8f8;
			color: #3d6e9f;
		}
		
		#divMenu li:hover{
			background-color: #e5e5e5;
			cursor: pointer;
		}
		
		
	</style>

</head>
<body>
	
	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<table width="100%">
		<tr>
			<td valign="top" width="150px;">
				<div id="divMenu">
					<ul>
						<li > Freight Order
							<ul>
								<li ><a href="#" onclick="getNotClosedFrightOrder('${loader}')">Not Closed Fright Order</a></li>
								<li ><a href="#" onclick="getDailyActivitiesPerTruck('${loader}')">Daily Activity</a></li>
								<li ><a href="#" onclick="getTransactionListPerTruckTemplate('${loader}')">Transaction List Per Truck (Truck History)</a></li>
								<li ><a href="#" onclick="getLoadingUnloadingDifferenceTemplate('${loader}')">Loading-Unloading Difference</a></li>
							</ul>
						</li>
						<li > Payment
							<ul>
								<li ><a href="#" onclick="getUncollectedPaymentAfterPaymentRequest('${loader}')">Payment not collected after request</a></li>
								<li ><a href="#" onclick="getUncollectedPaymentBeforePaymentRequest('${loader}')">Payment not collected before request</a></li>
								<li ><a href="#" onclick="getCollectedPaymentFilterForm('${loader}')">Collected Payment</a></li>
							</ul>
						</li>
						<li > Coupon
							<ul>
								<li ><a href="#" onclick="getCouponUsage('${loader}')">Coupon Usage</a></li>
							</ul>
						</li>
						<li > Expense
							<ul>
								<li ><a href="#" onclick="getRevenueExpensePerTruck('${loader}')">Revenue and Expense Per Truck</a></li>
								<li ><a href="#" onclick="getRevenueExpensePerFrightOrder('${loader}')">Revenue and Expense Per Fright Order</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</td>
			<td width="40px;">&nbsp;</td>
			<td id="reportMenuRslt" valign="top">
				
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>