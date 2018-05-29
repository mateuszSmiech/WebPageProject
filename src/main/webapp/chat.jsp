<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(session.getAttribute("loggedIn")==null) {
    response.sendRedirect("index.jsp");
}%>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
<%@ include file="navigation.jsp" %>

<br/>
<section class="middle chat_container">
<form action="chat" method="post">
    <input type="text" name="messageInput" required class="chat_message"/>
    <button type="submit" class="chat_button">Send</button>
    <c:forEach items="${sessionScope.get('messageList')}" var="message">
        <div class="chat-text">
            <span class="chat_username">${message.getUsername()}</span>
            <span><%= session.getAttribute("date")%></span><br/>
            <p class="chat_display">${message.getMessage()}</p>
        </div>

    </c:forEach>
    <br/>
</form>
</section>

</body>
</html>
