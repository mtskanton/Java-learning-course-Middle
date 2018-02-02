<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Введите данные регистрации</h2>
    <c:if test="${error != null}">
        <div class="alert alert-danger"><strong>Warning!</strong> ${error}</div>
    </c:if>

    <form action="" method="post">
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
    </form>
</div>
</body>
</html>
