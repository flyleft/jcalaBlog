package me.jcala.blog.mapping;

import me.jcala.blog.domain.Visiter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public interface VisiterMapper {
    @Insert({
            "insert into visiter ",
            "set day = #{day},",
            "size = #{size}"
    })
    void insert(@Param("day") Date day,@Param("size") int size) throws Exception;

    @Select({
            "select day,size from visiter order by day desc limit 30"
    })
    List<Visiter> select() throws Exception;

    @Delete({
            "delete from visiter ",
            "where day < #{tm}"
    })
    void delete(@Param("tm") String twoMonthAgo) throws Exception;
}
