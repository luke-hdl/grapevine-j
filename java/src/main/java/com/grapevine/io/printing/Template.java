package com.grapevine.io.printing;

public class Template {
    protected String name;
    protected boolean isCharacterSheet;

    public Template() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCharacterSheet() {
        return isCharacterSheet;
    }

    public void setCharacterSheet(boolean characterSheet) {
        isCharacterSheet = characterSheet;
    }

    public String toDetailedString() {
        String sb = "Name: " + (name != null ? name : "") + "\r\n" +
                "Is Character Sheet: " + isCharacterSheet + "\r\n";
        return sb;
    }
}
