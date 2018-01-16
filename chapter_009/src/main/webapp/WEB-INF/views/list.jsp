<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
    <b>List of users</b>
    <br/>
    <table cellpadding="5" cellspacing="0" border="1px" bordercolor="#000000"  >
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Creation date</th>
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.created}</td>
            <td>${user.role}</td>
            <td><a href="${path}/update?id=${user.id}">E</a></td>
            <td><a href="${path}/delete?id=${user.id}">D</a></td>
        </tr>

        </c:forEach>

    </table>
    <br/>
    <c:if test="${access != 'disabled'}">
    <a href="${path}/create">Add new user</a><br/>
    </c:if>
    <br/>
    <a href="${path}/logout">Log out</a>
</body>
</html>
