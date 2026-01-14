package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Mortal extends Character {
    protected String motivation;
    protected String association;
    protected String title;
    protected String regnant;
    protected int blood;
    protected int humanity;
    protected int conscience;
    protected int selfControl;
    protected int courage;
    protected int trueFaith;
    protected int tempBlood;
    protected int tempHumanity;
    protected int tempConscience;
    protected int tempSelfControl;
    protected int tempCourage;
    protected int tempTrueFaith;
    protected List<Trait> humanityList;
    protected List<Trait> numinaList;
    protected String other;

    public Mortal() {
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegnant() {
        return regnant;
    }

    public void setRegnant(String regnant) {
        this.regnant = regnant;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getHumanity() {
        return humanity;
    }

    public void setHumanity(int humanity) {
        this.humanity = humanity;
    }

    public int getConscience() {
        return conscience;
    }

    public void setConscience(int conscience) {
        this.conscience = conscience;
    }

    public int getSelfControl() {
        return selfControl;
    }

    public void setSelfControl(int selfControl) {
        this.selfControl = selfControl;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
    }

    public int getTrueFaith() {
        return trueFaith;
    }

    public void setTrueFaith(int trueFaith) {
        this.trueFaith = trueFaith;
    }

    public int getTempBlood() {
        return tempBlood;
    }

    public void setTempBlood(int tempBlood) {
        this.tempBlood = tempBlood;
    }

    public int getTempHumanity() {
        return tempHumanity;
    }

    public void setTempHumanity(int tempHumanity) {
        this.tempHumanity = tempHumanity;
    }

    public int getTempConscience() {
        return tempConscience;
    }

    public void setTempConscience(int tempConscience) {
        this.tempConscience = tempConscience;
    }

    public int getTempSelfControl() {
        return tempSelfControl;
    }

    public void setTempSelfControl(int tempSelfControl) {
        this.tempSelfControl = tempSelfControl;
    }

    public int getTempCourage() {
        return tempCourage;
    }

    public void setTempCourage(int tempCourage) {
        this.tempCourage = tempCourage;
    }

    public int getTempTrueFaith() {
        return tempTrueFaith;
    }

    public void setTempTrueFaith(int tempTrueFaith) {
        this.tempTrueFaith = tempTrueFaith;
    }

    public List<Trait> getHumanityList() {
        return humanityList;
    }

    public void setHumanityList(List<Trait> humanityList) {
        this.humanityList = humanityList;
    }

    public List<Trait> getNuminaList() {
        return numinaList;
    }

    public void setNuminaList(List<Trait> numinaList) {
        this.numinaList = numinaList;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Motivation: ").append(motivation != null ? motivation : "").append("\r\n");
        sb.append("Association: ").append(association != null ? association : "").append("\r\n");
        sb.append("Title: ").append(title != null ? title : "").append("\r\n");
        sb.append("Regnant: ").append(regnant != null ? regnant : "").append("\r\n");
        sb.append("Blood: ").append(blood).append("\r\n");
        sb.append("Humanity: ").append(humanity).append("\r\n");
        sb.append("Conscience: ").append(conscience).append("\r\n");
        sb.append("Self Control: ").append(selfControl).append("\r\n");
        sb.append("Courage: ").append(courage).append("\r\n");
        sb.append("True Faith: ").append(trueFaith).append("\r\n");
        sb.append("Temp Blood: ").append(tempBlood).append("\r\n");
        sb.append("Temp Humanity: ").append(tempHumanity).append("\r\n");
        sb.append("Temp Conscience: ").append(tempConscience).append("\r\n");
        sb.append("Temp Self Control: ").append(tempSelfControl).append("\r\n");
        sb.append("Temp Courage: ").append(tempCourage).append("\r\n");
        sb.append("Temp True Faith: ").append(tempTrueFaith).append("\r\n");

        if (humanityList != null && !humanityList.isEmpty()) {
            sb.append("Humanity List:\r\n");
            for (Trait trait : humanityList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (numinaList != null && !numinaList.isEmpty()) {
            sb.append("Numina:\r\n");
            for (Trait trait : numinaList) {
                sb.append(trait.toDetailedString());
            }
        }

        sb.append("Other: ").append(other != null ? other : "").append("\r\n");

        return sb.toString();
    }

}
