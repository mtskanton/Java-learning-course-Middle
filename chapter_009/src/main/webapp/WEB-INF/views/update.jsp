<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
    <style type="text/css">
        .disabled {
            pointer-events: none;
            cursor: not-allowed;
        }
    </style>
</head>
<body>

    <b>Change user information</b>
    <br/>
    <form action="${pageContext.servletContext.contextPath}/update" method=post>
        <table>
            <tr><td>Name</td><td><input type="text" name="name" value="<c:out value="${user.name}"></c:out>"></td></tr>
            <tr><td>Login</td><td><input type="text" name="login" value="<c:out value="${user.login}"></c:out>"></td></tr>
            <tr><td>Password</td><td><input type="password" name="password" value="<c:out value="${user.password}"></c:out>"></td></tr>
            <tr><td>Email</td><td><input type="text" name="email" value="<c:out value="${user.email}"></c:out>"></td></tr>
            <tr><td>Role</td>
                <td>
                    <select name="role" class="${access}">
                        <c:forEach items="${roles}" var="each">
                        <option ${user.role == each.role ? "selected" : ""} value="${each.role}">${each.role}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr><td colspan="2"><input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"><input type="submit" value="Submit"></td></tr>
        </table>
    </form>

</body>
</html>
