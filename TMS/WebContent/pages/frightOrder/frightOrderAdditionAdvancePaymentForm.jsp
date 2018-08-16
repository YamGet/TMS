<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TMS</title>

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
                $("#send_date").datepicker({
                    //minDate: 0, 
                    //maxDate: "+30D",
                    numberOfMonths: 1,
                    onSelect: function(selected) {
                        $( "#send_date" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
                        $("#date_to").datepicker("option","minDate", selected)
                    }
                });
                $("#date_to").datepicker({ 
                    minDate: 0,
                    //maxDate: "+60D",
                    onSelect: function(selected) {
                        $( "#date_to" ).datepicker( "option", "dateFormat", "dd-mm-yy" );
                        $("#post_from").datepicker("option","maxDate", selected)
                    }
                });  
            });
	</script>

</head>
<body>
<div id="AddFormContent">
	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="panel panel-default" style="width: 35%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Additional Advance Payment Form 
				<c:set var="fo_id" value="${fright.fo_id}"/>
			</h4>
		</div>
		<div class="panel-body">
				<div class="errorMessage"><c:out value="${errorMessage}"/></div>
<%-- 			<form method="post" action="" role="form">				 --%>
				<div class="form-group">
					<label for="advance_payment_amount">Birr Amount</label>
					<input id="advance_payment_amount" type="text" class="form-control">				
				</div>
				<div class="form-group">
					<label for="send_reference_number">Reference Number</label>
					<input id="send_reference_number" type="text" class="form-control">				
				</div>
				<div class="form-group">
					<label for="send_date">Date</label>
					<input id="send_date" type="text" class="form-control" placeholder="dd-MM-yyyy">
				</div>						
				
				<button type="submit" class="btn btn-default" onclick="saveAdditionalAdvancePayment('${loader}', '${fo_id}')">Save</button>
<%-- 			</form>	 --%>
		</div>
	</div>
</div>
</body>
</html>