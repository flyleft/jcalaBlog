package me.jcala.blog.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Md5ToolsTest {
    @Test
    public void createMd5Str(){
        System.out.println(Md5Tools.MD5("admin"));
    }
}
