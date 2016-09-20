$(function() {
    $("#add-blog").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/post.action";
        form.method = "post";
        document.blogForm.article.value=testEditor.getHTML();
        document.blogForm.md.value=testEditor.getMarkdown();
        form.submit();
    });
    $("#modify-blog").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/update/${blog.Vid}";
        form.method = "post";
        document.blogForm.article.value=testEditor.getHTML();
        document.blogForm.md.value=testEditor.getMarkdown();
        form.submit();
    });
    $("#update-resume").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/resume.action";
        form.method = "post";
        document.resumeForm.resume.value=testEditor.getHTML();
        document.resumeForm.md.value=testEditor.getMarkdown();
        form.submit();
    });
    $(document).on("click", "#add_project", function (e) {
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
});