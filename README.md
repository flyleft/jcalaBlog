## JcalaBlog个人博客网站

#### 所用技术: SpringBoot+SpringMVC+Mybatis+undertow+Docker+Spring Security+HikariCP+Velocity+Bootstrap

#### 参考项目及所用其他开源项目
   - [SpringBlog](https://github.com/Raysmond/SpringBlog)  
   - [markdown-plus](https://github.com/tylingsoft/markdown-plus)

#### 项目依赖
```groovy
dependencies {
  compile ("org.springframework.boot:spring-boot-starter-web:${springBootVersion}"){
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'
  }
  compile "org.springframework.boot:spring-boot-starter-undertow:${springBootVersion}"
  compile "org.springframework.boot:spring-boot-starter-velocity:${springBootVersion}"
  compile "org.springframework.boot:spring-boot-starter-test:${springBootVersion}"
  //--------------------数据库驱动----------------------------
  compile 'mysql:mysql-connector-java:5.1.38'
  //-------------------数据库连接池---------------------------
  compile 'com.zaxxer:HikariCP:2.4.5'
  //----------------------ORM------------------------------
  compile group: 'org.mybatis', name: 'mybatis', version:mybatisVersion
  compile group: 'org.mybatis', name: 'mybatis-spring', version:mybatisSpringVersion
}
```

##正在开发中。。。
