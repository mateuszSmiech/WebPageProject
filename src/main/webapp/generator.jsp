<%@ page contentType="text/html; utf-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<main class="container middle">
    <h1>Password Generator</h1>
    <hr/>
    <form action="generate" method="post">
        <label>Password length:</label><br/>
        <select name="passLength">
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="8">8</option>
            <option value="10">10</option>
        </select><br/>
        <label>Include Symbols:</label>
        <input type="checkbox" name="incSymbols" value="symbol" checked/><br/>
        <label>Include Numbers:</label>
        <input type="checkbox" name="incNumber" value="number" checked/><br/>
        <label>Include Lowercase Characters:</label>
        <input type="checkbox" name="incLower" value="lower" checked /><br/>
        <label>Include Uppercase Characters:</label>
        <input type="checkbox" name="incUpper" value="upper" checked/><br/>
        <hr/>
        <button type="submit">Generate Password</button><br/>
        <input type="text" name="result" class="result"
                <%if (request.getAttribute("result2") != null) {%>
               value="<%= request.getAttribute("result2")%>"/>
        <%}else{%> placeholder="Generate password"/>
        <%}%>

    </form>
    <a href="index.jsp" class="go_back">Go back to Login form</a>
</main>


</body>
</html>
