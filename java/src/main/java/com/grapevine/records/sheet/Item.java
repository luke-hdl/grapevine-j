package com.grapevine.records.sheet;

import java.util.Date;
import java.util.List;

public class Item {
    protected String name;
    protected String itemType;
    protected String itemSubtype;
    protected int level;
    protected int bonus;
    protected String damageType;
    protected int damageAmount;
    protected String concealability;
    protected List<Trait> temperList;
    protected List<Trait> negativeList;
    protected List<Trait> abilityList;
    protected String powers;
    protected List<Trait> availability;
    protected String appearance;
    protected String notes;
    protected Date lastModified;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemSubtype() {
        return itemSubtype;
    }

    public void setItemSubtype(String itemSubtype) {
        this.itemSubtype = itemSubtype;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public int getDamageAmount() {
        return damageAmount;
    }

    public void setDamageAmount(int damageAmount) {
        this.damageAmount = damageAmount;
    }

    public String getConcealability() {
        return concealability;
    }

    public void setConcealability(String concealability) {
        this.concealability = concealability;
    }

    public List<Trait> getTemperList() {
        return temperList;
    }

    public void setTemperList(List<Trait> temperList) {
        this.temperList = temperList;
    }

    public List<Trait> getNegativeList() {
        return negativeList;
    }

    public void setNegativeList(List<Trait> negativeList) {
        this.negativeList = negativeList;
    }

    public List<Trait> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<Trait> abilityList) {
        this.abilityList = abilityList;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public List<Trait> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Trait> availability) {
        this.availability = availability;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name != null ? name : "").append("\r\n");
        sb.append("Item Type: ").append(itemType != null ? itemType : "").append("\r\n");
        sb.append("Item Subtype: ").append(itemSubtype != null ? itemSubtype : "").append("\r\n");
        sb.append("Level: ").append(level).append("\r\n");
        sb.append("Bonus: ").append(bonus).append("\r\n");
        sb.append("Damage Type: ").append(damageType != null ? damageType : "").append("\r\n");
        sb.append("Damage Amount: ").append(damageAmount).append("\r\n");
        sb.append("Concealability: ").append(concealability != null ? concealability : "").append("\r\n");
        
        if (temperList != null && !temperList.isEmpty()) {
            sb.append("Temper Traits:\r\n");
            for (Trait trait : temperList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        if (negativeList != null && !negativeList.isEmpty()) {
            sb.append("Negative Traits:\r\n");
            for (Trait trait : negativeList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        if (abilityList != null && !abilityList.isEmpty()) {
            sb.append("Abilities:\r\n");
            for (Trait trait : abilityList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        sb.append("Powers: ").append(powers != null ? powers : "").append("\r\n");
        
        if (availability != null && !availability.isEmpty()) {
            sb.append("Availability:\r\n");
            for (Trait trait : availability) {
                sb.append(trait.toDetailedString());
            }
        }
        
        sb.append("Appearance: ").append(appearance != null ? appearance : "").append("\r\n");
        sb.append("Notes: ").append(notes != null ? notes : "").append("\r\n");
        sb.append("Last Modified: ").append(lastModified != null ? lastModified.toString() : "").append("\r\n");
        return sb.toString();
    }
}
