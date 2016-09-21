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
    function update_pro(id) {
        $.getJSON( "/admin/pro.json?id="+id, function(data){
            bootbox.dialog({
                    title: "Update Project",
                    message: '<div class="row">  ' +
                    '<div class="col-md-12"> ' +
                    '<form class="form-horizontal" id="add_pro_form"> ' +
                    '<input name="id" type="hidden" value="'+data.id+'">' +
                    '<div class="form-group"> ' +
                    '<label class="col-md-2 control-label">Name</label> ' +
                    '<div class="col-md-9"> ' +
                    '<input name="name" type="text" placeholder="project name" value="' +data.name+
                    '" class="form-control input-md"> ' +
                    '</div></div>' +
                    '<div class="form-group"> ' +
                    '<label class="col-md-2 control-label">Addr</label> ' +
                    '<div class="col-md-9"> ' +
                    '<input name="url" type="text" placeholder="project url" value="' +data.url+
                    '" class="form-control input-md"> ' +
                    '</div></div>' +
                    '<div class="form-group"> ' +
                    '<label class="col-md-2 control-label">Tech</label> ' +
                    '<div class="col-md-9"> ' +
                    '<textarea class="form-control" rows="3" name="tech" maxlength="220">' +data.tech+
                    '</textarea></div></div>' +
                    '<div class="form-group"> ' +
                    '<label class="col-md-2 control-label">Desc</label> ' +
                    '<div class="col-md-9"> ' +
                    '<textarea class="form-control" rows="5" name="desp">' +data.desp+
                    '</textarea></div></div>' +
                    '</form> </div>  </div>',
                    buttons: {
                        success: {
                            label: "Update",
                            className: "btn-success",
                            callback: function () {
                                var form =document.getElementById("add_pro_form");
                                form.action = "/admin/updPro.action";
                                form.method = "post";
                                form.submit();
                            }
                        }
                    }
                }
            );
        } );
    }
    $("#info_update").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/info.action";
        form.method = "post";
        form.submit();
    });
    $("#update_pass").bind('click', function(){
        var form = document.forms[1];
        form.action = "/admin/pass.action";
        form.method = "post";
        form.submit();
    });
    $("#add-blog").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/post.action";
        form.method = "post";
        document.blogForm.article.value=$(".markdown-body")[0].innerHTML;
        document.blogForm.md.value=mdEditor.getMarkdown();
        form.submit();
    });
    $("#update-blog").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/update.action";
        form.method = "post";
        document.blogForm.article.value=$(".markdown-body")[0].innerHTML;
        document.blogForm.md.value=mdEditor.getMarkdown();
        form.submit();
    });
    $("#update-resume").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/resume.action";
        form.method = "post";
        document.resumeForm.resume.value=$(".markdown-body")[0].innerHTML;
        document.resumeForm.md.value=mdEditor.getMarkdown();
        form.submit();
    });
    $(document).on("click", "#add_project", function () {
        bootbox.dialog({
                title: "New Project",
                message: '<div class="row">  ' +
                '<div class="col-md-12"> ' +
                '<form class="form-horizontal" id="add_pro_form"> ' +
                '<div class="form-group"> ' +
                '<label class="col-md-2 control-label">Name</label> ' +
                '<div class="col-md-9"> ' +
                '<input name="name" type="text" placeholder="project name" class="form-control input-md"> ' +
                '</div></div>' +
                '<div class="form-group"> ' +
                '<label class="col-md-2 control-label">Addr</label> ' +
                '<div class="col-md-9"> ' +
                '<input name="url" type="text" placeholder="project url" class="form-control input-md"> ' +
                '</div></div>' +
                '<div class="form-group"> ' +
                '<label class="col-md-2 control-label">Tech</label> ' +
                '<div class="col-md-9"> ' +
                '<textarea class="form-control" rows="3" name="tech" maxlength="220"></textarea> ' +
                '</div></div>' +
                '<div class="form-group"> ' +
                '<label class="col-md-2 control-label">Desc</label> ' +
                '<div class="col-md-9"> ' +
                '<textarea class="form-control" rows="5" name="desp"></textarea> ' +
                '</div></div>' +
                '</form> </div>  </div>',
                buttons: {
                    success: {
                        label: "Save",
                        className: "btn-success",
                        callback: function () {
                            var form =document.getElementById("add_pro_form");
                            form.action = "/admin/addPro.action";
                            form.method = "post";
                            form.submit();
                        }
                    }
                }
            }
        );
    });

    $(".upd_project").click(function () {
        var id=$(this).attr("name");
        update_pro(id);
    });

});