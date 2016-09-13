package me.jcala.blog.controller.admin;

import me.jcala.blog.domain.Visiter;
import me.jcala.blog.service.MoniterSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
@Controller
public class MoniterCtrl {
    @Autowired
    private MoniterSer moniterSer;
    @GetMapping("/admin")
    public String moniter(Model model) {
        List<Visiter> visiters=moniterSer.getVisiters();
        Collections.reverse(visiters);
        model.addAttribute("VsiterList",visiters);
        return "admin/moniter";
    }
}
