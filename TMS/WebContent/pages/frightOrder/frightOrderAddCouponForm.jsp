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
	
	<c:set var="fo_id" value="${fright.fo_id}"/>
	
	<div class="alert alert-warning alert-dismissable" style="width: 30%;">
		Warning: This coupons are not given to the drivers. They are going to saved only to relate the coupons with fright orders.
	</div>

	<div class="panel panel-default" style="width: 30%">
		<div class="panel-heading">
			<h4 class="panel-title">
				<table width="100%">
					<tr><td>Coupon Dissemination (Optional)</td>
					<td align="right" > <span class="onclick" onclick="assignNotGivenCoupon('${loader}', '${fo_id}', '${fright.total_coupon_amount}')" style="font-size: 14px;">Assign</span> </td></tr>
				</table>
			</h4>
		</div>
		<div class="panel-body">
		
<!-- 			<div class="form-group"> -->
<!-- 				<label for="fon">Coupon Type</label> -->
<!-- 				<input id="fon" type="text" class="form-control">				 -->
<!-- 			</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<label for="fon">Coupon Quantity</label> -->
<!-- 				<input id="fon" type="text" class="form-control">				 -->
<!-- 			</div> -->
<!-- 			<div> -->
<%-- 				<button type="submit" class="btn btn-default" onclick="addSelectedCoupon('${loader}')">Add</button> --%>
<!-- 			</div> -->
<!-- 			<hr/> -->
			<div class="form-group" style="min-height: 20px;">
				<label for="fon"> Total Coupon Amount - ${fright.total_coupon_amount} </label>															
			</div>
			<div class="form-group" style="min-height: 100px;">
				<label for="fon">Selected Coupon Type Serial Number List</label>
				
				<div id="foCouponListForm">
					<jsp:include page="frightOrderSelectedCouponTypeList.jsp"/>
				</div>											
			</div>
			<hr/>
			<div class="form-group">
				<label for="couponStatus">Coupon Status</label>				
			</div>
			<div class="form-group" id="couponStatus"> 
				<label class="checkbox-inline"> <input type="radio" name="optionsRadiosinline" id="givenToDriver" value="-" onclick="selectedCouponPlacementStatus(this.value)"> Given to drivers </label>    
				<label class="checkbox-inline"> <input type="radio" name="optionsRadiosinline" id="atOffice" value="YES" onclick="selectedCouponPlacementStatus(this.value)"> At Office </label>
			</div>				
<%-- 			<button type="submit" class="btn btn-default" onclick="insertFrightOrderDetailInformation('${loader}')">Save</button> --%>
		
		</div>
	</div>

	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>