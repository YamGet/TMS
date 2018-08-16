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
				Expense Type Add Form
			</h4>
		</div>
		<div class="panel-body">
			<div class="errorMessage">${errorMessage} </div>
<%-- 			<form method="post" action="">				 --%>
				<div class="form-group">
					<label for="expense_type_name">Expense Type</label>
					<form:input id="expense_type_name" path="expenseType.expense_type_name" cssClass="form-control"/>				
				</div>
				<div class="form-group">
					<label for="account_number">Account Number</label>
					<form:input id="account_number" path="expenseType.account_number" cssClass="form-control"/>				
				</div>
				<div class="form-group">
					<label for="expense_type_usage">Expense Type Usage</label>
					<form:select id="expense_type_usage" path="expenseType.expense_type_usage" class="form-control">
						<form:option value="Both"> Both </form:option>
						<form:option value="Internal"> Internal Only </form:option>
					</form:select>
				</div>
				<div class="form-group">
					<label for="expense_type_status">Expense Type Status</label>
					<form:select id="expense_type_status" path="expenseType.expense_type_status" class="form-control">
						<form:option value="Active"> Active </form:option>
						<form:option value="Inactive"> Inactive </form:option>
					</form:select>
				</div>
				
				<button type="submit" class="btn btn-default" onclick="updateExpenseType('${loader}', '${expenseType.et_id}')">Update</button>
<%-- 			</form>	 --%>
			
		</div>
	</div>
	
	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>