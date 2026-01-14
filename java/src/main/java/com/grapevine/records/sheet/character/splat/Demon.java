package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Demon extends Character {
    protected String house;
    protected String faction;
    protected int torment;
    protected int faith;
    protected int conscience;
    protected int conviction;
    protected int courage;
    protected int tempTorment;
    protected int tempFaith;
    protected int tempConscience;
    protected int tempConviction;
    protected int tempCourage;
    protected List<Trait> loreList;
    protected List<Trait> visageList;

    public Demon() {
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public int getTorment() {
        return torment;
    }

    public void setTorment(int torment) {
        this.torment = torment;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public int getConscience() {
        return conscience;
    }

    public void setConscience(int conscience) {
        this.conscience = conscience;
    }

    public int getConviction() {
        return conviction;
    }

    public void setConviction(int conviction) {
        this.conviction = conviction;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
    }

    public int getTempTorment() {
        return tempTorment;
    }

    public void setTempTorment(int tempTorment) {
        this.tempTorment = tempTorment;
    }

    public int getTempFaith() {
        return tempFaith;
    }

    public void setTempFaith(int tempFaith) {
        this.tempFaith = tempFaith;
    }

    public int getTempConscience() {
        return tempConscience;
    }

    public void setTempConscience(int tempConscience) {
        this.tempConscience = tempConscience;
    }

    public int getTempConviction() {
        return tempConviction;
    }

    public void setTempConviction(int tempConviction) {
        this.tempConviction = tempConviction;
    }

    public int getTempCourage() {
        return tempCourage;
    }

    public void setTempCourage(int tempCourage) {
        this.tempCourage = tempCourage;
    }

    public List<Trait> getLoreList() {
        return loreList;
    }

    public void setLoreList(List<Trait> loreList) {
        this.loreList = loreList;
    }

    public List<Trait> getVisageList() {
        return visageList;
    }

    public void setVisageList(List<Trait> visageList) {
        this.visageList = visageList;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("House: ").append(house != null ? house : "").append("\r\n");
        sb.append("Faction: ").append(faction != null ? faction : "").append("\r\n");
        sb.append("Torment: ").append(torment).append("\r\n");
        sb.append("Faith: ").append(faith).append("\r\n");
        sb.append("Conscience: ").append(conscience).append("\r\n");
        sb.append("Conviction: ").append(conviction).append("\r\n");
        sb.append("Courage: ").append(courage).append("\r\n");
        sb.append("Temp Torment: ").append(tempTorment).append("\r\n");
        sb.append("Temp Faith: ").append(tempFaith).append("\r\n");
        sb.append("Temp Conscience: ").append(tempConscience).append("\r\n");
        sb.append("Temp Conviction: ").append(tempConviction).append("\r\n");
        sb.append("Temp Courage: ").append(tempCourage).append("\r\n");

        if (loreList != null && !loreList.isEmpty()) {
            sb.append("Lore:\r\n");
            for (Trait trait : loreList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (visageList != null && !visageList.isEmpty()) {
            sb.append("Visage:\r\n");
            for (Trait trait : visageList) {
                sb.append(trait.toDetailedString());
            }
        }

        return sb.toString();
    }

}
