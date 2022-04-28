<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Form Use</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_home_page.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css"/>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<nav>
    <div class="sidebar-top">
          <span class="shrink-btn">
            <i class='bx bx-chevron-left'></i>
          </span>
        <img src="${pageContext.request.contextPath}/resources/images/logo.png" class="logo" alt="">
        <h3 class="hide">Training</h3>
    </div>

    <div class="search" style="visibility: hidden;display: none">
        <i class='bx bx-search'></i>
        <input type="text" class="hide" placeholder="Quick Search ...">
    </div>

    <div class="sidebar-links" style="margin-top: 1rem">
        <ul>
            <li class="tooltip-element" data-tooltip="0">
                <a href="#" class="active" data-active="0">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All Form Use</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="1">
                <a href="${pageContext.request.contextPath}/views/home_admin_page.jsp" data-active="1">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">Go Back</span>
                </a>
            </li>
        </ul>
    </div>

    <div class="sidebar-footer">
        <a href="#" class="account tooltip-element" data-tooltip="0">
            <i class='bx bx-user'></i>
        </a>
        <div class="admin-user tooltip-element" data-tooltip="1">
            <div class="admin-profile hide">
                <img src="${pageContext.request.contextPath}/resources/images/face-1.png" alt="">
                <div class="admin-info">
                    <h3>Hùng</h3>
                    <h5>Admin</h5>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/views/login.jsp" class="log-out">
                <i class="fa fa-sign-out" aria-hidden="true"></i>
            </a>
        </div>
    </div>
</nav>
<main>
    <h1 class="banner">Manage Form Use</h1>

    <input type="text" placeholder="Search.." id="searchBar">

    <div>
        <table id="branchs">
            <thead>
            <th>ID</th>
            <th>Name Form</th>
            <th>Unit Price</th>
            <th></th>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${formUseList.size() == 0}">
<%--                        <tr>--%>
<%--                            <h2>NO DATA</h2>--%>
<%--                        </tr>--%>
                    </c:when>
                    <c:when test="${formUseList.size() > 0}">
                        <c:forEach items="${formUseList}" var="formUse">
                            <tr>
                                <td>${formUse.getId()}</td>
                                <td contenteditable='true'>
                                        ${formUse.getNameForm()}
                                </td>
                                <td contenteditable='true'>
                                        ${formUse.getUnitPrice()}
                                </td>
                                <td>
                                    <button class="button-update action-update-form-use"
                                            id-formUse-update="${formUse.getId()}">
                                        Update
                                    </button>
                                    <button class="button-delete action-delete-form-use"
                                            id-formUse-delete="${formUse.getId()}">
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <tr class="row-add-form-use">
                    <td></td>
                    <td contenteditable='true'></td>
                    <td contenteditable='true'></td>
                    <td>
                        <button class="button-add action-add-form-use">Add</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
</body>
</html>