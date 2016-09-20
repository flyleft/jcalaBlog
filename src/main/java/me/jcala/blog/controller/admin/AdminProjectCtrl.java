package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Project;
import me.jcala.blog.service.ProjectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     @GetMapping("/project/{page}")
    public String project(@PathVariable int page, Model model){
         model.addAttribute("current",page);
         model.addAttribute("pageNum",projectSer.getPageNum());
         model.addAttribute("proList",projectSer.adminGetPros(page));
        return "admin/project";
     }
    @PostMapping("/addPro.action")
    public String addProject(Project project){
        projectSer.savePro(project);
        return "redirect:/admin/project/1";
    }
}
