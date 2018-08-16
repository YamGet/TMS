<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/resources/images/loader.gif" var="loader"/>

<form:select id="ur_id" path="map" cssClass="form-control" onchange="getSystemURLListPerModule('${loader}', this.value)" style="min-width: 150px;">
	<form:option value="-" ></form:option>
	<form:options itemLabel="systemModule.module_name" itemValue="systemModule.m_id" items="${map.relatedSystemModuleList}"/>
</form:select>