package me.jcala.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by jcala on 2016/7/20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArchivesYear {
   private int YearNumber;
    private List<Archive> Archives;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Archive{
        private int month;
        private String href;
        private String title;
    }
}
