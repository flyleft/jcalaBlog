package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/9/16.
 */
@Controller
@RequestMapping("/admin")
public class AdminProjectCtrl {
     @GetMapping("/project")
    public String project(){
        return "admin/project";
     }
    @PostMapping("/addPro.action")
    public String addProject(Project project){
        System.out.println(project);
        return "admin/project";
    }
}
