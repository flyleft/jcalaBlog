$(document).ready(function() {
    switch_theme_others();
    var pos = $("#blog_content").offset().top;
    $("html,body").animate({scrollTop: pos}, 1000);
});
function switch_theme_others() {
    if(localStorage.blog_theme=="dark"){
        var md=document.getElementById("md-container");
        if(md!=null){
            md.className="editormd-preview editormd-preview-theme-dark";
        }
        var btn=document.getElementById("day-night");
        var themeFont=document.getElementById("day-night-name");
        btn.className="fa fa-sun-o";
        themeFont.textContent="DAY";
    }
}
function day_night() {
    var btn=document.getElementById("day-night");
    var md=document.getElementById("md-container");
    var themeFont=document.getElementById("day-night-name");
    var css=document.getElementById("theme-css");
    if (btn.className=="fa fa-sun-o"){
        btn.className="fa fa-moon-o";
        themeFont.textContent="NIGHT";
        css.href="/css/landing-page.css";
        if(md!=null){
            md.className="editormd-preview";
        }
        localStorage.blog_theme="light";
    }else {
        btn.className="fa fa-sun-o";
        themeFont.textContent="DAY";
        css.href="/css/landing-page-dark.css";
        if(md!=null){
            md.className="editormd-preview editormd-preview-theme-dark";
        }
        localStorage.blog_theme="dark";
    }
}