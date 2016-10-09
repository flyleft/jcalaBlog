package me.jcala.blog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装反向代理服务器的路径信息
 */
@Getter
@Setter
@ToString
public class ReversePath {
    private String picFilePath;//图片文件所在基础路径,形如"/home/jcala/blog/static/img/"
    private String picUrlPath;//图片的链接基础路径,形如"http://127.0.0.1:8090/static/img/"
    private String cssUrlPath;//css文件链接基础路径,形如"http://127.0.0.1:8090/static/css/"
    private String jsUrlPath;//js文件链接基础路径,形如"http://127.0.0.1:8090/static/js/"
}
