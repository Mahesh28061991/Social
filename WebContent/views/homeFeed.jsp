<%@ page import="social.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta charset="ISO-8859-1">
<head>
    <title>Home Feed</title>
</head>
<body>
<h1>Welcome ${user.getFname()} ${user.getLname()}! </h1>
<%
    User user = (User) session.getAttribute("user");
%>
<p><a href="profile.jsp?userId=<%=user.getUserId()%>">Profile</a></p>
<p>Friends</p>
    <ul>
        <% List<User> list = user.getFriends();
        for(User friend : list) {
        %>
        <li><a href="profile.jsp?userId=<%=friend.getUserId()%>"><%=friend.getFname()%> <%=friend.getLname()%></a></li>
        <%}
        %>
    </ul>
</body>


<h3><a href="../index.jsp">Log out</a></h3>
</body>

</html>
