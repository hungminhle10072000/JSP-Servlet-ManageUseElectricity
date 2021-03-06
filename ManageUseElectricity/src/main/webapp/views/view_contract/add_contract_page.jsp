<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Contract</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_home_page.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css_form_add_customer.css"/>
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
                <a href="${pageContext.request.contextPath}/ContractController" data-active="0">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">List All Contract</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="1">
                <a class="active" href="#"
                   data-active="1">
                    <div class="icon">
                        <i class='bx bx-folder'></i>
                        <i class='bx bxs-folder'></i>
                    </div>
                    <span class="link hide">Add Contract</span>
                </a>
            </li>
            <li class="tooltip-element" data-tooltip="2">
                <a href="${pageContext.request.contextPath}/HomeController" data-active="2">
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
                    <h3>H??ng</h3>
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
    <form method="post" action="${pageContext.request.contextPath}/ContractController" accept-charset="utf-8">
        <label for="contentContract">Content Contract</label>
        <textarea rows="4" cols="50" required
                  id="contentContract" name="contentContract" placeholder="Content contract.."></textarea>

        <label for="dateSign">Date Sign</label>
        <input type="date" id="dateSign" name="dateSign" required>

        <div class="div-selects">
            <div>
                <label for="selectCustomer" style="margin-bottom: 2rem;">Select Customer</label>
                <div class="custom-select" style="width:200px;">
                    <select name="selectCustomer" id="selectCustomer" required>
                        <option value="0">Select Customer</option>
                        <c:if test="${customerList.size() > 0}">
                            <c:forEach items="${customerList}" var="customer">
                                <option value="${customer.getId()}">${customer.getId()} - ${customer.getName()}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div>

            <div>
                <label style="margin-bottom: 2rem;">Select Branch</label>
                <div class="custom-select" style="width:200px;">
                    <select name="selectBranch" id="selectBranch" required>
                        <option value="0">Select Brand</option>
                        <c:if test="${branchList.size() > 0}">
                            <c:forEach items="${branchList}" var="branch">
                                <option value="${branch.getId()}">${branch.getId()} - ${branch.getNameBranch()}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div>

            <div>
                <label style="margin-bottom: 2rem;">Select Form Use</label>
                <div class="custom-select" style="width:200px;">
                    <select id="selectFormUse" name="selectFormUse" required>
                        <option value="0">Select Form Use</option>
                        <c:if test="${formUseList.size() > 0}">
                            <c:forEach items="${formUseList}" var="formUse">
                                <option value="${formUse.getId()}">${formUse.getId()}
                                    - ${formUse.getNameForm()}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div>
        </div>
        <input type="submit" value="Submit" id="btnSubmit">
    </form>
</main>
<script src="${pageContext.request.contextPath}/resources/js/js_home_page.js"></script>
<script>

    $(document).ready(function () {
        $("#btnSubmit").click(function (event) {
            let checkConfirm = confirm("Are you sure you want to create contract !!!");
            if (checkConfirm) {
                let valueSelectCustomer = $('#selectCustomer').val();
                let valueSelectBranch = $('#selectBranch').val();
                let valueSelectFormUse = $('#selectFormUse').val();
                if (valueSelectCustomer == 0) {
                    alert("Request choose customer !!!");
                    event.preventDefault();
                    return;
                }
                if (valueSelectBranch == 0) {
                    alert("Request choose branch !!!");
                    event.preventDefault();
                    return;
                }
                if (valueSelectFormUse == 0) {
                    alert("Request choose form use !!!");
                    event.preventDefault();
                    return;
                }
            } else {
                event.preventDefault();
            }
        })
    });

    var x, i, j, l, ll, selElmnt, a, b, c;
    /*look for any elements with the class "custom-select":*/
    x = document.getElementsByClassName("custom-select");
    l = x.length;
    for (i = 0; i < l; i++) {
        selElmnt = x[i].getElementsByTagName("select")[0];
        ll = selElmnt.length;
        /*for each element, create a new DIV that will act as the selected item:*/
        a = document.createElement("DIV");
        a.setAttribute("class", "select-selected");
        a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
        x[i].appendChild(a);
        /*for each element, create a new DIV that will contain the option list:*/
        b = document.createElement("DIV");
        b.setAttribute("class", "select-items select-hide");
        for (j = 1; j < ll; j++) {
            /*for each option in the original select element,
            create a new DIV that will act as an option item:*/
            c = document.createElement("DIV");
            c.innerHTML = selElmnt.options[j].innerHTML;
            c.addEventListener("click", function (e) {
                /*when an item is clicked, update the original select box,
                and the selected item:*/
                var y, i, k, s, h, sl, yl;
                s = this.parentNode.parentNode.getElementsByTagName("select")[0];
                sl = s.length;
                h = this.parentNode.previousSibling;
                for (i = 0; i < sl; i++) {
                    if (s.options[i].innerHTML == this.innerHTML) {
                        s.selectedIndex = i;
                        h.innerHTML = this.innerHTML;
                        y = this.parentNode.getElementsByClassName("same-as-selected");
                        yl = y.length;
                        for (k = 0; k < yl; k++) {
                            y[k].removeAttribute("class");
                        }
                        this.setAttribute("class", "same-as-selected");
                        break;
                    }
                }
                h.click();
            });
            b.appendChild(c);
        }
        x[i].appendChild(b);
        a.addEventListener("click", function (e) {
            /*when the select box is clicked, close any other select boxes,
            and open/close the current select box:*/
            e.stopPropagation();
            closeAllSelect(this);
            this.nextSibling.classList.toggle("select-hide");
            this.classList.toggle("select-arrow-active");
        });
    }

    function closeAllSelect(elmnt) {
        /*a function that will close all select boxes in the document,
        except the current select box:*/
        var x, y, i, xl, yl, arrNo = [];
        x = document.getElementsByClassName("select-items");
        y = document.getElementsByClassName("select-selected");
        xl = x.length;
        yl = y.length;
        for (i = 0; i < yl; i++) {
            if (elmnt == y[i]) {
                arrNo.push(i)
            } else {
                y[i].classList.remove("select-arrow-active");
            }
        }
        for (i = 0; i < xl; i++) {
            if (arrNo.indexOf(i)) {
                x[i].classList.add("select-hide");
            }
        }
    }

    /*if the user clicks anywhere outside the select box,
    then close all select boxes:*/
    document.addEventListener("click", closeAllSelect);
</script>
</body>
</html>
