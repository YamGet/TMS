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

	<table cellpadding="0" cellspacing="0" width="100%" border="0">
		<tr>
			<td valign="top" width="60%" align="left">
			
				<table class="table table-hover" cellpadding="0" cellspacing="1" width="80%">  
					<thead> 
						<tr style="background-color: #e5e5e5; text-align: center;"> 
							<th> Expense Type </th> 
							<th> Account Number </th>
							<th> Expense Usage </th>
							<th> Expense Type Status </th>
						</tr> 
					</thead>
					<c:forEach var="expenseTypeList" items="${expenseTypeList}">
						<tbody> 
							<tr>
								<td> 
								
									<c:set var="et_id" value="${expenseTypeList.et_id}"/>
									<c:set var="expense_type_name" value="${expenseTypeList.expense_type_name}"/>
									<c:set var="account_number" value="${expenseTypeList.account_number}"/>
									<c:set var="expense_type_usage" value="${expenseTypeList.expense_type_usage}"/>
									<c:set var="expense_type_status" value="${expenseTypeList.expense_type_status}"/>
								
									<div class="onclick" onclick="getExpenseTypeUpdateForm('${loader}','${et_id}', '${expense_type_name}', '${account_number}', '${expense_type_usage}', '${expense_type_status}')">
										${expenseTypeList.expense_type_name} 
									</div>
								</td>
								<td> ${expenseTypeList.account_number} </td>
								<td> ${expenseTypeList.expense_type_usage} </td>						
								<td> ${expenseTypeList.expense_type_status} </td> 
							</tr>										
						</tbody>
					</c:forEach>
				</table>
			
			</td>
			<td width="5%">&nbsp;</td>
			<td valign="top">
			
				<div id="expenseTypeForm">
					<jsp:include page="expenseTypeSaveForm.jsp"/>
				</div>
			
			</td>
		</tr>
	</table>

	<jsp:include page="/pages/footer_resource.jsp"/>

</body>
</html>