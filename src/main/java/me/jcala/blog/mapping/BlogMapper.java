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
public interface BlogMapper {
      @Select({
              "select vid,title,tags",
              "from blog_view",
              "limit #{st},10"
      })
      List<BlogView> selectTenBlogs(@Param("st") int start) throws Exception;

      @Select("select count(*) from blog_view")
      int selectBlogNum() throws Exception;

      @Select("select distinct name from view_tag")
      @ResultType(String.class)
      List<String> selectTags() throws Exception;

      @Select({"select vid,date,title",
              "from blog_view",
              "order by date desc",
              "limit #{st},12"})
      List<BlogView> selectArc(@Param("st") int start) throws Exception;

      @Select({
              "select title,tags,md",
              "from blog_view",
              "where vid = #{id}",
              "limit 1"
      })
      BlogView selectAdmin(@Param("id") int id) throws Exception;

      @Insert({"insert into blog_view " ,
              "(date,title,article,tags,md) " ,
              "values(#{bv.date},#{bv.title}," ,
              "#{bv.article},#{bv.tags},#{bv.md})"})
      @SelectKey(before=false,keyProperty="bv.vid",resultType=Integer.class,
              statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
      int insertBlog(@Param("bv") BlogView blogView) throws Exception;

      @Insert("insert ignore into view_tag (name,vid) values(#{tn},#{id})")
      int insertViewTag(@Param("tn") String tagName,@Param("id") int vid) throws Exception;

      @Delete("delete from view_tag where vid = #{vid}")
      int deleteViewTag(@Param("vid") int vid) throws Exception;

      @Delete("delete from blog_view where vid =#{vid} limit 1")
      int deleteBlogView(@Param("vid") int vid) throws Exception;

      @Update({
            "update blog_view",
              "set title = #{bv.title},",
              "tags = #{bv.tags},",
              "md = #{bv.md},",
              "article = #{bv.article}",
              "where vid = #{bv.vid}"
      })
      void updateBlogView(@Param("bv") BlogView blogView) throws Exception;

}
