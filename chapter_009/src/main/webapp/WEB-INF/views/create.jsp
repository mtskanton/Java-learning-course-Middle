<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new user</title>
</head>
<body>
    <b>Create new user</b>
    <c:if test="${error != ''}">
        <div style="background-color: red">${error}</div>
    </c:if>

    <br/>
    <form action="${pageContext.servletContext.contextPath}/create" method=post>
        <table>
            <tr><td>Name</td><td><input type="text" name="name"></td></tr>
            <tr><td>Login</td><td><input type="text" name="login"></td></tr>
            <tr><td>Password</td><td><input type="password" name="password"></td></tr>
            <tr><td>Email</td><td><input type="text" name="email"></td></tr>
            <tr><td>Role</td>
                <td>
                    <select name="role">
                        <c:forEach items="${roles}" var="each">
                            <option>${each.role}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr><td colspan="2"><input type="submit" value="Submit"></td></tr>
        </table>
    </form>
</body>
</html>
