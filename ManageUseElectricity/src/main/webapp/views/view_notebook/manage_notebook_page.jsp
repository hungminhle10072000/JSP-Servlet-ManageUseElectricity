<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage NoteBook</title>
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
                <a href="${pageContext.request.contextPath}/NoteBookController" class="active" data-active="0">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All NoteBook</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="1">
                <a href="${pageContext.request.contextPath}/NoteBookController?typePage=addNoteBookPage"
                   data-active="1">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">Add NoteBook</span>
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
    <h1 class="banner">Manage NoteBook</h1>

    <input type="text" placeholder="Search.." id="searchBar">

    <div>
        <table id="electrics" class="table-custom">
            <tr>
                <th>ID</th>
                <th>Index Electric</th>
                <th>Date Write</th>
                <th>Electric</th>
                <th></th>
            </tr>
            <c:if test="${noteBookList.size() > 0}">
                <c:forEach items="${noteBookList}" var="notebook">
                    <tr>
                        <td>${notebook.id}</td>
                        <td>${notebook.indexElectric}</td>
                        <td>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${notebook.dateWrite}"/>
                        </td>
                        <td>${notebook.getElectricMeter().getId()}</td>
                        <td>
                            <button
                                    class="button-update btn-detail-notebook" id-notebook-detail="${notebook.id}">
                                Detail
                            </button>
                            <button class="button-delete btn-delete-notebook" id-notebook-delete="${notebook.id}">
                                Delete
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#electrics").on('click', '.btn-delete-notebook', function () {

            let currentRow = $(this).closest("tr");
            let checkConfirm = confirm("Are you sure you want to delete !!!");
            let idDelete = $(this).attr("id-notebook-delete");
            if (checkConfirm) {
                $.ajax({
                    type: "DELETE",
                    url: '${pageContext.request.contextPath}/NoteBookController',
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

        $("#electrics").on('click', '.btn-detail-notebook', function () {
            let idDetail = $(this).attr("id-notebook-detail");
            window.location.assign('${pageContext.request.contextPath}/NoteBookController?typePage=detailNoteBookPage&idDetail=' + idDetail);
        })
    })
</script>
</body>
</html>
