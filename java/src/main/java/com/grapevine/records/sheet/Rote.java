package com.grapevine.records.sheet;

import java.util.Date;
import java.util.List;

public class Rote {
    protected String name;
    protected int level;
    protected String duration;
    protected List<Trait> sphereList;
    protected String description;
    protected String grades;
    protected Date lastModified;
    protected String iconKey;

    public Rote() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Trait> getSphereList() {
        return sphereList;
    }

    public void setSphereList(List<Trait> sphereList) {
        this.sphereList = sphereList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getIconKey() {
        return iconKey;
    }

    public void setIconKey(String iconKey) {
        this.iconKey = iconKey;
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name != null ? name : "").append("\r\n");
        sb.append("Level: ").append(level).append("\r\n");
        sb.append("Duration: ").append(duration != null ? duration : "").append("\r\n");
        if (sphereList != null && !sphereList.isEmpty()) {
            sb.append("Spheres:\r\n");
            for (Trait trait : sphereList) {
                sb.append(trait.toDetailedString());
            }
        }
        sb.append("Description: ").append(description != null ? description : "").append("\r\n");
        sb.append("Grades: ").append(grades != null ? grades : "").append("\r\n");
        sb.append("Icon Key: ").append(iconKey != null ? iconKey : "").append("\r\n");
        sb.append("Last Modified: ").append(lastModified != null ? lastModified.toString() : "").append("\r\n");
        return sb.toString();
    }
}
