package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.service.inter.BlogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jcala on 2016/7/17
 */
@Controller
@RequestMapping("/admin")
public class AdminBlogCtrl {
    @Autowired
    private BlogSer blogSer;
    @GetMapping("/blogAdd")
    public String blogAdd() {
        return "admin/blog_add";
    }
    @GetMapping("/update{id:\\d+}")
    public String blogModify(@PathVariable int id,Model model) {
        BlogView blogView=blogSer.adminGetBlog(id);
        if (blogView==null){
            return "error";
        }else {
            blogView.setVid(id);
            model.addAttribute("blog",blogView);
            return "admin/blog_modify";
        }
    }
    @PostMapping("/post.action")
    public String postAction(BlogView view,Model model){
        boolean result=blogSer.addBlog(view);
        if (result){
            model.addAttribute("targetUrl","/admin/blogList/1");
        }else {
            model.addAttribute("targetUrl","/admin/blog_add");
            model.addAttribute("result",0);
        }
        return "admin/result";

    }

    @PostMapping("/update.action")
    public String update(BlogView view) throws Exception{
        blogSer.updateBlog(view);
        return "redirect:/post/"+view.getVid();
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,Model model) {
        boolean result= blogSer.deleteBlogById(id);
        if (result){
            return "redirect:/admin/blogList/1";
        }else {
            model.addAttribute("targetUrl","/admin/blogList/1");
            model.addAttribute("result",0);
            return "admin/result";
        }
    }
    @GetMapping("/blogList/{page}")
    public String blogList(@PathVariable int page, Model model) {
        model.addAttribute("current",page);
        model.addAttribute("pageNum",blogSer.getPageNum());
        model.addAttribute("blogList",blogSer.getBlogPage(page));
        return "admin/blog_list";
    }
}
