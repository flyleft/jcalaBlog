package me.jcala.blog.exception;

/**
 * Created by Administrator on 2016/9/27.
 */
public class BlogNotNullException extends RuntimeException {
    public BlogNotNullException() {
        super();
    }

    public BlogNotNullException(String message) {
        super(message);
    }
}
