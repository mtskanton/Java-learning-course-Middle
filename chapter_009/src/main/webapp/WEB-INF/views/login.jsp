<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <b>Please enter your credentials</b>
    <c:if test="${error != ''}">
        <div style="background-color: red">${error}</div>
    </c:if>

    <br/>
    <form action="${pageContext.servletContext.contextPath}/login" method=post>
        <table>
            <tr><td>Login</td><td><input type="text" name="login"></td></tr>
            <tr><td>Password</td><td><input type="password" name="password"></td></tr>
            <tr><td colspan="2"><input type="submit" value="Submit"></td></tr>
        </table>
    </form>
</body>
</html>
