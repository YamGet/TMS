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
	
	<div class="panel panel-default" style="width: 100%">
		<div class="panel-heading">
			<h4 class="panel-title">
				<table width="100%">
					<tr><td>Coupon Dissemination (Optional)</td>
					<td align="right" > <span class="onclick" onclick="assignCoupon('${loader}', '${fo_id}')" style="font-size: 14px;">Assign</span> </td></tr>
				</table>
			</h4>
		</div>
		<div class="panel-body">
					
<!-- 						<div class="form-group"> -->
<!-- 							<label for="coupon_type">Coupon Type</label> -->
<!-- 							<input id="coupon_type" type="text" class="form-control">				 -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="coupon_quantity">Coupon Quantity</label> -->
<!-- 							<input id="coupon_quantity" type="text" class="form-control">				 -->
<!-- 						</div> -->
<!-- 						<div> -->
<%-- 							<button type="submit" class="btn btn-default" onclick="addRequiredCoupon('${loader}')">Add</button> --%>
<!-- 						</div> -->
<!-- 						<hr/> -->
			<div class="form-group" style="min-height: 100px;">
				<label for="fon">Selected Coupon Type Serial Number List</label>
				
				<div id="foCouponListForm">
					<jsp:include page="../frightOrderSelectedCouponTypeList.jsp"/>
				</div>
								
			</div>
			<hr/>
			<div class="form-group">
				<label for="date_from">Coupon Status</label>
				<div class="checkbox">
					<label>	<input type="checkbox" name="optionsRadiosinline" id="op1" value="Op 1" checked="checked" disabled> Given to drivers</label>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" name="optionsRadiosinline" id="op1" value="Op 1" disabled> At Office</label>
				</div>
			</div>				
<%-- 			<button type="submit" class="btn btn-default" onclick="insertFrightOrderDetailInformation('${loader}')">Update</button> --%>
					
			</div>
		</div>

</body>
</html>