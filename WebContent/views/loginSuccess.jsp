<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="social.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Success</title>
</head>
<body>
	<h1>YOU ARE LOGGED IN</h1>
	<%
		User user = (User) session.getAttribute("user");
	%>
	<h1>${user.getFname()} ${user.getLname()}</h1>
	<h3><a href="../index.jsp">LOG OUT</a></h3>
</body>
</html>