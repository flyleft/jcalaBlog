package me.jcala.blog.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
@Controller
public class UserCtrl {
    @GetMapping("/about")
    public String about(Model model) {
        List<String> aboutMe=new ArrayList<>();
        aboutMe.add("我编程不仅仅为了挣钱，更因为我喜欢编程.");
        aboutMe.add("2015年在我大二下半学期正式开始学习编程，但我学习相当快，学到了非常多的东西.");
        aboutMe.add("Java是我最常用的编程语言，但scala是我最喜爱的，Java+scala+Go/Rust将是我的语言组合.");
        aboutMe.add("JAVAEE方面我喜欢用的技术有Netty,Gralde,SpringBoot,SpringMVC,Spring IOC,Mybatis,Shiro,Ecache,Velocity,Docker,MySQL,Bootstrap...");
        aboutMe.add("我也知道如何去用Tomcat,Json,JS,Jetty,Mongodb,Git,Linux,Velocity,HTML/CSS,AKKA,Android,Play!,Spring Data Mongo,Revel,Guava.");
        aboutMe.add("能灵活利用设计模式，会JVM性能调优，擅长使用Intellij IDEA,用Jrebel做热部署，Jmeter做压力测试，擅长并发和多线程编程.");
        aboutMe.add("爱好源码分析，深入分析过Spring和Jfinal.");
        aboutMe.add("Dobbo,Zookeeper,Reactjs以及复杂算法是我将要学习的技术.");
        model.addAttribute("aboutMyself",aboutMe);
        List<String> experience=new ArrayList<>();
        experience.add("在学习java一个月，刚开始学习安卓一星期的情况下，两星期内帮助朋友完成省级srtp安卓项目.");
        experience.add("完成基于注解的简易框架<a href=\"https://github.com/jcalaz/janser\">janser</a>,为了快速开发基于基于Servlet的HTTP服务.");
        experience.add("作为组长带领组员完成'基于安卓平台的聚会社交软件'省级srtp项目,并完成项目的大部分编码任务.");
        experience.add("开源了用jsoup在安卓客户端爬去教务网课程信息的迷你项目<a href=\"https://github.com/jcalaz/SpiderForSchool\">SpiderForSchool</a>.");
        experience.add("加入工作室，将工作室服务器Beego后端转为用Netty5+Mybatis+Spring Data Mongo+HikariCP+Ehcache+Logback+Gson,实现HTTP和Websocket服务，" +
                "并将项目骨架(包含路由,Ehcache模拟session,从http获取真实IP等)<a href=\"https://github.com/jcalaz/nettyServer\">nettyServer</a></M>开源.");
        experience.add("运用Spring Boot,Spring MVC,Mybatis,Spring Undertow,Velocity,Bootstrap,HikariCP,Mysql,Spring Security,Ecache等完成本网站，" +
                "即JcalaBlog个人博客网站的全部开发");
        experience.add("正在实现一个完整的编程语言来提高编程能力，实现一门JVM上的语言，目前用scala语言实现了<a href=\"https://github.com/jcalaz/zuyi\">分词器</a>");
        model.addAttribute("experience",experience);
        return "about";
    }
    @GetMapping("login")
    public String login(){
        return "admin/login";
    }

}
