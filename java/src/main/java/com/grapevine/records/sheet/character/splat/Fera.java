package com.grapevine.records.sheet.character.splat;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

import java.util.List;

public class Fera extends Character {
    protected String species; //saved as "fera" in the files, but this is confusing. Using "species" here.
    protected String breed;
    protected String auspice;
    protected String rank;
    protected String pack;
    protected String totem;
    protected String position;
    protected int notoriety;
    protected int rage;
    protected int gnosis;
    protected int tempRage;
    protected int tempGnosis;
    protected int honor;
    protected int glory;
    protected int wisdom;
    protected float tempHonor;
    protected float tempGlory;
    protected float tempWisdom;
    protected List<Trait> featureList;
    protected List<Trait> giftList;
    protected List<Trait> riteList;
    protected List<Trait> honorList;
    protected List<Trait> gloryList;
    protected List<Trait> wisdomList;

    public Fera() {
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAuspice() {
        return auspice;
    }

    public void setAuspice(String auspice) {
        this.auspice = auspice;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getTotem() {
        return totem;
    }

    public void setTotem(String totem) {
        this.totem = totem;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNotoriety() {
        return notoriety;
    }

    public void setNotoriety(int notoriety) {
        this.notoriety = notoriety;
    }

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public int getGnosis() {
        return gnosis;
    }

    public void setGnosis(int gnosis) {
        this.gnosis = gnosis;
    }

    public int getTempRage() {
        return tempRage;
    }

    public void setTempRage(int tempRage) {
        this.tempRage = tempRage;
    }

    public int getTempGnosis() {
        return tempGnosis;
    }

    public void setTempGnosis(int tempGnosis) {
        this.tempGnosis = tempGnosis;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public int getGlory() {
        return glory;
    }

    public void setGlory(int glory) {
        this.glory = glory;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public float getTempHonor() {
        return tempHonor;
    }

    public void setTempHonor(float tempHonor) {
        this.tempHonor = tempHonor;
    }

    public float getTempGlory() {
        return tempGlory;
    }

    public void setTempGlory(float tempGlory) {
        this.tempGlory = tempGlory;
    }

    public float getTempWisdom() {
        return tempWisdom;
    }

    public void setTempWisdom(float tempWisdom) {
        this.tempWisdom = tempWisdom;
    }

    public List<Trait> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Trait> featureList) {
        this.featureList = featureList;
    }

    public List<Trait> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Trait> giftList) {
        this.giftList = giftList;
    }

    public List<Trait> getRiteList() {
        return riteList;
    }

    public void setRiteList(List<Trait> riteList) {
        this.riteList = riteList;
    }

    public List<Trait> getHonorList() {
        return honorList;
    }

    public void setHonorList(List<Trait> honorList) {
        this.honorList = honorList;
    }

    public List<Trait> getGloryList() {
        return gloryList;
    }

    public void setGloryList(List<Trait> gloryList) {
        this.gloryList = gloryList;
    }

    public List<Trait> getWisdomList() {
        return wisdomList;
    }

    public void setWisdomList(List<Trait> wisdomList) {
        this.wisdomList = wisdomList;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Fera: ").append(species != null ? species : "").append("\r\n");
        sb.append("Breed: ").append(breed != null ? breed : "").append("\r\n");
        sb.append("Auspice: ").append(auspice != null ? auspice : "").append("\r\n");
        sb.append("Rank: ").append(rank != null ? rank : "").append("\r\n");
        sb.append("Pack: ").append(pack != null ? pack : "").append("\r\n");
        sb.append("Totem: ").append(totem != null ? totem : "").append("\r\n");
        sb.append("Position: ").append(position != null ? position : "").append("\r\n");
        sb.append("Notoriety: ").append(notoriety).append("\r\n");
        sb.append("Rage: ").append(rage).append("\r\n");
        sb.append("Gnosis: ").append(gnosis).append("\r\n");
        sb.append("Temp Rage: ").append(tempRage).append("\r\n");
        sb.append("Temp Gnosis: ").append(tempGnosis).append("\r\n");
        sb.append("Honor: ").append(honor).append("\r\n");
        sb.append("Glory: ").append(glory).append("\r\n");
        sb.append("Wisdom: ").append(wisdom).append("\r\n");
        sb.append("Temp Honor: ").append(tempHonor).append("\r\n");
        sb.append("Temp Glory: ").append(tempGlory).append("\r\n");
        sb.append("Temp Wisdom: ").append(tempWisdom).append("\r\n");

        if (featureList != null && !featureList.isEmpty()) {
            sb.append("Features:\r\n");
            for (Trait trait : featureList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (giftList != null && !giftList.isEmpty()) {
            sb.append("Gifts:\r\n");
            for (Trait trait : giftList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (riteList != null && !riteList.isEmpty()) {
            sb.append("Rites:\r\n");
            for (Trait trait : riteList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (honorList != null && !honorList.isEmpty()) {
            sb.append("Honor List:\r\n");
            for (Trait trait : honorList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (gloryList != null && !gloryList.isEmpty()) {
            sb.append("Glory List:\r\n");
            for (Trait trait : gloryList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (wisdomList != null && !wisdomList.isEmpty()) {
            sb.append("Wisdom List:\r\n");
            for (Trait trait : wisdomList) {
                sb.append(trait.toDetailedString());
            }
        }

        return sb.toString();
    }

}
