<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hduonghung
  Date: 4/26/2022
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cssProject.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<div class="wrapper">
    <form class="login">
        <p class="title">Log in</p>
        <input type="text" placeholder="Username" autofocus class="text-username"/>
        <i class="fa fa-user"></i>
        <input type="password" placeholder="Password" class="text-password"/>
        <i class="fa fa-key"></i>
        <button>
            <i class="spinner"></i>
            <span class="state" urlBase="${pageContext.request.contextPath}">Log in</span>
        </button>
    </form>
    </p>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/JsLoginPage.js"></script>
</body>
</html>
