package com.grapevine.records.sheet;

import java.util.Date;

public class Boon {
    protected String charName;
    protected boolean isOwed;
    protected String boonType;
    protected Date boonDate;
    protected String description;

    public Boon() {
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public boolean isOwed() {
        return isOwed;
    }

    public void setOwed(boolean owed) {
        isOwed = owed;
    }

    public String getBoonType() {
        return boonType;
    }

    public void setBoonType(String boonType) {
        this.boonType = boonType;
    }

    public Date getBoonDate() {
        return boonDate;
    }

    public void setBoonDate(Date boonDate) {
        this.boonDate = boonDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toDetailedString() {
        String sb = "Character Name: " + (charName != null ? charName : "") + "\r\n" +
                "Is Owed: " + isOwed + "\r\n" +
                "Boon Type: " + (boonType != null ? boonType : "") + "\r\n" +
                "Boon Date: " + (boonDate != null ? boonDate.toString() : "") + "\r\n" +
                "Description: " + (description != null ? description : "") + "\r\n";
        return sb;
    }
}
