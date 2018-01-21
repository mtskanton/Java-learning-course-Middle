<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script>
        function validateForm() {
            var error_message = document.getElementById("error");
            if(document.loginForm.login.value == "" || document.loginForm.password.value == "") {
                error_message.className = "alert alert-danger";
                error_message.innerHTML = "<strong>Warning!</strong> Both fields, login and password should be filled";
                return;
            }
            document.loginForm.submit();
        }
    </script>
</head>
<div class="container">
    <h2>Please enter your credentials</h2>
    <div id="error">
        <c:if test="${error != null}">
            <div class="alert alert-danger"><strong>Warning!</strong> ${error}</div>
        </c:if>
    </div>

    <form name="loginForm" onsubmit="validateForm(); return(false);" action="${pageContext.servletContext.contextPath}/login" method=post>
        <div class="form-group row">
            <div class="col-xs-4">
                <label for="login">Login:</label>
                <input id="login" class="form-control" name="login" type="text" placeholder="Enter login">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-xs-4">
                <label for="pass">Password:</label>
                <input id="pass" class="form-control" name="password" type="password" placeholder="Enter password">
            </div>
        </div>
       <input class="btn btn-default" type="submit" value="Submit">
    </form>
</div>
</body>
</html>
