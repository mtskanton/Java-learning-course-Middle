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
                    <h1 class="page-header">Сохранение объекта
                        <small>в cookie</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Пример работы с cookie</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <c:url value="/writeCookie" var="writeCookie" />
            <c:url value="/readCookie" var="readCookie" />
            <!-- Content Row -->
            <div class="row">

                <!-- Sidebar Column -->
                <div class="col-md-3">
                    <div class="list-group">

                        <a href="/index.html" class="list-group-item">Home</a>
                        <a href="${writeCookie}" class="list-group-item">Записать cookie</a>
                        <a href="${readCookie}" class="list-group-item">Прочитать cookie</a>
                    </div>
                </div>
                <!-- Content Column -->
                <div class="col-md-9">
                    cookieResult: <br />
                    <b> ${cookieResult} </b>
                    <br/>
                    <br/>
                    Cookie in response: <br/>
                    <b>Cookie: ${cookie.cookieExample} </b><br/>
                    <b>Cookie name: ${cookie.cookieExample.name} </b> <br/>
                    <b>Cookie value: ${cookie.cookieExample.value} </b>  <br/>
                </div>
            </div>

            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->
    </jsp:body>
</page:template>
