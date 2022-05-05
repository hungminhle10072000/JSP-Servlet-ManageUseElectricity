<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage HouseHold Customer</title>
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
                <a href="${pageContext.request.contextPath}/CustomerController" data-active="0">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All Customer</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="1">
                <a href="#" class="active" data-active="1">
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
    <h1 class="banner">Manage HouseHold Customer</h1>

    <input type="text" placeholder="Enter keyword....." id="searchBar" value="${keyWord}">

    <button class="button-add btn-search" style="margin-bottom: 1rem; width: fit-content">Search</button>

    <div>
        <table id="customers" class="table-custom">
            <tr>
                <th>ID</th>
                <th>Address</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Indentity Card</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${listHouseHoldCustomer == null}">
                    <tr>
                        <td>No Data</td>
                        <td>No Data</td>
                        <td>No Data</td>
                        <td>No Data</td>
                        <td>No Data</td>
                        <td></td>
                    </tr>
                </c:when>
                <c:when test="${listHouseHoldCustomer != null}">
                    <c:forEach items="${listHouseHoldCustomer}" var="houseHold">
                        <tr>
                            <td>${houseHold.getId()}</td>
                            <td contenteditable='true'>${houseHold.getAddress()}</td>
                            <td contenteditable='true'>${houseHold.getName()}</td>
                            <td class="td-unit-price"><input class="input-value-unit-price input-number" type="number"
                                                             step="any"
                                                             value="${houseHold.getPhoneNumber()}" min="0"/></td>
                            <td class="td-unit-price"><input class="input-value-unit-price input-number" type="number"
                                                             step="any"
                                                             value="${houseHold.getIndentityCard()}" min="0"/></td>
                            <td>
                                <button class="button-update btn-update-house-hold"
                                        id-house-hold-update="${houseHold.getId()}">Update
                                </button>
                                <button class="button-delete btn-delete-house-hold"
                                        id-house-hold-delete="${houseHold.getId()}">Delete
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

    $(document).ready(function () {

        $("#customers").on('click', '.btn-delete-house-hold', function () {

            let currentRow = $(this).closest("tr");
            let checkConfirm = confirm("Are you sure you want to delete !!!");
            let idDelete = $(this).attr("id-house-hold-delete");
            if (checkConfirm) {
                $.ajax({
                    type: "DELETE",
                    url: '${pageContext.request.contextPath}/HouseHoldController',
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


        $("#customers").on('click', '.btn-update-house-hold', function () {
            let currentRow = $(this).closest('tr');
            let idUpdate = $(this).attr('id-house-hold-update');
            let address = currentRow.find("td:eq(1)").text().trim();
            if (address == "") {
                alert("Request enter address !!!!");
                return;
            }
            let name = currentRow.find("td:eq(2)").text().trim();
            if (name == "") {
                alert("Request enter name !!!!");
                return;
            }
            let phoneNumber = currentRow.find("td:eq(3) input[type='number']").val();
            if (phoneNumber == "") {
                alert("Request enter Phone Number !!!");
                return;
            }
            if (Number(phoneNumber) < 0) {
                alert("Request re-enter Phone Number !!!");
                return;
            }
            let indentityCard = currentRow.find("td:eq(4) input[type='number']").val();
            if (indentityCard == "") {
                alert("Request enter Indentity Card !!!");
                return;
            }

            let myData = {
                "id": idUpdate,
                "address": address,
                "name": name,
                "phoneNumber": phoneNumber,
                "indentityCard": indentityCard
            }

            let checkConfirm = confirm("Are you sure you want to update !!!");
            if (checkConfirm) {
                $.ajax({
                    type: "PUT",
                    url: '${pageContext.request.contextPath}/HouseHoldController',
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

        $(".btn-search").on('click', function () {
            let keyWord = $("#searchBar").val();
            window.location.assign('${pageContext.request.contextPath}/HouseHoldController?keyWord=' + keyWord);
        })
    })

</script>
</body>
</html>