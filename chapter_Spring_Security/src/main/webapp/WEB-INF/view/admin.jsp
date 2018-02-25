<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
    This is admin page
    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="Logoff"/> (also clears any remember-me cookie)
        <security:csrfInput/>
    </form>
</body>
</html>
