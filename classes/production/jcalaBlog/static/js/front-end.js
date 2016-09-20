$(document).ready(function() {
    var pos = $("#blog_content").offset().top;
    $("html,body").animate({scrollTop: pos}, 1000);
});
function day_night() {
    var ele=document.getElementById("day-night");
    if (ele.className=="fa fa-sun-o"){
        ele.className="fa fa-moon-o";
        document.getElementById("day-night-name").textContent="NIGHT";
    }else {
        ele.className="fa fa-sun-o";
        document.getElementById("day-night-name").textContent="DAY";
    }
}