<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Welcome page</title>
</head>
<body>
    <p>This is welcome file page (webapp\WEB-INF\view\welcome.jsp).
    <p>Click button to try security!
    <p>Click me to go inside app!
        (link handle by MainController)
        <a href="<c:url value="/second"/>">Go!</a>
        <br>
    <p>Next link for admin only! Try it</p>
    <a href="<c:url value="/admin"/>">AdminPage</a>
</body>
</html>
