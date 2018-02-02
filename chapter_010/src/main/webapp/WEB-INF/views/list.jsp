<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список объявлений</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class = "container">
    <div class="page-header"><h1>Список объявлений</h1></div>

    <c:if test="${sessionScope.userId == null}">
        <a class="btn btn-default" href="${path}/login">Войти</a>
    </c:if>
    <c:if test="${sessionScope.userId != null}">
        <a class="btn btn-default" href="${path}/logout" onclick="return confirm('Хотите выйти?')">Выйти</a>
    </c:if>

    <a class="btn btn-default" href="${path}/create">Разместить объявление</a> <br/><br/>

    <c:forEach items="${ads}" var="ad">
        <c:if test="${sessionScope.userId == ad.user.id}">
            <a class="btn btn-default" href="${path}/update?id=${ad.id}">Обновить</a>
            <a class="btn btn-default" href="${path}/delete?id=${ad.id}" onclick="return confirm('Вы уверены?')">Удалить</a> <br/>
        </c:if>
        <br/> <img src="${uploadPath}${ad.id}.jpg" alt="no-image" class="img-responsive" style="max-height:200px"><br/>
        <c:if test="${ad.sold == true}">
            Продано! <br/>
        </c:if>
        последнее обновление: <fmt:formatDate value="${ad.date}" pattern="d MMMM yyyy, H:mm:ss"/> <br/>
        марка: ${ad.brand.name} <br/>
        модель: ${ad.model} <br/>
        год: ${ad.year} <br/>
        кузов: ${ad.carcase.type} <br/>
        цена: ${ad.price} руб. <br/>
        описание: ${ad.description} <br/>
        контакты для связи: ${ad.user.name} ${ad.user.phone} <br/>
        <hr/>
    </c:forEach>
</div>
</body>
</html>
