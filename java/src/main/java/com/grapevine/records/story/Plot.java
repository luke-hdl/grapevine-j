package com.grapevine.records.story;

import com.grapevine.records.sheet.Trait;

import java.util.Date;
import java.util.List;

public class Plot {
    protected String name;
    protected Date startDate;
    protected Date endDate;
    protected String outline;
    protected String narrator;
    protected Date lastModified;
    protected List<Trait> castList;

    public Plot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public List<Trait> getCastList() {
        return castList;
    }

    public void setCastList(List<Trait> castList) {
        this.castList = castList;
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name != null ? name : "").append("\r\n");
        sb.append("Start Date: ").append(startDate != null ? startDate.toString() : "").append("\r\n");
        sb.append("End Date: ").append(endDate != null ? endDate.toString() : "").append("\r\n");
        sb.append("Outline: ").append(outline != null ? outline : "").append("\r\n");
        sb.append("Narrator: ").append(narrator != null ? narrator : "").append("\r\n");
        
        if (castList != null && !castList.isEmpty()) {
            sb.append("Cast:\r\n");
            for (Trait trait : castList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        sb.append("Last Modified: ").append(lastModified != null ? lastModified.toString() : "").append("\r\n");
        return sb.toString();
    }
}
