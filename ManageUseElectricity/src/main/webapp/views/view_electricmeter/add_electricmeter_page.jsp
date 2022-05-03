<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Electric Meter</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_home_page.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add_contract.css"/>
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
                <a href="${pageContext.request.contextPath}/ElectricMeterController" data-active="0">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All Electric Meter</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="1">
                <a class="active"
                   href="${pageContext.request.contextPath}/ElectricMeterController?typePage=addElectricPage"
                   data-active="1">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">Add Electric Meter</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="2">
                <a href="${pageContext.request.contextPath}/views/home_admin_page.jsp" data-active="2">
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
    <c:if test="${alert != null}">
        <h1 style="color: red">${alert}</h1>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/ElectricMeterController" accept-charset="utf-8">
        <div>
            <label for="typeElectricMeter">Type Electric Meter</label>
            <input style="display: block; width: 100%; height: 2rem" type="text" id="typeElectricMeter"
                   name="typeElectricMeter" required>
        </div>

        <div style="margin-top: 1rem; margin-bottom: 1rem">
            <label for="selectContract" style="margin-bottom: 2rem;">Select Contract</label>
            <div style="width:200px;">
                <select name="selectContract" id="selectContract" required>
                    <option value="0">Select Contract</option>
                    <c:if test="${contractList.size() >0}">
                        <c:forEach items="${contractList}" var="contract">
                            <option value="${contract.getId()}">${contract.getId()}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>

        <input type="submit" value="Submit" id="btnSubmit">
    </form>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#btnSubmit").click(function (event) {
            let checkConfirm = confirm("Are you sure you want to add electric meter !!!");
            if (checkConfirm) {
                let valueSelectContract = $('#selectContract').val();
                if (valueSelectContract == 0) {
                    alert("Request choose contract !!!");
                    event.preventDefault();
                    return;
                }
            } else {
                event.preventDefault();
            }
        })
    });
</script>
</body>
</html>
