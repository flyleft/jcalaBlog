package me.jcala.blog.controller.admin;

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
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jcala on 2016/7/17
 */
@Controller
@RequestMapping("/admin")
public class AdminCtrl {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCtrl.class);
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
    @PostMapping("/post")
    public ModelAndView post(String title,String tags,String article,String md) {
        int id=1;
        int blogId=-1;
        String errorInfo="";
        try {
             //blogId=adminBlogSer.addBlog(title,tags,article,md);
        } catch (Exception e) {
            id=11;
            LOGGER.error(e.getMessage());
            errorInfo=e.getMessage();
        }if (id==1){
            return new ModelAndView("redirect:/admin/result/1/"+blogId);
        }else {
            return new ModelAndView("redirect:/admin/result/11/"+errorInfo);
        }

    }
    @GetMapping("/blogSet")
    public String blogSet() {
        return "admin/blogSet";
    }
    @GetMapping("/blogRevise/{id}")
    public String blogRevise(@PathVariable int id) {
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
    @GetMapping("/result/{id}/{str}")
    public String result(@PathVariable int id, @PathVariable String str,Model model) {
        System.out.println("==============="+id);
        if(id<10){
            switch (id){
                case 1:model.addAttribute("targetUrl","/post/"+str);break;//添加博客成功
                case 2:model.addAttribute("targetUrl","/post/"+str);break;//修改博客成功
                case 3:model.addAttribute("targetUrl","/info");break;//修改admin信息成功
                default:model.addAttribute("targetUrl","/");
            }
            return "admin/suc";
        }else {
            model.addAttribute("errorInfo",str);
            switch (id){
                case 11:model.addAttribute("targetUrl","/admin/newBlog");break;//添加博客失败
                case 12:model.addAttribute("targetUrl","/admin/blogSet");break;//修改博客失败
                case 13:model.addAttribute("targetUrl","/info");break;//修改admin信息失败
                default:model.addAttribute("targetUrl","/");
            }
            return "admin/fail";
        }
    }
}
