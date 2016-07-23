package me.jcala.blog.entity;

/**
 * Created by jcala on 2016/7/18
 */
public class Tag {
    private String title;
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tag(String title, String href) {
        this.title = title;
        this.href = href;
    }

    public Tag() {
    }
}
