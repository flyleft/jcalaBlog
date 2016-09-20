$(function() {
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