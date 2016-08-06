package me.jcala.blog.service.inter;

import me.jcala.blog.domain.ArchivesYear;
import me.jcala.blog.domain.Project;
import me.jcala.blog.domain.Tag;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface BlogSerInter {
    List<ArchivesYear> archives();
    List<Project> projects();
    List<Tag> tags();
}
