<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="/pages/header_resource.jsp"/>
</head>
<body>
	
	<c:set var="rowNum" value="1"/>
	<table id="downloadlink" class="table table-striped table-bordered" width="100%" cellspacing="0">
		<thead>
			<tr>
				<th>No.</th>
				<th>File Name</th>
				<th>Note</th>
				<th>File Type</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="file" items="${files}" >
				<tr>
					<td> ${rowNum} </td>
					<td> ${file.filename} </td>
					<td> ${file.notes} </td>
					<td> ${file.type} </td>
					<td>
						<a href="/TMS/files/download?id=${file.f_id}">Download</a>
					</td>
					<td>
						<a href="/TMS/files/delete?id=${file.f_id}">Delete</a>
					</td>
				</tr>
				<c:set var="rowNum" value="${rowNum + 1}"/>
			</c:forEach>
		</tbody>
	</table>
	
	<jsp:include page="/pages/footer_resource.jsp"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#downloadlink').DataTable();
		});
	</script>
	
</body>
</html>