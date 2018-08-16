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
	
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="20%" valign="top">
			
				<div class="panel panel-default" style="width: 100%">
					<div class="panel-heading">
						<h4 class="panel-title">
							Search Result
						</h4>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="fon">Fright Order No.</label>
							<input id="fon" type="text" value="${map.frightOrderList[0].fon}" class="form-control" disabled>									
						</div>
						<div class="form-group">
							<label for="fon">Driver</label>
							<input id="fon" type="text" value="${map.frightOrderList[0].drivers.fname} ${map.frightOrderList[0].drivers.mname}" class="form-control" disabled>								
						</div>
						<div class="form-group">
							<label for="fon">Truck Plate No.</label>
							<input id="fon" type="text" value="${map.frightOrderList[0].truckInformation.truck_plate_no}" class="form-control" disabled>								
						</div>
						<div class="form-group">
							<label for="fon">Trial Plate No.</label>
							<input id="fon" type="text" value="${map.frightOrderList[0].trailInformation.trail_plate_no}" class="form-control" disabled>								
						</div>						
						<div class="form-group">
							<label for="fon">Total Advance Payment</label>
							<input id="totalAdvancePayment" type="text" value="${map.frightOrderList[0].advancePayment.advance_payment_amount}" class="form-control" disabled>									
						</div>
						<div class="form-group">
							<label for="fon">Total Coupon</label>
							<input id="totalCouponAmount" type="text" value="${map.frightOrderList[0].total_coupon_amount}" class="form-control" disabled>									
						</div>
						<div class="form-group">
							<label for="fon">Grand Total</label>
							<input id="grandTotal" type="text" value="${map.frightOrderList[0].advancePayment.advance_payment_amount + map.frightOrderList[0].total_coupon_amount}" class="form-control" disabled>									
						</div>
					
					</div>
				</div>
				
				
			</td>
			<td width="2%">&nbsp;</td>
			<td valign="top" width="20%" style="padding-left: 5px;">
			
			<div id="expenseAddForm" style="width: 100%">
			
				<div class="panel panel-default" style="width: 100%">
					<div class="panel-heading">
						<h4 class="panel-title">
							Expense Type List
						</h4>
					</div>
					<div class="panel-body">
			
						<table width="100%">
							<tr>											
								<td style="padding-left: 5px; padding-right: 5%;">							
											
									<div class="form-group">
									
										<c:forEach var="expType" items="${map.expenseTypeList}">
											<div class="checkbox">
												<label> <input id="${expType.et_id}" type="checkbox" value="${expType.expense_type_name}" onchange="selectExpenseType('${loader}', '${expType.et_id}', this.value)"> ${expType.expense_type_name} </label>
											</div>
										</c:forEach>

									</div>											
								</td>
							</tr>
							<tr>
								<td style="border-top-color: #e5e5e5; border-top-style: solid; border-top-width: thin; padding-top: 5px;">
									<table><tr><td>
										<button type="submit" class="btn btn-default" onclick="getSelectedExpenseType('${loader}', '${map.frightOrderList[0].fo_id}')">Select</button>
									</td><td>&nbsp;</td><td>
										<button type="submit" class="btn btn-default" onclick="viewRegisteredExpenseList('${loader}', '${map.frightOrderList[0].fo_id}')">Expense Info.</button>
									</td></tr></table>
								</td>
							</tr>
						</table>
					
					</div>
				</div>
				
				</div>
			
			</td>
<!-- 			<td width="2%">&nbsp;</td> -->
<!-- 			<td valign="top" style="padding-left: 5px;" width="20%"> -->
			
<!-- 				<div id="expenseAddForm" style="width: 100%"></div> -->
			
<!-- 			</td> -->
			<td width="2%">&nbsp;</td>
			<td valign="top">
			
				<div id="checkBalance" style="width: 100%"> </div>
			
			</td>
		</tr>
	</table>
		
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>