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
                <a href="${pageContext.request.contextPath}/HomeController" data-active="1">
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

    <input type="text" value="${keyWord}" placeholder="Search.." id="searchBar">

    <button class="button-add btn-search" style="margin-bottom: 1rem; width: fit-content">Search</button>

    <div>
        <table id="formUses" class="table-custom">
            <thead>
            <th>ID</th>
            <th>Name Form</th>
            <th>Unit Price</th>
            <th></th>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${formUseList.size() > 0}">
                    <c:forEach items="${formUseList}" var="formUse">
                        <tr>
                            <td>${formUse.getId()}</td>
                            <td contenteditable='true'>
                                    ${formUse.getNameForm()}
                            </td>
                            <td class="td-unit-price">
                                <input class="input-value-unit-price" type="number" step="any"
                                       value="${formUse.getUnitPrice()}" min="0"/>
                            </td>
                            <td>
                                <button class="button-update action-update-form-use"
                                        id-form-use-update="${formUse.getId()}">
                                    Update
                                </button>
                                <button class="button-delete action-delete-form-use"
                                        id-form-use-delete="${formUse.getId()}">
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
                <td class="td-unit-price">
                    <input class="input-value-unit-price" type="number" step="any"/>
                </td>
                <td>
                    <button class="button-add action-add-form-use">Add</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
<script type="text/javascript">

    $(document).ready(function () {

        $(".btn-search").on('click', function () {
            let keyWord = $("#searchBar").val();
            window.location.assign('${pageContext.request.contextPath}/FormUseController?keyWord=' + keyWord);
        })

        $("#formUses").on('click', '.action-delete-form-use', function () {

            let currentRow = $(this).closest("tr");
            let checkConfirm = confirm("Are you sure you want to delete !!!");
            let idDelete = $(this).attr("id-form-use-delete");
            if (checkConfirm) {
                $.ajax({
                    type: "DELETE",
                    url: '${pageContext.request.contextPath}/FormUseController',
                    contentType: 'application/json',
                    data: idDelete,
                    dataType: 'json',
                    success: function (data) {
                        alert("Delete success");
                        currentRow.remove();
                    },
                    error: function (data) {
                        alert("Delete failed");
                        window.scrollTo({top: 0, behavior: 'smooth'});
                    }
                })
            }
        })

        $(".action-add-form-use").on('click', function (event) {

            let currentRow = $(this).closest("tr");
            let nameFormUse = currentRow.find("td:eq(1)").text().trim();
            if (nameFormUse == "") {
                alert("Request enter Name Form !!!!");
                return;
            }
            let unitPrice = currentRow.find("td:eq(2) input[type='number']").val();
            if (unitPrice == "") {
                alert("Request enter Unit Price !!!");
                return;
            }
            if (Number(unitPrice) < 0) {
                alert("Request re-enter Unit Price !!!");
                return;
            }
            var myData = {
                "nameForm": nameFormUse,
                "unitPrice": unitPrice
            }
            let checkConfirm = confirm("Are you sure you want to add !!!");
            if (checkConfirm) {
                $.ajax({
                    type: "POST",
                    url: '${pageContext.request.contextPath}/FormUseController',
                    contentType: 'appication/json',
                    dataType: 'json',
                    data: JSON.stringify(myData),
                    success: function (result) {
                        alert("Add success");
                        window.location.href = "${pageContext.request.contextPath}/FormUseController";
                    },
                    error: function (textStatus) {
                        alert("Add failed");
                        window.scrollTo({top: 0, behavior: 'smooth'});
                    }
                })
            }
        })

        $("#formUses").on('click', '.action-update-form-use', function () {
            let currentRow = $(this).closest('tr');
            let idUpdate = $(this).attr('id-form-use-update');
            let nameFormUse = currentRow.find("td:eq(1)").text().trim();
            if (nameFormUse == "") {
                alert("Request enter Name Form !!!!");
                return;
            }
            let unitPrice = currentRow.find("td:eq(2) input[type='number']").val();
            if (unitPrice == "") {
                alert("Request enter Unit Price !!!");
                return;
            }
            if (Number(unitPrice) < 0) {
                alert("Request re-enter Unit Price !!!");
                return;
            }
            var myData = {
                "id": idUpdate,
                "nameForm": nameFormUse,
                "unitPrice": unitPrice
            }
            let checkConfirm = confirm("Are you sure you want to update !!!");
            if (checkConfirm) {
                $.ajax({
                    type: "PUT",
                    url: '${pageContext.request.contextPath}/FormUseController',
                    contentType: 'application/json',
                    data: JSON.stringify(myData),
                    success: function (result) {
                        alert("Update success");
                    },
                    error: function (data) {
                        alert("Update failed");
                        window.scrollTo({top: 0, behavior: 'smooth'});
                    }
                })
            }
        })
    })
</script>
</body>
</html>