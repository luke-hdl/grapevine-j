package com.grapevine.records.sheet.experience;

public class Experience {
    protected float earned;
    protected float unspent;

    public Experience() {
    }

    public float getEarned() {
        return earned;
    }

    public void setEarned(float earned) {
        this.earned = earned;
    }

    public float getUnspent() {
        return unspent;
    }

    public void setUnspent(float unspent) {
        this.unspent = unspent;
    }

    public String toDetailedString() {
        String sb = "Earned: " + earned + "\r\n" +
                "Unspent: " + unspent + "\r\n";
        return sb;
    }
}
