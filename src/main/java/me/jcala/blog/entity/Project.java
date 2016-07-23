package me.jcala.blog.entity;

import java.util.Date;

/**
 * Created by jcala on 2016/7/20
 */
public class Project {
    private String Name;
    private String ShowHref;
    private String OpenSourceHref;
    private Date ProjectDate;
    private String Summary;
    private String Technology;

    public Project(String name, String showHref, String openSourceHref,
                   Date projectDate, String summary, String technology) {
        Name = name;
        ShowHref = showHref;
        OpenSourceHref = openSourceHref;
        ProjectDate = projectDate;
        Summary = summary;
        Technology = technology;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setShowHref(String showHref) {
        ShowHref = showHref;
    }

    public void setOpenSourceHref(String openSourceHref) {
        OpenSourceHref = openSourceHref;
    }

    public void setProjectDate(Date projectDate) {
        ProjectDate = projectDate;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public void setTechnology(String technology) {
        Technology = technology;
    }

    public Project() {
    }

    public String getName() {
        return Name;
    }

    public String getShowHref() {
        return ShowHref;
    }

    public String getOpenSourceHref() {
        return OpenSourceHref;
    }

    public Date getProjectDate() {
        return ProjectDate;
    }

    public String getSummary() {
        return Summary;
    }

    public String getTechnology() {
        return Technology;
    }
}
