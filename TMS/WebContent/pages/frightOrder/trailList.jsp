<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<label for="TrialPlateNumber">Trailer Plate Number</label>
<form:select id="tli_id" path="map" cssClass="form-control" >
	<form:option value="-" ></form:option>
	<form:options itemLabel="trail_plate_no" itemValue="tli_id" items="${map.getAvailableTrails}"/>
</form:select>