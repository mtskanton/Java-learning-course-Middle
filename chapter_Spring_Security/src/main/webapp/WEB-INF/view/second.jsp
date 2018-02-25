<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Second page</title>
</head>
<body>
    <p>This is secondPage page (webapp\WEB-INF\views\second.jsp).
    <p>without login you can't see it

    <form action="<c:url value="/logout"/>" method="post">
        <input type="submit" value="log out"/> (also clears any remember-me cookie)
        <security:csrfInput/>
    </form>
</body>
</html>
