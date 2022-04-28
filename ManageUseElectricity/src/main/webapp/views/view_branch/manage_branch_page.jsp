<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Branch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_home_page.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css"/>
    <%--    <link rel="stylesheet"--%>
    <%--          href="${pageContext.request.contextPath}/resources_new/bootstrap-4.6.0-dist/css/bootstrap.css"/>--%>
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
                    <span class="link hide">List All Branch</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="1">
                <a href="${pageContext.request.contextPath}/views/home_admin_page.jsp" data-active="1">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">Go back</span>
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
    <h1 class="banner">Manage Branch</h1>

    <input type="text" placeholder="Search.." id="searchBar">

    <div>
        <table id="branchs">
            <tr>
                <th>ID</th>
                <th>Address</th>
                <th>Name</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${branchList.size() == 0}">
                    <h2>NO DATA</h2>
                </c:when>
                <c:when test="${branchList.size() > 0}">
                    <c:forEach items="${branchList}" var="branch">
                        <tr>
                            <td>${branch.getId()}</td>
                            <td contenteditable='true'>
                                    ${branch.getAddress()}
                            </td>
                            <td contenteditable='true'>
                                    ${branch.getNameBranch()}
                            </td>
                            <td>
                                <button class="button-update action-update-branch" id-branch-update="${branch.getId()}">
                                    Update
                                </button>
                                <button class="button-delete action-delete-branch" id-branch-delete="${branch.getId()}">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
            <tr class="row-add-branch">
                <td></td>
                <td contenteditable='true'></td>
                <td contenteditable='true'></td>
                <td>
                    <button class="button-add action-add-branch">Add</button>
                </td>
            </tr>
        </table>
    </div>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $(".action-add-branch").on('click', function (event) {

            let currentRow = $(this).closest('tr');
            let addressBranch = currentRow.find("td:eq(1)").text().trim();
            let nameBranch = currentRow.find("td:eq(2)").text().trim();

            var myData = {
                "address": addressBranch,
                "nameBranch": nameBranch
            }

            if (nameBranch == "") {
                alert("Request enter nameBranch !!!");
            } else {
                let checkConfirm = confirm("Are you sure you want to add !!!");

                if (checkConfirm == true) {
                    $.ajax({
                        type: "POST",
                        url: '${pageContext.request.contextPath}/branchController',
                        contentType: 'appication/json',
                        dataType: 'json',
                        data: JSON.stringify(myData),
                        success: function (result) {
                            console.log(result);
                            alert("Add success");

                            var tableBranch = document.getElementById('branchs');
                            var index = tableBranch.rows.length - 1;

                            row = tableBranch.insertRow(index);
                            cell0 = row.insertCell(0);
                            cell1 = row.insertCell(1);
                            $(cell1).attr('contenteditable', true);
                            cell2 = row.insertCell(2);
                            $(cell2).attr('contenteditable', true);
                            cell3 = row.insertCell(3);

                            cell0.innerHTML = result.id;
                            cell1.innerHTML = result.address;
                            cell2.innerHTML = result.nameBranch;
                            cell3.innerHTML =
                                '<button class="button-update action-update-branch" id-branch-update="' + result.id + '"> Update </button>' + " "
                                + '<button class="button-delete action-delete-branch" id-branch-delete="' + result.id + '"> Delete </button>';


                            currentRow.find("td:eq(1)").html("");
                            currentRow.find("td:eq(2)").html("");
                        },
                        error: function (data) {
                            alert("ADD failed");
                            window.scrollTo({top: 0, behavior: 'smooth'});
                        }
                    })
                } else {
                }
            }
        })

        $("#branchs").on('click', '.action-update-branch', function () {
            let currentRow = $(this).closest('tr');
            let idBranch = $(this).attr('id-branch-update');
            let addressBranch = currentRow.find("td:eq(1)").text().trim();
            let nameBranch = currentRow.find("td:eq(2)").text().trim();

            var myData = {
                "id": idBranch,
                "address": addressBranch,
                "nameBranch": nameBranch
            }

            if (nameBranch == "") {
                alert("Request enter nameBranch !!!");
            } else {

                let checkConfirm = confirm("Are you sure you want to update !!!");
                if (checkConfirm == true) {
                    $.ajax({
                        type: "PUT",
                        url: '${pageContext.request.contextPath}/branchController',
                        contentType: 'appication/json',
                        dataType: 'json',
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
            }
        })

        $("#branchs").on('click', '.action-delete-branch', function () {
            let currentRow = $(this).closest("tr");
            let checkConfirm = confirm("Are you sure you want to delete !!!");
            let idBranchDelete = $(this).attr("id-branch-delete");
            if (checkConfirm == true) {
                $.ajax({
                    type: "DELETE",
                    url: '${pageContext.request.contextPath}/branchController',
                    contentType: 'application/json',
                    data: idBranchDelete,
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
            } else {

            }
        })
    })
</script>
<%--<script src="${pageContext.request.contextPath}/resources_new/bootstrap-4.6.0-dist/js/bootstrap.min.js"></script>--%>
</body>
</html>