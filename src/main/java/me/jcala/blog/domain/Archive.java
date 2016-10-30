package me.jcala.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Archive {
    private int year;
    private List<BlogView> list;
}
