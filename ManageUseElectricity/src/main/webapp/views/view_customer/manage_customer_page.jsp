<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Customer</title>
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
                    <span class="link hide">List All Customer</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="2">
                <a href="${pageContext.request.contextPath}/HouseHoldController" data-active="2">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All HouseHold Customer</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="2">
                <a href="${pageContext.request.contextPath}/BusinessCustomerController" data-active="2">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All Business Customer</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="3">
                <a href="${pageContext.request.contextPath}/views/view_customer/add_customer_page.jsp" data-active="3">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">Add Customer</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="4">
                <a href="${pageContext.request.contextPath}/views/home_admin_page.jsp" data-active="4">
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
    <h1 class="banner">Manage Customer</h1>

    <input type="text" placeholder="Enter keyword....." id="searchBar" value="${keyWord}">

    <button class="button-add btn-search" style="margin-bottom: 1rem; width: fit-content">Search</button>

    <div>
        <table id="customers" class="table-custom">
            <tr>
                <th>ID</th>
                <th>Address</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${customerList.size() == 0}">
                    <tr>
                        <td>No Data</td>
                        <td>No Data</td>
                        <td>No Data</td>
                        <td>No Data</td>
                        <td></td>
                    </tr>
                </c:when>
                <c:when test="${customerList.size() > 0}">
                    <c:forEach items="${customerList}" var="customer">
                        <tr>
                            <td>${customer.getId()}</td>
                            <td>${customer.getAddress()}</td>
                            <td>${customer.getName()}</td>
                            <td>${customer.getPhoneNumber()}</td>
                            <td>
                                <button class="button-update">
                                    Detail
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
        </table>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
<script type="text/javascript">
    $(document).ready(function (){
        $(".btn-search").on('click', function () {
            let keyWord = $("#searchBar").val();
            window.location.assign('${pageContext.request.contextPath}/CustomerController?keyWord=' + keyWord);
        })
    })
</script>
</body>
</html>