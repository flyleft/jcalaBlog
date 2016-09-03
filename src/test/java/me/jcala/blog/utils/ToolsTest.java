package me.jcala.blog.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2016/9/3.
 */
public class ToolsTest {
    @Test
    public void testGetTagList(){
        String tagStr="java scala spring SpringBoot";
        System.out.println(Tools.getTagList(tagStr));
    }
}
