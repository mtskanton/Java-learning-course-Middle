<%@ page contentType="text/html" language="java" %>
<html>
<head>
    <title>Create new user</title>
</head>
<body>
    <b>Create new user</b>
    <br/>
    <form action="<%=request.getContextPath()%>/create" method=post>
        <table>
            <tr><td>Name</td><td><input type="text" name="name"></td></tr>
            <tr><td>Login</td><td><input type="text" name="login"></td></tr>
            <tr><td>Email</td><td><input type="text" name="email"></td></tr>
            <tr><td colspan="2"><input type="submit" value="Submit"></td></tr>
        </table>
    </form>
</body>
</html>
