package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Project;
import me.jcala.blog.service.inter.ProjectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台管理中监控页面
 * 目前只包括内存占用的监控
 */
@Controller
@RequestMapping("/admin")
public class ProjectCtrl {

    private static final String PROJECT_ONE = "redirect:/admin/project/1";

    private ProjectSer projectSer;

    @Autowired
    public ProjectCtrl(ProjectSer projectSer) {
        this.projectSer = projectSer;
    }

    @GetMapping("/project/{page}")
    public String project(@PathVariable int page, Model model){
         model.addAttribute("current",page);
         model.addAttribute("pageNum",projectSer.adminGetPageNum());
         model.addAttribute("proList",projectSer.adminGetPros(page));
        return "admin/project";
     }
    @PostMapping("/addPro.action")
    public String addProject(Project project){
        projectSer.addPro(project);
        return PROJECT_ONE;
    }
    @GetMapping("/deletePro/{id}")
    public String deletePro(@PathVariable int id){
       projectSer.deletePro(id);
        return PROJECT_ONE;
    }
    @ResponseBody
    @GetMapping("/pro.json")
    public Project getProJson(HttpServletRequest request){
        String idStr=request.getParameter("id");
        return projectSer.getProById(idStr);
    }

    @PostMapping("/updPro.action")
    public String updatePro(@ModelAttribute("updateForm") Project project){
        projectSer.updatePro(project);
        return PROJECT_ONE;
    }
}
