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
	
	<div class="panel panel-default" style="width: 100%;">
		<div class="panel-heading">
			<h4 class="panel-title">
				Coupon Registration Form
			</h4>
		</div>
		<div class="panel-body">
			<div class="errorMessage">${errorMessage}</div>

				<div class="form-group">
					<label for="oil_company">Oil Company Name</label>					
					<form:select path="couponReg.oil_company" id="oil_company" class="form-control">
						<form:option value=" - ">-</form:option>
						<form:options itemLabel="company_name" itemValue="company_name" items="${fuelCompanyList}"/>
					</form:select>			
				</div>
				<div class="form-group">
					<label for="amount">Coupon Type</label>
					<form:input id="amount" path="couponReg.amount" type="text" class="form-control"/>				
				</div>
				<div class="form-group">
					<label for="date_from">Insertion Type</label>
					<label class="checkbox-inline">
						<input type="radio" name="optionsRadiosinline" id="op1" value="1" onclick="insertOptionSelected(this.value)" > Single Coupon
						<input type="radio" name="optionsRadiosinline" id="op1" value="2" onclick="insertOptionSelected(this.value)" > Multiple Coupon
					</label>
				</div>
				
				<div id="coupon_1" class="form-group" style="display: none">
					<label for="coupon_serial_no">Coupon Serial Number</label>								
					<form:input path="couponReg.c_serial_no" id="c_serial_no" type="text" class="form-control"/>
				</div>
				
				<div id="coupon_4" class="form-group" style="display: none">
					<label for="no_of_coupon">Number of Coupon</label>								
					<form:input path="couponReg.no_of_coupon" id="no_of_coupon" type="text" class="form-control"/>
				</div>
				<div id="coupon_2" class="form-group" style="display: none">
					<label for="coupon_from">Coupon Serial From</label>								
					<form:input path="couponReg.c_serial_no_from" id="c_serial_no_from" type="text" class="form-control"/>
				</div>
				<div id="coupon_3" class="form-group" style="display: none">
					<label for="coupon_to">Coupon Serial To</label>
					<form:input path="couponReg.c_serial_no_to" id="c_serial_no_to" type="text" class="form-control"/>
				</div>						
				
				<button type="submit" class="btn btn-default" onclick="registorCoupon('${loader}')">Save</button>
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>