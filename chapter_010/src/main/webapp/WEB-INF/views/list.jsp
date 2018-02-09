<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список объявлений</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script>
        function filterParams() {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i=0;i<vars.length;i++) {
                var pair = vars[i].split("=");
                if (pair[0] == 'today') {
                    document.getElementById("for_today").checked = true;
                }
                if (pair[0] == 'brand') {
                    var br = document.getElementById("brand");
                    br.options[pair[1]].selected = true;
                }
            }
        }
    </script>
</head>
<body onload="filterParams()">
<div class = "container">
    <div class="page-header"><h1>Список объявлений</h1></div>

    <c:if test="${sessionScope.userId == null}">
        <a class="btn btn-default" href="${path}/login">Войти</a>
    </c:if>
    <c:if test="${sessionScope.userId != null}">
        <a class="btn btn-default" href="${path}/logout" onclick="return confirm('Хотите выйти?')">Выйти</a>
    </c:if>

    <a class="btn btn-default" href="${path}/create">Разместить объявление</a> <br/><br/>

    <div class="form-group row">
        <div class="col-lg-3">
            <form method="get" action="">
                <input id="for_today" name="today" type="checkbox"/>
                <label for="for_today">Обновления за сегодня</label>
                <br/>
                <label for="brand">Марка авто</label>
                <select id="brand" class="form-control" name="brand">
                    <option value=""></option>
                    <c:forEach items="${brands}" var="brand">
                        <option value="${brand.id}">${brand.name}</option>
                    </c:forEach>
                </select>
                <br/>
                <input class="btn btn-default mb-2" type="submit" value="Применить"/>
            </form>
        </div>
    </div>

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
