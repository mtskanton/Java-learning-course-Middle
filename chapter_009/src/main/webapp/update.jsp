<%@ page import="application.User" %>
<%@ page import="application.UsersStore" %>

<%@ page contentType="text/html" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
    <% Integer id = Integer.valueOf(request.getParameter("id"));
        User user = UsersStore.getInstance().getUser(id); %>

    <b>Change user information</b>
    <br/>
    <form action="<%=request.getContextPath()%>/update" method=post>
        <table>
            <tr><td>Name</td><td><input type="text" name="name" value="<%=(user == null ? " " : user.getName())%>"></td></tr>
            <tr><td>Login</td><td><input type="text" name="login" value="<%=(user == null ? " " : user.getLogin())%>"></td></tr>
            <tr><td>Email</td><td><input type="text" name="email" value="<%=(user == null ? " " : user.getEmail())%>"></td></tr>
            <tr><td colspan="2"><input type="hidden" name="id" value="<%=(user == null ? " " : String.valueOf(user.getId()))%>"><input type="submit" value="Submit"></td></tr>
        </table>
    </form>

</body>
</html>
