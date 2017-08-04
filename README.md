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
#### 预览
![Home](http://jcalaz.github.io/img/jcala_blog.index.jpg)
![about](http://jcalaz.github.io/img/jcala_blog.about.jpg)

---
##### 一键黑夜模式
![dark about](http://jcalaz.github.io/img/jcala_blog.bkindex.jpg)
![dark blog](http://jcalaz.github.io/img/jcala_blog.bkpost.jpg)

---
##### 后台管理
![博客列表](http://jcalaz.github.io/img/jcala_blog.adlist.jpg)
![markdown编辑器](http://jcalaz.github.io/img/jcala_blog.adre.jpg)

#### 部署

- 打开deploy目录，可以看到jcalaBlog-1.0.0.jar。(也可以自己用gradle打成jar包，shell进入项目主目录，执行gradle clean build，在项目目录/build/libs目录下可看到打成的jar包)将jar上传到服务器/home/jcala目录

- 服务器安装mysql，进入mysql创建jcala_blog数据库。(不创建的话HikariCP数据库连接池有时会出现问题)

- 部署文件夹下的application-prod.yml配置文件为默认配置，运行时可以覆盖默认配置。例如运行:java -jar  --spring.datasource.username=root --spring.datasource.password=123 --spring.datasource.initialize=true
设置数据库用户名为root，数据库密码为123，运行时自动导入数据库表结构和数据

#### 配置文件
```yaml
server:
    port: 80 #博客端口号
    compression:
        min-response-size: 512 #压缩文件最小大小(kb)
        enabled: true #是否压缩
        mime-types: text/html,text/css,text/javascript,application/javascript,image/gif,image/png,image/jpg #要压缩的文件格式
    undertow:
        io-threads: 4 #io线程数
        worker-threads: 10 #工作线程数
        buffer-size: 16384 #每个缓冲的字节数
        accesslog:
            enabled: true #是否开启undertow日志
            dir: /home/jcala/blog/log/server_log #undertow日志目录
            pattern: combined #日志格式
logging:
    config: classpath:logback-spring.xml #logback配置文件
    path: /home/jcala/blog/log/spring_log #logback日志目录
spring:
    velocity:
        cache: true #velocity是否开启缓存
        charset: UTF-8
        view-names: error,index,projects,tags,tagView,archives,post,about,login,admin/blog_add,admin/project, 
                    admin/moniter,admin/blog_modify,admin/blog_list,admin/info,admin/result,admin/resume
                    #允许的vm名称，新建的vm文件要想使用必须在此注册
        properties:
          input.encoding: UTF-8
          output.encoding: UTF-8
          velocimacro.library: VM_global_library.vm #velocity的宏文件
          directive.parse.max.depth: 2 #parse解析的深度
    http:
        encoding.charset: UTF-8
        encoding.force: false
        multipart:
          max-file-size: 3MB #上传文件最大的大小，因此上传的图片不可超过这个大小，否则抛出异常
          max-request-size: 10MB
    cache:
        cache-names: ehcache #设置缓存的实现为ehcache
        ehcache:
            config: ehcache.xml #ehcache的配置文件
    datasource:
        type: com.zaxxer.hikari.HikariDataSource #数据库连接池为hikariCP
        username: root #数据库访问用户名
        password: root #数据库访问密码
        url: jdbc:mysql://127.0.0.1:3306/jcala_blog?useUnicode:true&amp;characterEncoding:UTF-8
        driverClassName: com.mysql.jdbc.Driver
        schema: classpath:import.sql #数据库表结构
        data: classpath:data.sql #数据库初始化数据
        initialize: false #是否初始化数据库。true的话则执行import.sql,data.sql sql语句，导入表结构和初始化数据
        sql-script-encoding: UTF-8 #导入的sql文件编码
        hikari:
            connection-timeout: 30000
            maximum-pool-size: 50 #数据库连接池最大连接数
            minimum-idle: 5 #初始连接池的连接数
mybatis:
    type-aliases-package: me.jcala.blog.domain,me.jcala.blog.mapping
    type-handlers-package: org.apache.ibatis.type.LocalDateTypeHandler
    config-location: classpath:mybatis-config.xml #mybatis配置文件
pic:
    home: G:\home\jcala\xmarket\pic #图片在服务器上存放的目录
```

#### 注意事项
1. 运行后中文显示乱码:在linux下,vim /etc/mysql/my.cnf 。在[mysqld]下添加character-set-server=utf8
2. 二次开发报错,没有getter,setter等方法:因为使用了lombok,需要idea或者Eclipse安装lombok插件
3. 运行后，后台管理初始用户名admin,密码admin

#### 优点
1. 响应式，前端适配手机
2. cdn加速,gzip压缩
3. 前端页面用Ehcache缓存加速
4. 一键黑夜模式
5. 事务处理

#### 2.0版本将要做出的改变
1. 用react.js将前端重写为单页面应用，去除模板引擎,前端模拟路由。服务器端只负责收发json数据。
2. 项目分模块，前后端分离，增强拓展性。
3. 加入钩子，方便自定义插件。
4. 前端模板化，可自定义定制主题。
5. 增强安全性。
6. jdk版本升级为java8。
7. 还会逐步提供评论，全文检索，项目托管等功能或者插件。
