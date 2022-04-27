var working = false;
$('.login').on('submit', function (e) {
    e.preventDefault();
    window.location.href = $("span").attr("urlBase") + "/views/home_login_page.jsp";
});