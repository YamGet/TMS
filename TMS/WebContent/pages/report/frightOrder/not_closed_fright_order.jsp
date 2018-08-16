<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<jsp:include page="/pages/header_resource.jsp"/>
	
</head>
<body>

	<c:url value="/resources/images/loader.gif" var="loader"/>
	
	<table><tr><td style="padding-top: 4px;">
	
		<select id="truck_type" onchange="filterNotClosedFrightOrder(this.value, '${loader}')" class="form-control" style="height: 40px;">
			<option value="BOTH">BOTH TRUCKS TYPE</option>
			<option value="DRY">TRUCKS-DRY</option>
			<option value="FLUID">TRUCKS-FLUID</option>
		</select>
		
	</td><td>&nbsp;</td><td style="padding-top: 4px;">
	
		<div class="btn btn-default" onclick="rptPDF_generateNotClosedFrightOrder('${loader}')">
			<table align="center"><tr><td>			
				<c:url value="/resources/images/pdf.gif" var="PDF"/>
				<img alt="PDF" src="${PDF}" height="25px">
			</td><td valign="middle">
				PDF
			</td></tr></table>
		</div>
	
	</td><td>&nbsp;</td><td style="padding-top: 4px;">
	
		<div class="btn btn-default" onclick="excel_generateNotClosedFrightOrder('${loader}')">
			<table align="center"><tr><td>			
				<c:url value="/resources/images/excel_icon.jpg" var="excel"/>
				<img alt="excel" src="${excel}" height="25px">
			</td><td valign="middle">
				Excel
			</td></tr></table>
		</div>
	
	</td></tr></table>
	
	
	<div id="filteredReportContent">
	
		<jsp:include page="not_closed_fright_order_content.jsp"/>
		
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>