package me.jcala.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by jcala on 2016/7/18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private String title;
    private String href;
}
