<%@ page import="social.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="ISO-8859-1">
<head>
    <title>Wall</title>
</head>
<body>
<h1>Welcome, ${user.getFname()} ${user.getLname()} </h1>
<%
    User user = (User) session.getAttribute("user");
%>
<h1></h1>
<h3><a href="../index.jsp">LOG OUT</a></h3>
</body>
</html>
