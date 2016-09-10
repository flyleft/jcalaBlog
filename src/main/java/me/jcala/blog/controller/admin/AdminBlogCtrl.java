package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.service.AdminBlogSer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private AdminBlogSer adminBlogSer;
    @GetMapping("/")
    public String moniter() {
        return "admin/moniter";
    }
    @GetMapping("/newBlog")
    public String newBlogBefore() {
        return "admin/newBlog";
    }
    @GetMapping("/update{id:\\d+}")
    public String blogRevise(@PathVariable int id,Model model) {
        BlogView blogView=adminBlogSer.getBlogByVid(id);
        if (blogView==null){
            return "error";
        }else {
            blogView.setVid(id);
            model.addAttribute("blog",blogView);
            return "admin/blogRevise";
        }
    }
    @PostMapping("/post")
    public String post(BlogView view,Model model) {
        boolean result=true;
        if (result){
            model.addAttribute("targetUrl","/admin/blogSet/1");
            model.addAttribute("result",1);
        }else {
            model.addAttribute("targetUrl","/admin/newBlog");
            model.addAttribute("result",0);
        }
        return "admin/result";

    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,BlogView view,Model model) {
        view.setVid(id);
        boolean result= adminBlogSer.updateBlog(view);
        if (result){
            model.addAttribute("targetUrl","/admin/blogSet/1");
            model.addAttribute("result",1);
        }else {
            model.addAttribute("targetUrl","/admin/update/"+id);
            model.addAttribute("result",0);
        }
        return "admin/result";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {

        return "redirect:/admin/blogSet/1";
    }
    @GetMapping("/blogSet/{id}")
    public String blogSet(@PathVariable int id, Model model) {
        model.addAttribute("currentPageId",id);
        model.addAttribute("pageNum",adminBlogSer.getPageNum());
        model.addAttribute("blogList",adminBlogSer.getBlogPage(id));
        return "admin/blogSet";
    }
}
