package jcala.blog.controller;

import jcala.blog.entity.ArchivesYear;
import jcala.blog.entity.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jcala.blog.entity.Tag;
@Controller
public class BlogController {
    @GetMapping("/blogs")
    public String blogs() {
        return "blogs";
    }

    @GetMapping("/archives")
    public String archives(Model model){
        ArchivesYear.Archive archive2=new ArchivesYear.Archive(2,"","scala的AKKA使用指南");
        ArchivesYear.Archive archive1=new ArchivesYear.Archive(8,"","Spark与Hadoop");
        List<ArchivesYear.Archive> archives = new ArrayList<>();
        archives.add(archive1);
        archives.add(archive2);
        ArchivesYear archivesYear1=new ArchivesYear(2015,archives);
        ArchivesYear.Archive archive4=new ArchivesYear.Archive(1,"","Finagle使用指南");
        ArchivesYear.Archive archive3=new ArchivesYear.Archive(7,"","Guava教程");
        List<ArchivesYear.Archive> archives1 = new ArrayList<>();
        archives1.add(archive3);
        archives1.add(archive4);
        ArchivesYear archivesYear2=new ArchivesYear(2016,archives1);
        List<ArchivesYear> archivesYears=new ArrayList<>();
        archivesYears.add(archivesYear2);
        archivesYears.add(archivesYear1);
        model.addAttribute("archivesYears",archivesYears);
        return "archives";
    }

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

    @GetMapping("/projects")
    public String projects(Model model) {
        List<Project> projects=new ArrayList<>();
        Project project=new Project();
        project.setName("JcalaBlog");
        project.setShowHref("https://jcala.me/projects#jcalaBlog");
        project.setOpenSourceHref("https://github.com/jcalaz/jcalaBlog");
        project.setProjectDate(new Date());
        project.setSummary("SpringBlog is a very simple and clean-design blog system implemented with " +
                "Spring Boot. It is one of my learning projects to explore awesome features in Spring " +
                "Boot web programming. It is also the source code of my blog site ");
        project.setTechnology("Spring Boot/MVC/JPA + Hibernate + MySQL + Redis + Bootstrap + Jade");
        projects.add(project);
        model.addAttribute("projects",projects);
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        List<Tag> tags=new ArrayList<>();
        tags.add(new Tag("home","/#home"));
        tags.add(new Tag("blogs","/blogs"));
        tags.add(new Tag("projects","/projects"));
        tags.add(new Tag("about","/about"));
        model.addAttribute("tags",tags);
        return "tags";
    }
}
