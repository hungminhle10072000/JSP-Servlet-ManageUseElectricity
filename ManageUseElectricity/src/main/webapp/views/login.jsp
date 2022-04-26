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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/JsLoginPage.js"></script>
<div class="wrapper">
    <form class="login">
        <p class="title">Log in</p>
        <input type="text" placeholder="Username" autofocus/>
        <i class="fa fa-user"></i>
        <input type="password" placeholder="Password"/>
        <i class="fa fa-key"></i>
        <button>
            <i class="spinner"></i>
            <span class="state">Log in</span>
        </button>
    </form>
    </p>
</div>
</body>
</html>
