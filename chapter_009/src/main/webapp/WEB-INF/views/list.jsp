<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html" language="java" %>
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
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <c:forEach items="${users}" var="user">

        <tr>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.created}"></c:out></td>
            <td><a href="${path}/update?id=<c:out value="${user.id}"></c:out>">E</a></td>
            <td><a href="${path}/delete?id=<c:out value="${user.id}"></c:out>">D</a></td>
        </tr>

        </c:forEach>

    </table>
    <br/>
    <a href="${path}/create">Add new user</a>

</body>
</html>
