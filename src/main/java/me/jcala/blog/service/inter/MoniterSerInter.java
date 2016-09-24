package me.jcala.blog.service.inter;

import me.jcala.blog.domain.Visiter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public interface MoniterSerInter {
    List<Visiter> getVisiters();
    int getFreeMemery();
}
