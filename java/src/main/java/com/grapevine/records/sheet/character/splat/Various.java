package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Various extends Character {
    protected String characterClass;
    protected String subclass;
    protected String affinity;
    protected String plane;
    protected String brood;
    protected List<Trait> temperList;
    protected List<Trait> healthList;
    protected List<Trait> powerList;
    protected String other;
    protected String biography;
    protected String notes;

    public Various() {
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getBrood() {
        return brood;
    }

    public void setBrood(String brood) {
        this.brood = brood;
    }

    public List<Trait> getTemperList() {
        return temperList;
    }

    public void setTemperList(List<Trait> temperList) {
        this.temperList = temperList;
    }

    public List<Trait> getHealthList() {
        return healthList;
    }

    public void setHealthList(List<Trait> healthList) {
        this.healthList = healthList;
    }

    public List<Trait> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<Trait> powerList) {
        this.powerList = powerList;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getBiography() {
        return biography;
    }

    @Override
    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Character Class: ").append(characterClass != null ? characterClass : "").append("\r\n");
        sb.append("Subclass: ").append(subclass != null ? subclass : "").append("\r\n");
        sb.append("Affinity: ").append(affinity != null ? affinity : "").append("\r\n");
        sb.append("Plane: ").append(plane != null ? plane : "").append("\r\n");
        sb.append("Brood: ").append(brood != null ? brood : "").append("\r\n");

        if (temperList != null && !temperList.isEmpty()) {
            sb.append("Temper:\r\n");
            for (Trait trait : temperList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (powerList != null && !powerList.isEmpty()) {
            sb.append("Powers:\r\n");
            for (Trait trait : powerList) {
                sb.append(trait.toDetailedString());
            }
        }

        sb.append("Other: ").append(other != null ? other : "").append("\r\n");

        return sb.toString();
    }
}
