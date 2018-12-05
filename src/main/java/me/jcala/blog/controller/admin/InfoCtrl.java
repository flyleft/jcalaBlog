package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Info;
import me.jcala.blog.service.inter.InfoSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台管理中个人信息管理页面
 * 包括对修改密码，更改用户名，邮箱，twitter，github地址以及简历
 * 更改用户名，邮箱，twitter，github地址后，前端显示也会随之改变
 * 此页面中所有处理请求的前缀为/admin，比如info方法匹配的url为/admin/info
 */
@Controller
public class InfoCtrl {

    private InfoSer infoSer;

    @Autowired
    public InfoCtrl(InfoSer infoSer) {
        this.infoSer = infoSer;
    }

    @GetMapping("/admin/info")
    public String info(Model model) {
        Info info = infoSer.getInfo();
        model.addAttribute("info", info);
        return "admin/info";
    }

    @PostMapping("/admin/info.action")
    public String updateInfo(@ModelAttribute("infoForm") Info info, Model model) {
        boolean result = infoSer.updateInfo(info);
        model.addAttribute("targetUrl", "/admin/info");
        if (result) {
            model.addAttribute("result", 1);
            return "admin/result";
        } else {
            model.addAttribute("result", 0);
            return "admin/result";
        }
    }

    @PostMapping("/admin/pass.action")
    public String passModify(@RequestParam String oldPass, @RequestParam String newPass, HttpServletRequest request) {
        int result = infoSer.modifyPw(oldPass, newPass);
        if (result == 0) {
            infoSer.destroySession(request);
        }
        return "redirect:/admin/info?result=" + result;
    }

    @GetMapping("/admin/resume")
    public String resume(Model model) {
        model.addAttribute("md", infoSer.getResumeMd());
        return "admin/resume";
    }

    @PostMapping("/admin/resume.action")
    public String resumeUpdate(@ModelAttribute("resumeForm") Info info) {
        infoSer.updateResume(info);
        return "redirect:/admin/resume";
    }
}
