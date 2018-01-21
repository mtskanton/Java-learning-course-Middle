<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
<div class = "container">
    <div class="page-header"><h1>List of users</h1></div>
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Creation date</th>
            <th>Country</th>
            <th>City</th>
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
            <td>${user.country}</td>
            <td>${user.city}</td>
            <td>${user.role}</td>
            <td><a href="${path}/update?id=${user.id}">E</a></td>
            <td><a href="${path}/delete?id=${user.id}">D</a></td>
        </tr>

        </c:forEach>

    </table>
    <br/>
    <c:if test="${access != 'disabled'}">
    <a href="${path}/create" class="btn btn-default">Add new user</a><br/>
    </c:if>
    <br/>
    <a href="${path}/logout" class="btn btn-default">Log out</a>
</div>
</body>
</html>
