package me.jcala.blog.controller.admin;

import me.jcala.blog.service.inter.MoniterSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台管理中监控页面
 * 目前只包括内存占用的监控
 */
@Controller
public class MoniterCtrl {

    private MoniterSer moniterSer;

    @Autowired
    public MoniterCtrl(MoniterSer moniterSer) {
        this.moniterSer = moniterSer;
    }

    @GetMapping("/admin")
    public String moniter(Model model, HttpServletRequest request) throws RuntimeException {
        model.addAttribute("freeMemory",moniterSer.getFreeMemery());
        return "admin/moniter";
    }
}
