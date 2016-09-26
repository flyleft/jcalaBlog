package me.jcala.blog.exception;

/**
 * Created by Administrator on 2016/9/27.
 */
public enum  ErrorCode{
    BLOG_NULL("the blog data from form has some null value"),
    PRO_NULL("");
    private String description;
    private ErrorCode(String description){

    }
}
