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
	
	<c:set var="fo_id" value="${expense.fo_id}"/>
	
	<div class="panel panel-default" style="width: 60%">
		<div class="panel-heading">						
			<table width="100%"><tr>
				<td>
					<h4 class="panel-title">Registered Expense List </h4> 
				</td>
				<td align="right">
					&nbsp;
				</td>
			</tr></table>
		</div>
		<table class="table">
			<c:set var="totalSumExpense" value="0"/>
			<tr> 
				<th width="250px;"> Details </th>
				<th width="150px;" style="text-align: center;"> Account Number </th>
				<th width="150px;" style="text-align: right;"> Expense Amount </th>
				<th width="150px;" style="text-align: right;"> &nbsp; </th> 
			</tr>
			<c:forEach var="expenseList" items="${expenseList}">
				<tr> 
					<td> ${expenseList.expenseType.expense_type_name} </td>
					<td align="center"> ${expenseList.expenseType.account_number} </td>
					<td align="right"> ${expenseList.expense_amount} </td>
					<td align="right"> [ <span onclick="updateFORegistoredExpenseAmount('${fo_id}', '${loader}', '${expenseList.expense_amount}', '${expenseList.e_id}', '${expenseList.expenseType.expense_type_name}', '${expenseList.expenseType.account_number}')" style="color: #3d6e9f; cursor: pointer;"> EDIT </span> ] </td>
				</tr>
				<c:set var="totalSumExpense" value="${totalSumExpense +  expenseList.expense_amount}"/>
			</c:forEach>			
		</table>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>