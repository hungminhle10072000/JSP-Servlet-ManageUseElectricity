<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_home_page.css">
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
                            <i class='bx bx-tachometer'></i>
                            <i class='bx bxs-tachometer'></i>
                        </div>
                        <span class="link hide">Dashboard</span>
                    </a>
                </li>
                <li class="tooltip-element" data-tooltip="1">
                    <a href="${pageContext.request.contextPath}/branchController" data-active="1">
                        <div class="icon">
                            <i class='bx bx-folder'></i>
                            <i class='bx bxs-folder'></i>
                        </div>
                        <span class="link hide">Manage branch</span>
                    </a>
                </li>
                <li class="tooltip-element" data-tooltip="2">
                    <a href="#" data-active="2">
                        <div class="icon">
                            <i class='bx bx-message-square-detail'></i>
                            <i class='bx bxs-message-square-detail'></i>
                        </div>
                        <span class="link hide">Manage customer</span>
                    </a>
                </li>
                <li class="tooltip-element" data-tooltip="3">
                    <a href="${pageContext.request.contextPath}/FormUseController" data-active="3">
                        <div class="icon">
                            <i class='bx bx-bar-chart-square'></i>
                            <i class='bx bxs-bar-chart-square'></i>
                        </div>
                        <span class="link hide">Manage form use</span>
                    </a>
                </li>
                <li class="tooltip-element" data-tooltip="4">
                    <a href="#" data-active="4">
                        <div class="icon">
                            <i class='bx bx-bar-chart-square'></i>
                            <i class='bx bxs-bar-chart-square'></i>
                        </div>
                        <span class="link hide">Manage contract</span>
                    </a>
                </li>
                <li class="tooltip-element" data-tooltip="5">
                    <a href="#" data-active="5">
                        <div class="icon">
                            <i class='bx bx-bar-chart-square'></i>
                            <i class='bx bxs-bar-chart-square'></i>
                        </div>
                        <span class="link hide">Manage electric meter</span>
                    </a>
                </li>
                <li class="tooltip-element" data-tooltip="6">
                    <a href="#" data-active="6">
                        <div class="icon">
                            <i class='bx bx-bar-chart-square'></i>
                            <i class='bx bxs-bar-chart-square'></i>
                        </div>
                        <span class="link hide">Manage notebook</span>
                    </a>
                </li>
                <li class="tooltip-element" data-tooltip="7">
                    <a href="#" data-active="7">
                        <div class="icon">
                            <i class='bx bx-bar-chart-square'></i>
                            <i class='bx bxs-bar-chart-square'></i>
                        </div>
                        <span class="link hide">Manage Invoice</span>
                    </a>
                </li>
                <div class="tooltip">
                    <span class="show">Dashboard</span>
                    <span>Projects</span>
                    <span>Messages</span>
                    <span>Analytics</span>
                </div>
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
        <h1 class="banner">Manage Use Electricity</h1>
    </main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
</body>
</html>