package me.jcala.blog.entity;

import java.util.List;

/**
 * Created by jcala on 2016/7/20
 */
public class ArchivesYear {
   private int YearNumber;
    private List<Archive> Archives;

    public ArchivesYear(int yearNumber, List<Archive> archives) {
        YearNumber = yearNumber;
        Archives = archives;
    }

    public ArchivesYear() {
    }

    public int getYearNumber() {
        return YearNumber;
    }

    public List<Archive> getArchives() {
        return Archives;
    }

    public static class Archive{
        private int month;
        private String href;
        private String title;

        public int getMonth() {
            return month;
        }

        public String getHref() {
            return href;
        }

        public String getTitle() {
            return title;
        }

        public Archive(int month, String href, String title) {
            this.month = month;
            this.href = href;
            this.title = title;
        }

        public Archive() {
        }
    }
}
