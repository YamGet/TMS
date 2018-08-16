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
				Trailers Information Add Form
			</h4>
		</div>
		<div class="panel-body">
			<div class="errorMessage">${errorMessage} </div>
<!-- 			<form method="post" action="">				 -->
				<div class="form-group">
					<label for="trail_plate_no">Trailers Plate Number</label>
					<form:input id="trail_plate_no" path="trails.trail_plate_no" cssClass="form-control"/>			
				</div>
				<div class="form-group">
					<label for="loading_capacity">Loading Capacity (Quintal)</label>
					<form:input id="loading_capacity" path="trails.loading_capacity" cssClass="form-control"/>				
				</div>
				<div class="form-group">
					<label for="trail_owner">Trailers Owner</label>
					<form:input id="trail_owner" path="trails.trail_owner" cssClass="form-control" value="Ato Tilahun Mesafent"/>				
				</div>
				<div class="form-group">
					<label for="trail_type"> Trailers Carrying Type <span class="required"> * </span> </label>
					<select id="trail_type" class="form-control">
						<option value="-">-</option>
						<option value="DRY">DRY</option>
						<option value="FLUID">FLUID</option>
					</select>				
				</div>
				
				<button type="submit" class="btn btn-default" onclick="saveTrailInformation('${loader}')"> Save </button>
<!-- 			</form>	 -->
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>