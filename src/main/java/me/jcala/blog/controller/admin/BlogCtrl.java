package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.service.inter.BlogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理中博客管理页面
 * 包括对博客的增,删,改
 * 此页面中所有处理请求的前缀为/admin，比如blogAdd方法匹配的url为/admin/blogAdd
 * The type Blog ctrl.
 */
@Controller
@RequestMapping("/admin")
public class BlogCtrl {

    private BlogSer blogSer;

    @Autowired
    public BlogCtrl(BlogSer blogSer) {
        this.blogSer = blogSer;
    }

    /**
     *后台管理中添加博客页面的控制器
     *
     * @return templates下的admin/blog_add.vm页面
     */
    @GetMapping("/blogAdd")
    public String blogAdd() {
        return "admin/blog_add";
    }

    /**
     * 后台管理中修改博客页面的控制器
     * 由于markdown编辑器的问题，使用了正则匹配update地址，而不是/update/{id}的形式
     *
     * @param id    要修改的博客的id
     * @return      templates下的admin/blog_add.vm页面
     */
    @GetMapping("/update{id:\\d+}")
    public String blogModify(@PathVariable int id,Model model){
        BlogView blogView=blogSer.adminGetBlog(id);
        if (blogView==null){
            return "error";
        }else {
            blogView.setVid(id);
            model.addAttribute("blog",blogView);
            return "admin/blog_modify";
        }
    }

    /**
     * 添加博客的表单控制器
     *
     * @param view  表单中提交的博客信息,包括标题，标签，md页面，和md转成的html页面
     * @return      templates下的result页面，用于提示是否保存博客成功
     */
    @PostMapping("/post.action")
    public String postAction(@ModelAttribute("blogForm") BlogView view, Model model){
        boolean result=blogSer.addBlog(view);
        if (result){
            return "redirect:/admin/blogList/1";
        }else {
            model.addAttribute("targetUrl","/admin/blog_add");
            model.addAttribute("result",0);
            return "admin/result";
        }
    }

    /**
     * 更新博客的表单控制器
     *
     * @param view 表单中提交的博客信息,包括id,md页面，和md转成的html页面
     * @return     templates下的result页面，用于提示是否保存博客成功
     */
    @PostMapping("/update.action")
    public String update(@ModelAttribute("blogForm") BlogView view,Model model){
        boolean result=blogSer.updateBlog(view);
        if (result){
            model.addAttribute("targetUrl","/post/"+view.getVid());
            return "redirect:/post/"+view.getVid();
        }else {
            model.addAttribute("targetUrl","/admin/update"+view.getVid());
            model.addAttribute("result",0);
            return "admin/result";
        }
    }

    /**
     * 删除博客的控制器
     *
     * @param id    要删除的博客id
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,Model model){
        boolean result= blogSer.deleteBlogById(id);
        if (result){
            return "redirect:/admin/blogList/1";
        }else {
            model.addAttribute("targetUrl","/admin/blogList/1");
            model.addAttribute("result",0);
            return "admin/result";
        }
    }

    /**
     * 所有博客列表的显示界面.
     *
     * @param page  显示的为博客的第几页
     * @return      templates下的admin/blog_list.vm页面
     */
    @GetMapping("/blogList/{page}")
    public String blogList(@PathVariable int page, Model model) {
        model.addAttribute("current",page);
        model.addAttribute("pageNum",blogSer.adminGetPageNum());
        model.addAttribute("blogList",blogSer.getBlogPage(page));
        return "admin/blog_list";
    }
}
