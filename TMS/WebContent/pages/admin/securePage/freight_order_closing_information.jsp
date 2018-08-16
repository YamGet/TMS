<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                $("#deliveryDate").datepicker({
                    //minDate: 0, 
                    //maxDate: "+30D",
                    numberOfMonths: 1,
                    onSelect: function(selected) {
                        $( "#deliveryDate" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
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

	<c:set var="fo_id" value="${fright.fo_id}"/>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<div class="panel panel-default" style="width: 35%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Closing Form
			</h4>
		</div>
		<div class="panel-body">
			<div class="errorMessage"><c:out value="${errorMessage}"/></div>
			
				<c:set var="fo_id" value="${foDetail.fo_id}"/>

				<div class="form-group">
					<label for="dispatchDocRefNum">Dispatch Document Ref. Number</label>
					<form:input id="dispatchDocRefNum" path="foDetail.dispatch_doc_ref_no" cssClass="form-control"/>		
				</div>
				<div class="form-group">
					<label for="deliveryDocRefNum">Delivery Document Ref. Number</label>
					<form:input id="deliveryDocRefNum" path="foDetail.delivery_doc_ref_no" cssClass="form-control"/>		
				</div>
				<div class="form-group">
					<label for="deliveredQuantity">Delivered Quantity(Quintal)</label>
					<form:input id="deliveredQuantity" path="foDetail.delivered_quantity" cssClass="form-control"/>			
				</div>
				<div class="form-group">
					<label for="deliveryDate">Delivery Date</label>
					<form:input id="deliveryDate" path="foDetail.delivery_date" cssClass="form-control"/>
				</div>
				<div class="form-group">
					<label for="remark">Remark</label>
					<form:textarea id="remark" path="foDetail.fright_note" cssClass="form-control"/>
				</div>						
				
				<button type="submit" class="btn btn-default" onclick="updateFrightOrderCloseInfo('${fo_id}', '${loader}')">Update</button>

		</div>
	</div>
</div>
</body>
</html>