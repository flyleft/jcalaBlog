var mdEditor;
$(function() {
    mdEditor = editormd("markdown-editor", {
        width   : "100%",
        height  : 900,
        syncScrolling : "single",
        path    : "../lib/",
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "./php/upload.php?test=dfdf",
        saveHTMLToTextarea : true
    });
    function themeSelect(id, themes, lsKey, callback)
    {
        var select = $("#" + id);
        localStorage[lsKey] = themes[0];
        for (var i = 0, len = themes.length; i < len; i ++)
        {
            var theme    = themes[i];
            var selected = (localStorage[lsKey] == theme) ? "selected=\"selected\"" : "";

            select.append("<option value=\"" + theme + "\"" + selected + ">" + theme + "</option>");
        }
        select.bind("change", function(){
            var theme = $(this).val();

            if (theme === "")
            {
                return false;
            }
            console.log("lsKey =>", lsKey, theme);
            localStorage[lsKey] = theme;
            callback(select, theme);
        });
        return select;
    }
    themeSelect("editormd-theme-select", editormd.themes, "theme", function($this, theme) {
        mdEditor.setTheme(theme);
    });

    themeSelect("editor-area-theme-select", editormd.editorThemes, "editorTheme", function($this, theme) {
        mdEditor.setCodeMirrorTheme(theme);
    });

    themeSelect("preview-area-theme-select", editormd.previewThemes, "previewTheme", function($this, theme) {
        mdEditor.setPreviewTheme(theme);
    });
});