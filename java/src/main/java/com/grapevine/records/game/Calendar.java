package com.grapevine.records.game;

import java.util.Date;

public class Calendar {
    protected Date lastModified;

    public Calendar() {
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String toDetailedString() {
        String sb = "Last Modified: " + (lastModified != null ? lastModified.toString() : "") + "\r\n";
        return sb;
    }
}
