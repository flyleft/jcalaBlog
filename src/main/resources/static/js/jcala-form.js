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
});