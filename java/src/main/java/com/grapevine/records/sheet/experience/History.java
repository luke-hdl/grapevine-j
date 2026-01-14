package com.grapevine.records.sheet.experience;

public class History {
    protected float earned;
    protected float unspent;
    protected String history;

    public History() {
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

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String toDetailedString() {
        String sb = "Earned: " + earned + "\r\n" +
                "Unspent: " + unspent + "\r\n" +
                "History: " + (history != null ? history : "") + "\r\n";
        return sb;
    }
}
