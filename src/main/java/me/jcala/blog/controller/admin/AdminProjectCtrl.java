package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Project;
import me.jcala.blog.service.ProjectSer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
         model.addAttribute("pageNum",projectSer.adminGetPageNum());
         model.addAttribute("proList",projectSer.adminGetPros(page));
        return "admin/project";
     }
    @PostMapping("/addPro.action")
    public String addProject(Project project){
        projectSer.savePro(project);
        return "redirect:/admin/project/1";
    }
    @GetMapping("/deletePro/{id}")
    public String deletePro(@PathVariable int id){
       projectSer.deletePro(id);
        return "redirect:/admin/project/1";
    }
    @ResponseBody
    @GetMapping("/pro.json")
    public Project getProJson(HttpServletRequest request){
        String idStr=request.getParameter("id");
        return projectSer.getProById(idStr);
    }

    @PostMapping("/updPro.action")
    public String updatePro(Project project){
        projectSer.updatePro(project);
        return "redirect:/admin/project/1";
    }
}
