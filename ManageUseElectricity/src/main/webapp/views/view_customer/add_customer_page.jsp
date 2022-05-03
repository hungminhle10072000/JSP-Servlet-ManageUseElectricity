<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Customer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_home_page.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_form_add_customer.css"/>
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
                <a href="${pageContext.request.contextPath}/CustomerController" data-active="0">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All Customer</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="1">
                <a href="${pageContext.request.contextPath}/HouseHoldController" data-active="1">
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
                <a href="#" class="active" data-active="3">
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
    <h1 class="banner">Add Customer</h1>

    <div style="margin-bottom: 1rem;">
        <button class="btn-business  button-switch-business button-switch-business-choose">Business</button>
        <button class="btn-house-hold button-switch-house">HouseHold</button>
    </div>
    <div class="div-add-business">
        <form action="${pageContext.request.contextPath}/CustomerController" method="post">
            <div class="container">
                <p>Please fill in this form to add business customer.</p>
                <hr>

                <label><b>Name</b></label>
                <input type="text" placeholder="Enter Name" name="nameBusiness" required>

                <label><b>Address</b></label>
                <input type="text" placeholder="Enter Adress" name="address" required>

                <label><b>Phone Number</b></label>
                <input class="input-number" type="number" placeholder="Enter Phone Number" name="phone-number" required>

                <label><b>Tax Code</b></label>
                <input class="input-number" type="number" placeholder="Enter Tax Code" name="tax-code"
                       required>
                <hr>

                <input hidden name="typeCustomer" value="business"/>

                <button type="submit" class="registerbtn">Add</button>
            </div>
        </form>
    </div>

    <div class="div-add-house" style="display: none">
        <form action="${pageContext.request.contextPath}/CustomerController" method="post">
            <div class="container">
                <p>Please fill in this form to add house customer.</p>
                <hr>

                <label><b>Name</b></label>
                <input type="text" placeholder="Enter Name" name="nameHouse" required>

                <label><b>Address</b></label>
                <input type="text" placeholder="Enter Adress" name="address" required>

                <label><b>Phone Number</b></label>
                <input class="input-number" type="number" placeholder="Enter Phone Number" name="phone-number" required>

                <label><b>Indentity Card</b></label>
                <input class="input-number" type="number" placeholder="Enter Indentity Card" name="indentity-card" required>
                <hr>

                <input hidden name="typeCustomer" value="household"/>

                <button type="submit" class="registerbtn">Add</button>
            </div>
        </form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $(".btn-house-hold").click(function () {
            $(".div-add-house").css("display", "block");
            $(".div-add-business").css("display", "none");
            $(".btn-house-hold").addClass("button-switch-house-choose");
            $(".btn-business").removeClass("button-switch-business-choose");
        });

        $(".btn-business").click(function () {
            $(".div-add-house").css("display", "none");
            $(".div-add-business").css("display", "block");
            $(".btn-house-hold").removeClass("button-switch-house-choose");
            $(".btn-business").addClass("button-switch-business-choose");
        })

        document.querySelectorAll(".input-number").addEventListener("keypress", function (evt) {
            if (evt.which != 8 && evt.which != 0 && evt.which < 48 || evt.which > 57) {
                evt.preventDefault();
            }
        });
    })
</script>
</body>
</html>