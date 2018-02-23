<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">About</jsp:attribute>

    <jsp:body>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Страница с описанием</h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">About</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->
    </jsp:body>

</page:template>
