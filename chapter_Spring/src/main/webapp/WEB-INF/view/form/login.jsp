<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <!-- Page Content -->
        <div class="container">

        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Пожалуйста войдите</h1>
                <ol class="breadcrumb">
                    <li><a href="index.html">Home</a>
                    </li>
                    <li class="active">Пожалуйста войдите</li>
                </ol>
            </div>
        </div>
        <!-- /.row -->

        <form name="form" action="j_spring_security_check" method="post" class="form-signin">
            <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>

            <c:if test="${not empty param.error}">
                <font size="2" color="red"><b>Неправильный логин или пароль</b></font>
            </c:if>

            <input id="inputEmail" class="form-control" name="j_username" value="admin@gmail.com" required autofocus/>

            <input type="password" id="inputPassword" class="form-control" name="j_password" value="123" required/>

            <div class="checkbox">
                <label>
                    <input type="checkbox" id="rememberme"  name="_spring_security_remember_me"/>Запомнить меня
                </label>
            </div>
            <input type="submit" value="Войти" class="btn btn-lg btn-primary btn-block" >
            <br/>
            <a href="javascript:history.back()">Назад</a>

        </form>

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>
