<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
<% if(session.getAttribute("loggedIn")==null) {
    response.sendRedirect("index.jsp");
}%>
<%@ include file="navigation.jsp"%>
<br/>

</body>
</html>
