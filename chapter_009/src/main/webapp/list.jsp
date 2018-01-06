<%@ page import="application.User" %>
<%@ page import="java.util.List" %>
<%@ page import="application.UsersStore" %>
<%@ page import="java.util.Collections" %>

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

        <%  List<User> users = UsersStore.getInstance().getUsers();
            Collections.sort(users);
            for (User user : users) {
        %>

        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getCreated()%></td>
            <td><a href="<%=request.getContextPath()%>/update?id=<%=user.getId()%>">E</a></td>
            <td><a href="<%=request.getContextPath()%>/delete?id=<%=user.getId()%>">D</a></td>
        </tr>

        <% } %>
    </table>
    <br/>
    <a href="<%=request.getContextPath()%>/create">Add new user</a>

</body>
</html>
