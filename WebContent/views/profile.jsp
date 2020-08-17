<%@ page import="social.model.User" %>
<%@ page import="social.db.UserDBUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="ISO-8859-1">
<head>
    <title>Profile</title>
</head>
<body>
<%
    UserDBUtil dbUtil = new UserDBUtil();
    int userId = Integer.parseInt(request.getParameter("userId"));
    User profileUser = dbUtil.getUser(userId);
%>
<h1> <%=profileUser.getFname()%> <%=profileUser.getLname()%> Profile </h1>
<h3>First Name - <%=profileUser.getFname()%></h3>
<h3>Last Name -  <%=profileUser.getLname()%></h3>
<h3>Email - <%=profileUser.getEmail()%></h3>
<h3><a href="homeFeed.jsp">Home</a></h3>
<h3><a href="../index.jsp">Log out</a></h3>
</body>
</html>
