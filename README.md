[demo  http://115.28.18.158/](http://115.28.18.158/)

---

[github地址](https://github.com/jcalaz/jcalaBlog)
## jcalaBlog个人博客网站
#### 所用技术:
 - springBoot 
 - springMVC: MVC框架
 - undertow: web容器
 - mybatis: ORM框架
 - velocity: 模板引擎
 - lombok: 工具
 - quartz: 定时任务
 - ehcache: 缓存
 - bootstrap: css/html框架
 - [editor.md: markdown编辑器](https://github.com/pandao/editor.md)
 - mysql:数据库
---
####预览
![Home](http://jcalaz.github.io/img/jcala_blog.index.jpg)
![about](http://jcalaz.github.io/img/jcala_blog.about.jpg)

---
#####一键黑夜模式
![dark about](http://jcalaz.github.io/img/jcala_blog.bkindex.jpg)
![dark blog](http://jcalaz.github.io/img/jcala_blog.bkpost.jpg)

---
#####后台管理
![博客列表](http://jcalaz.github.io/img/jcala_blog.adlist.jpg)
![markdown编辑器](http://jcalaz.github.io/img/jcala_blog.adre.jpg)
####分支:
1. master主分支： 基于docker方式部署，可以动态更换头像，可以上传图片，用nginx做反向代理服务器。
2. simple分支：基于jar包形式部署，不可以动态更改头像，不可以上传图片，不用niginx做反向代理服务器。

####部署
simple分支部署

- 下载simple分支。打开部署目录，可以看到jcalaBlog-0.1.0.jar。(也可以自己用gradle打成jar包，shell进入项目主目录，执行gradle clean build，在项目目录/build/libs目录下可看到打成的jar包)将jar上传到服务器/home/jcala目录

- 服务器安装mysql，进入mysql创建jcala_blog数据库。(不创建的话HikariCP数据库连接池有时会出现问题)

- 部署文件夹下的application-prod.yml配置文件为默认配置，运行时可以覆盖默认配置。例如运行:java -jar  --spring.datasource.username=root --spring.datasource.password=123 --spring.datasource.initialize=true
设置数据库用户名为root，数据库密码为123，运行时自动导入数据库表结构和数据

master分支部署
...

####优点
1. 响应式，前端适配手机
2. cdn加速,gzip压缩
3. 前端页面用Ehcache缓存加速
4. 一键黑夜模式
5. nginx反向代理加速
6. 事务处理

####可能逐步加入的功能
1. 项目代码托管
2. 全文检索
3. 云IDE
4. 后台管理适配手机客户端
