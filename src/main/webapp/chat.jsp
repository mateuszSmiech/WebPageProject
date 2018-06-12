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

<form action="chat" method="post">
    <div class="chat_input_position">
        <input type="text" name="messageInput" required class="chat_message"/>
        <button type="submit" class="chat_button">Send</button>
    </div>
    <section class="chat_position">
    <%--<div class="message_area">--%>
    <c:forEach items="${sessionScope.get('messageList')}" var="message">
        <div class="chat-text">
            <span class="chat_username">${message.getUsername()}</span>
            <span>${message.getDate()}</span><br/>
            <p class="chat_display">${message.getMessage()}</p>
        </div>
    </c:forEach>
    <%--</div>--%>

    </section>
</form>


</body>
</html>
