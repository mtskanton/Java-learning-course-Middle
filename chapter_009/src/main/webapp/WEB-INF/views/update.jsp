<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>

    <b>Change user information</b>
    <br/>
    <form action="${pageContext.servletContext.contextPath}/update" method=post>
        <table>
            <tr><td>Name</td><td><input type="text" name="name" value="<c:out value="${user.name}"></c:out>"></td></tr>
            <tr><td>Login</td><td><input type="text" name="login" value="<c:out value="${user.login}"></c:out>"></td></tr>
            <tr><td>Email</td><td><input type="text" name="email" value="<c:out value="${user.email}"></c:out>"></td></tr>
            <tr><td colspan="2"><input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"><input type="submit" value="Submit"></td></tr>
        </table>
    </form>

</body>
</html>
