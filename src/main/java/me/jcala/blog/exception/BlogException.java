package me.jcala.blog.exception;

/**
 * Created by Administrator on 2016/9/27.
 */
public class BlogException extends RuntimeException {
    public BlogException() {
        super();
    }

    public BlogException(Object obj) {
        super(obj.toString());
    }
}
