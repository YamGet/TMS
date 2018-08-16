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
	
<%-- 	<c:out value="${frightOrder.fo_id}"></c:out> --%>
	
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="30%" valign="top" id="frightOrderInfoUpdate">
			
				<div class="panel panel-default" style="width: 100%">
					<div class="panel-heading">
						
						<table width="100%"><tr><td>
							<h4 class="panel-title">Freight Order Information</h4>  
						</td><td align="right"> 
							<c:set var="fo_id" value="${model.frightOrder[0].fo_id}"/>
							<span class="onclick" onclick="getFrightOrderInformationUpdateForm('${loader}', '${fo_id}')">Edit</span> 
						</td></tr></table>
						
					</div>
<!-- 					<div class="panel-body"> -->						
<!-- 						Information -->							
<!-- 					</div> -->
					<table class="table">
						<tr> <td> Association - </td><td> ${model.frightOrder[0].associations.association_name} </td></tr>
						<tr> <td> FON - </td><td> ${model.frightOrder[0].fon} </td></tr>
						<tr> <td> Truck Plate No - </td><td> ${model.frightOrder[0].truckInformation.truck_plate_no} </td></tr>
						<tr> <td> Trail Plate No - </td><td> ${model.frightOrder[0].trailInformation.trail_plate_no} </td></tr>
						<tr> <td> Driver - </td><td> ${model.frightOrder[0].drivers.fname} ${frightOrder[0].drivers.mname} </td></tr>
						<tr> <td> Date From - </td><td> ${model.frightOrder[0].date_from} </td></tr>
						<tr> <td> Date to - </td><td> ${model.frightOrder[0].date_to} </td></tr>						
					</table>
				</div>
			</td>
			<td>&nbsp;</td>
			<td width="30%" valign="top" id="frightOrderTripDetailInfoUpdate">
				
				<div class="panel panel-default" style="width: 100%">
					<div class="panel-heading">						
						<table width="100%"><tr><td>
							<h4 class="panel-title">Freight Order Trip Detail Information </h4> 
						</td><td align="right"> 
							<c:set var="fotd_id" value="${model.foTripDetail[0].fotd_id}"/>
							<span class="onclick" onclick="getFrightOrderTripDetailInfoUpdateForm('${loader}', '${fotd_id}')">Edit</span> 
						</td></tr></table>
					</div>
					<table class="table">
						<tr> <td> Client - </td><td> ${model.foTripDetail[0].client_organization} </td></tr>
						<tr> <td> Loading Type - </td><td> ${model.foTripDetail[0].loading_type} </td></tr>
						<tr> <td> Origin - </td><td> ${model.foTripDetail[0].origin_place} </td></tr>
						<tr> <td> Destination - </td><td> ${model.foTripDetail[0].destination_place} </td></tr>
						<tr> <td> Initial KM Reading - </td><td> ${model.foTripDetail[0].initial_km} </td></tr>
						<tr> <td> Quantity - </td><td> ${model.foTripDetail[0].loading_quantity} </td></tr>
						<tr> <td> Distance - </td><td> ${model.foTripDetail[0].distance} </td></tr>
						<tr> <td> Price - </td><td> ${model.foTripDetail[0].price} </td></tr>						
					</table>
				</div>
			
			</td>
			<td>&nbsp;</td>
			<td width="39%" valign="top">
			
				<div id="couponDisseminationUpdate">
					<div class="panel panel-default" style="width: 100%">
						<div class="panel-heading">						
							<table width="100%"><tr><td>
								<h4 class="panel-title"> Freight Order Coupon Dissemination Information </h4> 
							</td><td align="right"> 
								<span class="onclick" onclick="getCouponDissiminationPrintForm('${loader}', '${fo_id}')">Print</span> 
							</td></tr></table>
						</div>
						<div id="couponinfocontent">
							<div class="panel-body">						
								Total Coupon Amount - ${model.frightOrder[0].total_coupon_amount}
							</div>
							<table class="table">
								<tr><td> Coupon Type </td><td> Serial No</td> </tr>
								<c:forEach var="assignedCoupon" items="${model.getAssignedCoupon}">
									<tr> <td> ${assignedCoupon.amount} Birr </td><td> ${assignedCoupon.c_serial_no} </td></tr>
								</c:forEach>
								<tr><td colspan="2"> <div class="infomationMessage"> ${infoMessage} </div> </td> </tr>
							</table>
							<div class="panel-body">						
								<button type="button" onclick="getAddCouponForm('${loader}', '${fo_id}', '${model.frightOrder[0].total_coupon_amount}')" class="btn btn-default"> Add Coupon </button>		
							</div>
						</div>
					</div>
				</div>
				
				<div id="advancePaymentInfoUpdate">
					<div class="panel panel-default" style="width: 100%">
						<div class="panel-heading">						
							<table width="100%"><tr><td>
								<h4 class="panel-title"> Freight Order Advance Payment Information </h4> 
							</td>
<!-- 							<td align="right">  -->
<%-- 								<span class="onclick" onclick="getFrightOrderAdvacnePaymentInfoUpdateForm('${loader}', '${fo_id}')">Edit</span>  --%>
<!-- 							</td> -->
							</tr></table>
						</div>
						<div class="panel-body">
						
							<div>
								<button type="button" onclick="getAdditionalAdvancePaymentForm('${loader}', '${fo_id}')" class="btn btn-default"> Add Additional Payment </button>
							</div>
							<br/>
							<table>
								<c:forEach var="adv_payment" items="${model.getAdvancePayment}">
									<tr><td>Advance Payment - ${adv_payment.advance_payment_amount} Birr</td> <td> <span class="onclick" onclick="getFOAdvacnePaymentInfoUpdateForm('${loader}', '${fo_id}', '${adv_payment.ap_id}')">( Edit )</span> </td></tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>