<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
	
	<c:url value="/resources/calendar/css/demos.css" var="CalCssURL"/>
	<c:url value="/resources/calendar/css/themes/ui-lightness/jquery.ui.all.css" var="JqCssURL"/>
	<link href="${CalCssURL}"  rel="stylesheet" type="text/css">
	<link href="${JqCssURL}"  rel="stylesheet" type="text/css">
	
	<c:url value="/resources/js/jquery-1.12.3.js" var="JqJS1123"/>
	<script type="text/javascript" src="${JqJS1123}"></script>
	<c:url value="/resources/js/jquery.dataTables.min.js" var="JqJSDataTable"/>
	<script type="text/javascript" src="${JqJSDataTable}"></script>
	<c:url value="/resources/js/dataTables.bootstrap.min.js" var="JqJSDataTableBS"/>
	<script type="text/javascript" src="${JqJSDataTableBS}"></script>
	
	<c:url value="/resources/js/jquery-1.6.1.js" var="Jq161JS"/>
	<script type="text/javascript" src="${Jq161JS}"></script>
	<c:url value="/resources/calendar/js/jquery-1.5.1.js" var="Jq151JS"/>
	<script type="text/javascript" src="${Jq151JS}"></script>
	<c:url value="/resources/calendar/js/ui/jquery.ui.core.js" var="JqUiCoreJS"/>
	<script type="text/javascript" src="${JqUiCoreJS}"></script>
	<c:url value="/resources/calendar/js/ui/jquery.ui.datepicker.js" var="JqUiDPJS"/>
	<script type="text/javascript" src="${JqUiDPJS}"></script>	
	

	
	<script>
		
            $(document).ready(function(){
            	
                $("#payment_date").datepicker({
                    //minDate: 0, 
                    //maxDate: "+30D",
                    numberOfMonths: 1,
                    onSelect: function(selected) {
                        $( "#payment_date" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
                        $("#date_to").datepicker("option","minDate", selected)
                    }
                });
                $("#date_to").datepicker({ 
                    //minDate: 0,
                    //maxDate: "+60D",
                    onSelect: function(selected) {
                        $( "#date_to" ).datepicker( "option", "dateFormat", "dd-mm-yy" );
                        $("#post_from").datepicker("option","maxDate", selected)
                    }
                });
            });
            
//             $(document).ready(function() {
//         	    $('#inprfo').DataTable();
//         	});
	</script>
	
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="alert alert-info alert-dismissable" style="width: 60%;">
		Notice: Remember to add the CRSI Number for FO's with price zero before processing the payment .
	</div>

	<div class="panel panel-default" style="width: 60%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Payment Form
			</h4>
		</div>
		<div class="panel-body">

<!-- 			<div class="form-group"> -->
<!-- 				<label for="fon">In Process Freight Orders</label> -->
<!-- 				<div id="foErrorMessage" class="errorMessage"></div> -->
<%-- 				<c:forEach var="inpFrightOrder" items="${inprocessedFrightOrder}"> --%>
<%-- 					<c:set var="total_price" value="${inpFrightOrder.frightOrderTripDetail.delivered_quantity * inpFrightOrder.frightOrderTripDetail.price}"/> --%>
<%-- 					<c:set var="net_diff" value="${total_price - (total_price * inpFrightOrder.commission) - inpFrightOrder.total_coupon_amount}"/> --%>
<%-- 					<c:if test="${net_diff > 0.0}"> --%>
					
<!-- 						<div class="checkbox"> -->
<!-- 							<label>  -->
<%-- 								<input id="fon" type="checkbox" value="${inpFrightOrder.fo_id}" onchange="selectFrightOrder(this.value, '${net_diff}')"> FON - ${inpFrightOrder.fon} (Net Payment - <fmt:formatNumber maxFractionDigits="2" value="${net_diff}"/> ) --%>
<!-- 							</label> -->
<!-- 						</div> -->
					
<%-- 					</c:if> --%>
<%-- 				</c:forEach>					 --%>
<!-- 				<div id="fonTotal" style="font-weight: bold; background-color: #e5e5e5; width: 100%; height: 30px; padding-top: 5px;"></div>				 -->
<!-- 			</div> -->
		
				<table id="inprfo" class="table table-striped table-bordered" width="100%" cellspacing="0">  
					<thead> 
						<tr style="background-color: #e5e5e5; text-align: center;">
							<th> FON </th>
							<th> Net Payment </th>
							<th> Coupon Amount ( <input id="couponDeductionStatus" type="checkbox" value="deduct" onchange="couponDeductionStatus(this.value)"> Deducted ) </th>					
						</tr> 
					</thead>			
					<tbody>
						
						<c:forEach var="inpFrightOrder" items="${inprocessedFrightOrder}">
							
							<c:choose>
								<c:when test="${inpFrightOrder.truckInformation.truck_type == \"FLUID\"}">
									<c:set var="total_price" value="${inpFrightOrder.frightOrderTripDetail.loading_quantity * inpFrightOrder.frightOrderTripDetail.price}"/>
								</c:when>
								<c:otherwise>
									<c:set var="total_price" value="${inpFrightOrder.frightOrderTripDetail.delivered_quantity * inpFrightOrder.frightOrderTripDetail.price}"/>
								</c:otherwise>
							</c:choose>
							
							<c:set var="net_diff" value="${total_price - (total_price * inpFrightOrder.commission)}"/>
							
							<c:if test="${net_diff > 0.0}">
								<tr>
									<td> <input id="fon" type="checkbox" value="${inpFrightOrder.fo_id}" onchange="selectFrightOrder(this.value, '${net_diff}', '${inpFrightOrder.total_coupon_amount}')"> ${inpFrightOrder.fon} </td>
									<td> <fmt:formatNumber maxFractionDigits="2" value="${net_diff}"/> </td>
									<td> ${inpFrightOrder.total_coupon_amount}  </td>					
								</tr>
							</c:if>			
						</c:forEach>
					</tbody>
				</table>
				
				<div id="fonTotal" style="font-weight: bold; background-color: #e5e5e5; width: 100%; height: 30px; padding-top: 5px;"></div>
			<br/>

			<div style="width: 45%">
				<div class="form-group">
					<label for="date_from">Payment Form</label>
					<div id="pfErrorMessage" class="errorMessage"></div>
					<label class="checkbox-inline">
						<input type="radio" name="optionsRadiosinline" id="op1" value="CPV" onclick="selectPaymentForm(this.value)" > CPV
						<input type="radio" name="optionsRadiosinline" id="op1" value="BankTransfer" onclick="selectPaymentForm(this.value)" > Bank Transfer
						<input type="radio" name="optionsRadiosinline" id="op1" value="Cash" onclick="selectPaymentForm(this.value)" > Cash
					</label>
				</div>
				<div class="form-group">
					<label for="paymentDocRefNo">Payment Document Ref. Number</label>
					<input id="paymentDocRefNo" type="text" class="form-control">				
				</div>								
				<div class="form-group">
					<label for="paymentAmount">Payment Amount</label>
					<input id="paymentAmount" type="text" class="form-control">				
				</div>
				<div class="form-group">
					<label for="payment_date">Payment Date</label>
					<input id="payment_date" type="text" class="form-control" placeholder="dd-MM-yyyy" readonly="readonly">
				</div>
				<button type="submit" class="btn btn-default" onclick="saveFrightOrderPayment('${loader}')">Save</button>
			</div>
			
		</div>
	</div>

</body>
</html>