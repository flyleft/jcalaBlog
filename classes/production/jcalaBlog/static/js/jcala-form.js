$(function() {
    $("#add-blog").bind('click', function(){
        var form = document.forms[0];
        form.action = "/admin/post.action";
        form.method = "post";
        document.blogForm.article.value=testEditor.getHTML();
        document.blogForm.md.value=testEditor.getMarkdown();
        form.submit();
    });
});