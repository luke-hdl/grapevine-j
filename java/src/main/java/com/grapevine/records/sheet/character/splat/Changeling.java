package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Changeling extends Character {
    protected String kith;
    protected String seeming;
    protected String house;
    protected String title;
    protected String legacies;
    protected String seelie;
    protected String unseelie;
    protected String court;
    protected String threshold;
    protected int glamour;
    protected int banality;
    protected int nightmare;
    protected int tempGlamour;
    protected int tempBanality;
    protected int tempNightmare;
    protected List<Trait> statusList;
    protected List<Trait> artList;
    protected List<Trait> realmList;
    protected String oaths;

    public Changeling() {
    }

    public String getKith() {
        return kith;
    }

    public void setKith(String kith) {
        this.kith = kith;
    }

    public String getSeeming() {
        return seeming;
    }

    public void setSeeming(String seeming) {
        this.seeming = seeming;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLegacies() {
        return legacies;
    }

    public void setLegacies(String legacies) {
        this.legacies = legacies;
    }

    public int getGlamour() {
        return glamour;
    }

    public void setGlamour(int glamour) {
        this.glamour = glamour;
    }

    public int getBanality() {
        return banality;
    }

    public void setBanality(int banality) {
        this.banality = banality;
    }

    public int getNightmare() {
        return nightmare;
    }

    public void setNightmare(int nightmare) {
        this.nightmare = nightmare;
    }

    public int getTempGlamour() {
        return tempGlamour;
    }

    public void setTempGlamour(int tempGlamour) {
        this.tempGlamour = tempGlamour;
    }

    public int getTempBanality() {
        return tempBanality;
    }

    public void setTempBanality(int tempBanality) {
        this.tempBanality = tempBanality;
    }

    public int getTempNightmare() {
        return tempNightmare;
    }

    public void setTempNightmare(int tempNightmare) {
        this.tempNightmare = tempNightmare;
    }

    public List<Trait> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Trait> statusList) {
        this.statusList = statusList;
    }

    public List<Trait> getArtList() {
        return artList;
    }

    public void setArtList(List<Trait> artList) {
        this.artList = artList;
    }

    public List<Trait> getRealmList() {
        return realmList;
    }

    public void setRealmList(List<Trait> realmList) {
        this.realmList = realmList;
    }

    public String getOaths() {
        return oaths;
    }

    public void setOaths(String oaths) {
        this.oaths = oaths;
    }

    public String getSeelie() {
        return seelie;
    }

    public void setSeelie(String seelie) {
        this.seelie = seelie;
    }

    public String getUnseelie() {
        return unseelie;
    }

    public void setUnseelie(String unseelie) {
        this.unseelie = unseelie;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Kith: ").append(kith != null ? kith : "").append("\r\n");
        sb.append("Seeming: ").append(seeming != null ? seeming : "").append("\r\n");
        sb.append("House: ").append(house != null ? house : "").append("\r\n");
        sb.append("Title: ").append(title != null ? title : "").append("\r\n");
        sb.append("Legacies: ").append(legacies != null ? legacies : "").append("\r\n");
        sb.append("Seelie: ").append(seelie != null ? seelie : "").append("\r\n");
        sb.append("Unseelie: ").append(unseelie != null ? unseelie : "").append("\r\n");
        sb.append("Court: ").append(court != null ? court : "").append("\r\n");
        sb.append("Threshold: ").append(threshold != null ? threshold : "").append("\r\n");
        sb.append("Glamour: ").append(glamour).append("\r\n");
        sb.append("Banality: ").append(banality).append("\r\n");
        sb.append("Nightmare: ").append(nightmare).append("\r\n");
        sb.append("Temp Glamour: ").append(tempGlamour).append("\r\n");
        sb.append("Temp Banality: ").append(tempBanality).append("\r\n");
        sb.append("Temp Nightmare: ").append(tempNightmare).append("\r\n");

        if (statusList != null && !statusList.isEmpty()) {
            sb.append("Status:\r\n");
            for (Trait trait : statusList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (artList != null && !artList.isEmpty()) {
            sb.append("Arts:\r\n");
            for (Trait trait : artList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (realmList != null && !realmList.isEmpty()) {
            sb.append("Realms:\r\n");
            for (Trait trait : realmList) {
                sb.append(trait.toDetailedString());
            }
        }

        sb.append("Oaths: ").append(oaths != null ? oaths : "").append("\r\n");

        return sb.toString();
    }

}
