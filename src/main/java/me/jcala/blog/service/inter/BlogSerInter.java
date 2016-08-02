package me.jcala.blog.service.inter;

import me.jcala.blog.domain.ArchivesYear;
import me.jcala.blog.domain.Project;
import me.jcala.blog.domain.Tag;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface BlogSerInter {
    List<ArchivesYear> archives() throws Exception;
    List<String> aboutMe() throws Exception;
    List<String> experience() throws Exception;
    List<Project> projects() throws Exception;
    List<Tag> tags() throws Exception;
}
