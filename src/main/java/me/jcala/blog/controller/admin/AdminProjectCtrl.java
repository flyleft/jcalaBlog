package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Project;
import me.jcala.blog.service.ProjectSer;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProjectSer projectSer;
     @GetMapping("/project")
    public String project(){
        return "admin/project";
     }
    @PostMapping("/addPro.action")
    public String addProject(Project project){
        projectSer.savePro(project);
        return "redirect:/admin/project";
    }
}
