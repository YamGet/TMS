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
	<c:set var="rowNum" value="1"/>
	<c:set var="revenueGrandTotal" value="0"/>
	<c:set var="expenseGrandTotal" value="0"/>
	<c:set var="netDiffGrandTotal" value="0"/>
	
	<div class="errorMessage"> <c:out value="${errorMessage}"/> </div>
	<div class="successMessage"> <c:out value="${successMessage}"/> </div>
	
	<div align="center">
	
	<table width="50%">
		<tr>
			<td class="reportHeaderTitle" colspan="5">TILAHUN MESAFENT FREIGHT TRANSPORT</td>
		</tr>
		<tr>
			<td colspan="5" class="reportTitle">Revenue and Expense Per Freight Order <br/> <c:out value="${expense.date_from}"/> &nbsp;&nbsp;  - &nbsp;&nbsp;  <c:out value="${expense.date_to}"/> </td>
		</tr>
		<tr>
		<td>
		
		<table width="100%">
		<tr>
			<td class="tableHeader">No.</td>
			<td class="tableHeader">Freight Order Number</td>
			<td class="tableHeader" align="right">Revenue</td>
			<td class="tableHeader" align="right">Expense</td>
			<td class="tableHeader" align="right">Net Difference</td>
		</tr>
		<c:forEach var="expRevPerFonReport" items="${expRevPerFonReport}">
			<tr <c:choose><c:when test="${rowNum%2 == 0}">style="line-height: 35px; background-color: #e5e5e5;"</c:when><c:otherwise>style="line-height: 35px;"</c:otherwise> </c:choose>>
				<td align="center">${rowNum} - </td>
				<td style="padding-left: 5px;">${expRevPerFonReport.frightOrder.fon}</td>
				<td align="right">
					<c:set var="total_revenue" value="${expRevPerFonReport.total_revenue}"/>
					<fmt:formatNumber minFractionDigits="2" pattern="00,000.00" value="${total_revenue}"/>
				</td>
				<td align="right">					
					<c:set var="total_expense" value="${expRevPerFonReport.total_expense}"/>
					<fmt:formatNumber minFractionDigits="2" pattern="00,000.00" value="${total_expense}"/>
				</td>
				<td align="right">					
					<c:set var="net_diff" value="${expRevPerFonReport.total_revenue - expRevPerFonReport.total_expense}"/>
					<fmt:formatNumber minFractionDigits="2" pattern="00,000.00" value="${net_diff}"/>
				</td>
			</tr>
			<c:set var="rowNum" value="${rowNum + 1}"/>
			<c:set var="revenueGrandTotal" value="${revenueGrandTotal + total_revenue}"/>
			<c:set var="expenseGrandTotal" value="${expenseGrandTotal + total_expense}"/>
			<c:set var="netDiffGrandTotal" value="${netDiffGrandTotal + net_diff}"/>
		</c:forEach>
		<tr class="tableHeader">
			<td align="right" colspan="2" style="font-weight: bold;"> Total </td>
			<td align="right"> <fmt:formatNumber minFractionDigits="2" pattern="00,000.00" value="${revenueGrandTotal}"/> </td>
			<td align="right"> <fmt:formatNumber minFractionDigits="2" pattern="00,000.00" value="${expenseGrandTotal}"/> </td>
			<td align="right"> <fmt:formatNumber minFractionDigits="2" pattern="00,000.00" value="${netDiffGrandTotal}"/> </td>
		</tr>
	</table>
	
	</td>
	</tr>
	</table>
	
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>