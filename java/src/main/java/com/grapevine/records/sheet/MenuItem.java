package com.grapevine.records.sheet;

public class MenuItem {
    protected Menu menu;
    protected String name;
    protected String powerLevel;
    protected String cost;

    public MenuItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(String powerLevel) {
        this.powerLevel = powerLevel;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String toDetailedString() {
        String sb = "Name: " + (name != null ? name : "") + "\r\n" +
                "Power Level: " + (powerLevel != null ? powerLevel : "") + "\r\n" +
                "Cost: " + (cost != null ? cost : "") + "\r\n";
        return sb;
    }

    public Trait toTrait() {
        Trait trait = new Trait();
        trait.name = this.name;
        trait.total = "1";
        trait.note = menu.name;
        return trait;
    }
}
