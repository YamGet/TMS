<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		
	<c:url value="/resources/calendar/css/demos.css" var="CalCssURL"/>
	<c:url value="/resources/calendar/css/themes/ui-lightness/jquery.ui.all.css" var="JqCssURL"/>
	<link href="${CalCssURL}"  rel="stylesheet" type="text/css">
	<link href="${JqCssURL}"  rel="stylesheet" type="text/css">
	
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
                $("#date_from").datepicker({
                    minDate: 0, 
                    //maxDate: "+30D",
                    numberOfMonths: 1,
                    onSelect: function(selected) {
                        $( "#date_from" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
                        $("#date_to").datepicker("option","minDate", selected)
                    }
                });
                $("#appointment_date").datepicker({ 
                    minDate: 0,
                    //maxDate: "+60D",
                    onSelect: function(selected) {
                        $( "#appointment_date" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
                        $("#post_from").datepicker("option","maxDate", selected)
                    }
                });  
            });
	</script>
	
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="panel panel-default" style="width: 35%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Payment Appointment Date Add Form
			</h4>
		</div>
		<div class="panel-body">
		
			<div class="form-group">
				<label for="fon">Freight Order Number</label>
				<input id="fon" type="text" class="form-control" value="${getFrightOrderInfo[0].fon}" disabled>
			</div>
		
			<div class="form-group">
				<label for="date_from">Payment Request Date</label>
				<input id="date_from" type="text" class="form-control" value="${getFrightOrderInfo[0].frightOrderTripDetail.payment_request_date}" disabled>
			</div>
			
			<div class="form-group">
				<label for="appointment_date">Appointment Date</label>
				<input id="appointment_date" type="text" class="form-control" placeholder="yyyy-MM-dd">
			</div>
			
			<button type="submit" class="btn btn-default" onclick="addPaymentAppointmentDate('${loader}', '${getFrightOrderInfo[0].fo_id}')">Save</button>
			
		</div>
	</div>

</body>
</html>