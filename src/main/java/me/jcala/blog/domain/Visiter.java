package me.jcala.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Created by Administrator on 2016/9/13.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Visiter {
    private Date day;
    private int size;
}
