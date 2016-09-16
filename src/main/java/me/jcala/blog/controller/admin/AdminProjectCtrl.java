package me.jcala.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2016/9/16.
 */
@Controller
public class AdminProjectCtrl {
     @GetMapping("/admin/project")
    public String project(){
        return "admin/pro_add";
     }
}
