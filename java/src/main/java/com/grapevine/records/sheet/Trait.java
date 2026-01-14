package com.grapevine.records.sheet;

public class Trait {
    protected String name;
    protected String total;
    protected String note;

    public Trait() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String toDetailedString() {
        String sb = "Name: " + (name != null ? name : "") + "\r\n" +
                "Total: " + (total != null ? total : "") + "\r\n" +
                "Note: " + (note != null ? note : "") + "\r\n";
        return sb;
    }
}
