package me.jcala.blog.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/9.
 */
public class FileToolsTest {
    @Test
    public void testPath(){
        System.out.println(FileTools.isLinuxPath("/home/jcala/blog/static"));
    }
}
