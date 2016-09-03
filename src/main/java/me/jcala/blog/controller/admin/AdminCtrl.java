package me.jcala.blog.controller.admin;

import me.jcala.blog.service.AdminBlogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by jcala on 2016/7/17
 */
@Controller
@RequestMapping("/admin")
public class AdminCtrl {
    @Autowired
    private AdminBlogSer adminBlogSer;
    @GetMapping("/")
    public String moniter() {
        return "admin/moniter";
    }
    @GetMapping("/newBlog")
    public String newBlog() {
        return "admin/newBlog";
    }
    @PostMapping("/saveBlog")
    public ModelAndView saveBlog(String title,String tags,String article) {
        try {
            adminBlogSer.addBlog(title,tags,article);
        } catch (Exception e) {
           System.out.println("============="+e.getMessage());
        }
        return new ModelAndView("redirect:/admin/newBlog");
    }
    @GetMapping("/blogSet")
    public String blogSet() {
        return "admin/blogSet";
    }
    @GetMapping("/blogRevise")
    public String blogRevise() {
        return "admin/blogRevise";
    }
    @GetMapping("/info")
    public String info() {
        return "admin/info";
    }
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }
}
