<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TMS</title>
</head>
<body>

	<div class="panel panel-default" style="width: 60%">
		<div class="panel-heading">
			<h4 class="panel-title">
				Freight Order Expense Amount Update Form
			</h4>
		</div>
		<div class="panel-body">
			
			<div id="foErrorMessage" class="errorMessage"> ${errorMessage} </div>
			
			<div class="form-group">
				<label for="expenseType">Expense Type</label>
				<form:input id="expenseType" path="expense.expenseType.expense_type_name" class="form-control" readonly="true"/>
			</div>
			<div class="form-group">
				<label for="accountNumber">Account Number</label>
				<form:input id="accountNumber" path="expense.expenseType.account_number" class="form-control" readonly="true"/>			
			</div>
			<div class="form-group">
				<label for="expenseAmount">Expense Amount</label>
				<form:input id="expenseAmount" path="expense.expense_amount" class="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-default" onclick="updateFOExpenseAmount('${loader}', '${expense.fo_id}', '${expense.e_id}', '${expense.expenseType.expense_type_name}', '${expense.expenseType.account_number}')">Update</button>
			
		</div>
	</div>

</body>
</html>