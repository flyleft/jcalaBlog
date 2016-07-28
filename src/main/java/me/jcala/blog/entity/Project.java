package me.jcala.blog.entity;

import lombok.*;

import java.util.Date;

/**
 * Created by jcala on 2016/7/20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String Name;
    private String ShowHref;
    private String OpenSourceHref;
    private Date ProjectDate;
    private String Summary;
    private String Technology;
}
