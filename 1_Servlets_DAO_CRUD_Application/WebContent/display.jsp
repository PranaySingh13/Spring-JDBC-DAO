<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%-- To Import Class in JSPs use JSP page directive with import attribute--%>
<%@page import="com.gk.dto.Student"  %>
	<%-- JSP declaration tag is used to declare fields and methods--%>
<%! Student student; %>
	<%-- JSP Script let tag for used to execute java source code in JSP --%>
<% student=(Student)request.getAttribute("std"); %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GK Software Solutions</title>
<style>
table, td {
  border: 5px solid white;
  border-collapse: collapse;
}
</style>

</head>
<body style="text-align: center; background-color: maroon;">
	<br><br><br><br><br><br><br><br>
	<table style="margin-left: auto; margin-right: auto; color: yellow;">
		<tr>
			<td>Student Id: </td>
			<%--JSP expression tag is written to the output stream of the response. --%>
			<td><%= student.getSid() %></td>
		</tr>
		<tr>
			<td>Student Name: </td>
			<td><%= student.getSname() %></td>
		</tr>
		<tr>
			<td>Student Address: </td>
			<td><%= student.getSaddr() %></td>
		</tr>
	</table>
</body>
</html>