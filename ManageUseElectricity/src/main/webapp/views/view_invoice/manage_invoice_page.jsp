<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Invoices</title>
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
                    <span class="link hide">List All Invoice</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="2">
                <a href="${pageContext.request.contextPath}/InvoiceController?typePage=exportPage" data-active="2">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">Export Invoice</span>
                </a>
            </li>
            <%--            <li class="tooltip-element" data-tooltip="3">--%>
            <%--                <a href="#" data-active="3">--%>
            <%--                    <div class="icon">--%>
            <%--                        <i class='bx bx-folder'></i>--%>
            <%--                        <i class='bx bxs-folder'></i>--%>
            <%--                    </div>--%>
            <%--                    <span class="link hide">Update Status</span>--%>
            <%--                </a>--%>
            <%--            </li>--%>
            <li class="tooltip-element" data-tooltip="4">
                <a href="${pageContext.request.contextPath}/HomeController" data-active="4">
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
    <h1 class="banner">Manage Invoice</h1>

    <c:if test="${alert != null}">
        <h1 style="color: red">${alert}</h1>
    </c:if>

    <form action="${pageContext.request.contextPath}/InvoiceController" method="get">
        <div style="display: inline; width: 50%">
            <div>
                <label for="monthFind">Month</label>
                <input value="${monthFind}" required type="number" id="monthFind" name="monthFind" min="1" max="12"/>
            </div>

            <div>
                <label for="yearFind">Year</label>
                <input value="${yearFind}" required type="number" id="yearFind" name="yearFind" min="2000"
                       max="<%= new java.util.Date().getYear() + 1900 %>"/>
            </div>
        </div>

        <div>
            <label for="selectCustomer" style="margin-bottom: 2rem;">Select Customer</label>
            <div class="custom-select" style="width:200px; display: inline">
                <select name="selectCustomer" id="selectCustomer" required style="height: 2rem !important;">
                    <option value="0">Select Customer</option>
                    <c:if test="${customerList.size() > 0}">
                        <c:forEach items="${customerList}" var="customer">
                            <option ${customer.id == idFind ? 'selected="selected"' : ''}
                                    value="${customer.getId()}">${customer.getId()} - ${customer.getName()}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>

        <button class="button-add btn-search" style="margin-top: 1rem ;margin-bottom: 1rem; width: fit-content">Search
        </button>
    </form>
    <div>
        <table id="invoices" class="table-custom">
            <tr>
                <th>ID</th>
                <th>From Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Total Index</th>
                <th>Total money</th>
                <th>Electric</th>
                <th></th>
            </tr>
            <c:if test="${invoiceList.size() > 0}">
                <c:forEach items="${invoiceList}" var="invoice">
                    <tr>
                        <td>${invoice.id}</td>
                        <td>
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${invoice.dateFrom}"/>
                        </td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${invoice.dateEnd}"/></td>
                        <td>
                            <c:if test="${invoice.statusPaid == false}">UnPaid</c:if>
                            <c:if test="${invoice.statusPaid == true}">Paided</c:if>
                        </td>
                        <td>${invoice.totalIndex}</td>
                        <td>
                            <fmt:setLocale value="vi_VN"/>
                            <fmt:formatNumber value="${invoice.totalMoney}" type="currency"/>
                        </td>
                        <td>${invoice.electricMeter.id}</td>
                        <td>
                            <c:if test="${invoice.statusPaid == false}">
                                <button class="button-update action-update-invoice"
                                        id-invoice-update="${invoice.getId()}">
                                    Update Paided
                                </button>
                            </c:if>
                            <c:if test="${invoice.statusPaid == true}">
                                <button class="button-update action-update-invoice"
                                        id-invoice-update="${invoice.getId()}">
                                    Update UnPaid
                                </button>
                            </c:if>
                            <button class="button-delete action-delete-invoice" id-invoice-delete="${invoice.getId()}">
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

        $("#invoices").on('click', '.action-delete-invoice', function () {

            let currentRow = $(this).closest("tr");
            let checkConfirm = confirm("Are you sure you want to delete !!!");
            let idDelete = $(this).attr("id-invoice-delete");
            if (checkConfirm) {
                $.ajax({
                    type: "DELETE",
                    url: '${pageContext.request.contextPath}/InvoiceController',
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

        $("#invoices").on('click', '.action-update-invoice', function () {

            let currentRow = $(this).closest("tr");
            let checkConfirm = confirm("Are you sure you want update !!!");
            let idUpdate = $(this).attr("id-invoice-update");
            if (checkConfirm) {
                $.ajax({
                    type: "PUT",
                    url: '${pageContext.request.contextPath}/InvoiceController',
                    contentType: 'application/json',
                    data: idUpdate,
                    dataType: 'json',
                    success: function (data) {
                        alert("Update success");
                        window.location.assign("${pageContext.request.contextPath}/InvoiceController");
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