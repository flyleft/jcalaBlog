$(document).ready(function() {
    var pos = $("#blog_content").offset().top;
    $("html,body").animate({scrollTop: pos}, 1000);
    switch_theme();
});
function switch_theme() {
    if(localStorage.blog_theme==undefined){
        localStorage.blog_theme="light";
    }
    if(localStorage.blog_theme=="dark"){
        var btn=document.getElementById("day-night");
        var themeFont=document.getElementById("day-night-name");
        var css=document.getElementById("theme-css");
        var md=document.getElementById("md-container");
        btn.className="fa fa-sun-o";
        themeFont.textContent="DAY";
        css.href="/css/theme-dark.css";
        if(md!=null){
            md.className="editormd-preview editormd-preview-theme-dark";
        }
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
        css.href="#";
        if(md!=null){
            md.className="editormd-preview";
        }
        localStorage.blog_theme="light";
    }else {
        btn.className="fa fa-sun-o";
        themeFont.textContent="DAY";
        css.href="/css/theme-dark.css";
        if(md!=null){
            md.className="editormd-preview editormd-preview-theme-dark";
        }
        localStorage.blog_theme="dark";
    }
}