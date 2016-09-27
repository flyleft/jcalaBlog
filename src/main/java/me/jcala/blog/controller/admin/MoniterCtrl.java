package me.jcala.blog.controller.admin;

import me.jcala.blog.service.inter.MoniterSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/13.
 */
@Controller
public class MoniterCtrl {
    @Autowired
    private MoniterSer moniterSer;
    @GetMapping("/admin")
    public String moniter(Model model, HttpServletRequest request) throws Exception {
/*        List<Visiter> visiters=moniterSer.getVisiters();
        Collections.reverse(visiters);
        model.addAttribute("VsiterList",visiters);*/
        model.addAttribute("freeMemory",moniterSer.getFreeMemery());
        return "admin/moniter";
    }
}
