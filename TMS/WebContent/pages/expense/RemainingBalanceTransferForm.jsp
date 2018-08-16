<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
		
	<div class="panel panel-default" style="width: 50%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Remaining Balance Transfer Form
			</h4>
		</div>
		<div class="panel-body">
			
			<div class="form-group">
				<label for="return_amount">Transfer Amount</label>
				<input id="return_amount" value="${advancePayment.return_amount}" type="text" class="form-control" readonly="readonly">				
			</div>
			<div class="form-group">
				<label for="coupon_transfer_amount">Transfer Coupon Amount</label>
				<input id="coupon_transfer_amount" value="${coupon_transfer_value}" type="text" class="form-control" readonly="readonly">				
			</div>			
			<div class="form-group">
				<label for="fon">Active Fright Order</label>
				<form:select id="fo_id" path="map" cssClass="form-control">
					<form:option value="-" ></form:option>
					<form:options itemLabel="fon" itemValue="fo_id" items="${map.frightOrderList}"/>
				</form:select>		
			</div>
			
			<button type="submit" class="btn btn-default" onclick="saveRemainingBalanceTransfer('${loader}', '${advancePayment.ap_id}')">Save</button>
						
		</div>
	</div>

	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>