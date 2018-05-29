
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
<section class="middle chat_container">
<form action="chat" method="post">
    <input type="text" name="messageInput" required class="chat_message"/>
    <button type="submit" class="chat_button">Send</button>
    <div class="chat-text">
    qweqwe
    </div>
    <div class="chat-text">
        qweqwe
    </div>
    <div class="chat-text">
        qweqwe
    </div>
    <div class="chat-text">
        qweqwe
    </div>
    <br/>
</form>
</section>

</body>
</html>
