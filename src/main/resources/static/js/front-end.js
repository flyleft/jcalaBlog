$(document).ready(function() {
    var pos = $("#blog_content").offset().top;
    $("html,body").animate({scrollTop: pos}, 1000);
});
function day_night() {
    var btn=document.getElementById("day-night");
    var md=document.getElementById("md-container");
    var themeFont=document.getElementById("day-night-name");
    var css=document.getElementById("theme-css");
    if (btn.className=="fa fa-sun-o"){
        btn.className="fa fa-moon-o";
        themeFont.textContent="NIGHT";
        css.href="#";
        if(md!=null){
            md.className="editormd-preview";
        }
    }else {
        btn.className="fa fa-sun-o";
        themeFont.textContent="DAY";
        css.href="/css/theme-dark.css";
        if(md!=null){
            md.className="editormd-preview editormd-preview-theme-dark";
        }
    }
}