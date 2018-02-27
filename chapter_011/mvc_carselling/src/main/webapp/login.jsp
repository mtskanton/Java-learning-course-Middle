<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница входа</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<div class = "container">
    <div class="page-header"><h1>Страница входа</h1></div>

    <c:if test="${not empty param.error}">
      <span style="color: red; ">
        Ошибка аутентификации. Попробуйте еще раз. <br/><br/>
        Причина: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
      </span>
    </c:if>

    <form action="<c:url value='login'/>" method="post">
        <div class="form-group row">
            <div class="col-xs-4">
                <label for="login">Login:</label>
                <input id="login" class="form-control" name="login" type="text" placeholder="Введите логин" required>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-xs-4">
                <label for="pass">Password:</label>
                <input id="pass" class="form-control" name="password" type="password" placeholder="Введите пароль" required>
            </div>
        </div>
        <input class="btn btn-default" type="submit" value="Войти">
        <%--<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>--%>
    </form>
</div>
</body>

</html>


