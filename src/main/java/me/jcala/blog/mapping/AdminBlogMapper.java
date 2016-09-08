package me.jcala.blog.mapping;

import me.jcala.blog.domain.BlogView;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Administrator on 2016/9/3.
 */
@Repository
@Mapper
public interface AdminBlogMapper {
      @Insert({"insert into blog_view " ,
              "(date,title,article,tags,md) " ,
              "values(#{bv.date},#{bv.title}," ,
              "#{bv.article},#{bv.tags},#{bv.md})"})
      @SelectKey(before=false,keyProperty="bv.vid",resultType=Integer.class,statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
      int addBlog(@Param("bv") BlogView blogView) throws Exception;

      @Insert("insert into view_tag (name,vid) values(#{tn},#{id})")
      int addViewTag(@Param("tn") String tagName,@Param("id") int vid) throws Exception;

      @Insert("insert ignore into blog_tag set name = #{tg}")
      int addTag(@Param("tg") String tagName) throws Exception;

      @Select({
            "select title,tags,md",
              "from blog_view",
              "where vid = #{id}",
              "limit 1"
      })
      BlogView getBlogById(@Param("id") int id) throws Exception;

      @Update({
            "update blog_view",
              "set title = #{bv.title},",
              "tags = #{bv.tags},",
              "md = #{bv.md},",
              "article = #{bv.article}",
              "where vid = #{bv.vid}"
      })
      void updateBlogById(@Param("bv") BlogView blogView) throws Exception;

      @Select({
              "select vid,title,tags",
              "from blog_view",
              "limit 10"
      })
      List<BlogView> getBlogList() throws Exception;

}
