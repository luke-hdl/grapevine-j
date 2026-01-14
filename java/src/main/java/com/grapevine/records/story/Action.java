package com.grapevine.records.story;

import java.util.Date;

public class Action {
    protected String charName;
    protected Date actDate;
    protected boolean done;
    protected Date lastModified;

    public Action() {
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
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
        String sb = "Character Name: " + (charName != null ? charName : "") + "\r\n" +
                "Action Date: " + (actDate != null ? actDate.toString() : "") + "\r\n" +
                "Done: " + done + "\r\n" +
                "Last Modified: " + (lastModified != null ? lastModified.toString() : "") + "\r\n";
        return sb;
    }
}
