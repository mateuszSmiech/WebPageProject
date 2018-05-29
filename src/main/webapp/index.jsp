<%@ page contentType="text/html; utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<% if(session.getAttribute("loggedIn")!=null) {
    response.sendRedirect("dashboard.jsp");
}%>
<c:if test="${sessionScope.get('invalidLogin') eq ''}}">
<div class="error_message" style="display: none">
</div>
</c:if>
<c:if test="${sessionScope.get('invalidLogin') ne null }">
    <div class="error_message" style="display: block">
        <%=session.getAttribute("invalidLogin")%>
    </div>
</c:if>
<main class="container middle" style="height:400px">
    <h1>Login Form</h1>
    <hr/>
    <form action="login" method="post">
        <label>Login:</label><br/>
        <input type="text" name="loginInput" class="login_form"/><br/>
        <label>Password:</label><br/>
        <input type="text" name="passwordInput" class="login_form"/><br/>
        <button type="submit" class="login_button">Login</button>
    </form>
</main>
<footer>
    <a href="generator.jsp" class="footer_link">Password Generator</a>
</footer>

</body>
</html>
