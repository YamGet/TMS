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
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr><td style="border-bottom: thin; border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: thin; padding-bottom: 5px;">
			
			<div>
				<table><tr><td style="padding-top: 14px;">
				<div class="form-group">
					<input id="fon" type="text" class="form-control" placeholder="Search FON">
				</div>
				</td><td style="padding-left: 5px;">
					<button type="submit" class="btn btn-default" onclick="searchFrightOrder('${loader}')" >Search</button>
				</td></tr></table>
			</div>
			
		</td></tr>
		<tr><td>
		
			<div id="expenseSearchResult" style="padding-top: 5px; width: 100%">
				<div class="successMessage"> <c:out value="${message}"/> </div>
			</div>
			
		</td></tr>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
</body>
</html>