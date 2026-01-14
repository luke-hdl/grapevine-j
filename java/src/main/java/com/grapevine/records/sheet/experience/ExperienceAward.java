package com.grapevine.records.sheet.experience;

public class ExperienceAward {
    protected String name;
    protected float change;
    protected String reason;
    protected boolean xp;

    public ExperienceAward() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isXp() {
        return xp;
    }

    public void setXp(boolean xp) {
        this.xp = xp;
    }

    public String toDetailedString() {
        String sb = "Name: " + (name != null ? name : "") + "\r\n" +
                "Change: " + change + "\r\n" +
                "Reason: " + (reason != null ? reason : "") + "\r\n" +
                "Is XP: " + xp + "\r\n";
        return sb;
    }
}
