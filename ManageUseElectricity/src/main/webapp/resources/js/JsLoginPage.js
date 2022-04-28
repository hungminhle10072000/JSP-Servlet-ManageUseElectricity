var working = false;
$('.login').on('submit', function (e) {
    e.preventDefault();
    let valueUsername = document.getElementsByClassName('text-username')[0].value;
    let valuePassWord = document.getElementsByClassName('text-password')[0].value;
    if (valueUsername == "" || valuePassWord == "") {
        alert("Request enter username and password !!!!");
    } else {
        window.location.href = $("span").attr("urlBase") + "/views/home_admin_page.jsp";
    }
});