package com.grapevine.records.story;

import java.util.Date;

public class Rumor {
    protected String title;
    protected Date rumorDate;
    protected String multiKey;
    protected String multiMatch;
    protected boolean done;
    protected Date lastModified;

    public Rumor() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRumorDate() {
        return rumorDate;
    }

    public void setRumorDate(Date rumorDate) {
        this.rumorDate = rumorDate;
    }

    public String getMultiKey() {
        return multiKey;
    }

    public void setMultiKey(String multiKey) {
        this.multiKey = multiKey;
    }

    public String getMultiMatch() {
        return multiMatch;
    }

    public void setMultiMatch(String multiMatch) {
        this.multiMatch = multiMatch;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String toDetailedString() {
        String sb = "Title: " + (title != null ? title : "") + "\r\n" +
                "Rumor Date: " + (rumorDate != null ? rumorDate.toString() : "") + "\r\n" +
                "Multi Key: " + (multiKey != null ? multiKey : "") + "\r\n" +
                "Multi Match: " + (multiMatch != null ? multiMatch : "") + "\r\n" +
                "Done: " + done + "\r\n" +
                "Last Modified: " + (lastModified != null ? lastModified.toString() : "") + "\r\n";
        return sb;
    }
}
