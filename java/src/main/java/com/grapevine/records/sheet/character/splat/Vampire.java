package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.Boon;
import com.grapevine.records.sheet.character.Character;

public class Vampire extends Character {
    protected String clan;
    protected String sect;
    protected int generation;
    protected String title;
    protected String coterie;
    protected String path;
    protected String sire;
    protected String aura;
    protected String auraBonus;
    protected int blood;
    protected int conscience;
    protected int selfControl;
    protected int courage;
    protected int pathTraits;
    protected int tempBlood;
    protected int tempConscience;
    protected int tempSelfControl;
    protected int tempCourage;
    protected int tempPathTraits;
    protected List<Trait> statusList;
    protected List<Trait> bondList;
    protected List<Trait> miscellaneousList;
    protected List<Trait> disciplineList;
    protected List<Trait> ritualList;
    protected List<Boon> boonList;
    protected List<Trait> boonTraitList;

    public Vampire() {
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getSect() {
        return sect;
    }

    public void setSect(String sect) {
        this.sect = sect;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoterie() {
        return coterie;
    }

    public void setCoterie(String coterie) {
        this.coterie = coterie;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public String getAura() {
        return aura;
    }

    public void setAura(String aura) {
        this.aura = aura;
    }

    public String getAuraBonus() {
        return auraBonus;
    }

    public void setAuraBonus(String auraBonus) {
        this.auraBonus = auraBonus;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
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

    public int getPathTraits() {
        return pathTraits;
    }

    public void setPathTraits(int pathTraits) {
        this.pathTraits = pathTraits;
    }

    public int getTempBlood() {
        return tempBlood;
    }

    public void setTempBlood(int tempBlood) {
        this.tempBlood = tempBlood;
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

    public int getTempPathTraits() {
        return tempPathTraits;
    }

    public void setTempPathTraits(int tempPathTraits) {
        this.tempPathTraits = tempPathTraits;
    }

    public List<Trait> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Trait> statusList) {
        this.statusList = statusList;
    }

    public List<Trait> getBondList() {
        return bondList;
    }

    public void setBondList(List<Trait> bondList) {
        this.bondList = bondList;
    }

    public List<Trait> getMiscellaneousList() {
        return miscellaneousList;
    }

    public void setMiscellaneousList(List<Trait> miscellaneousList) {
        this.miscellaneousList = miscellaneousList;
    }

    public List<Trait> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Trait> disciplineList) {
        this.disciplineList = disciplineList;
    }

    public List<Trait> getRitualList() {
        return ritualList;
    }

    public void setRitualList(List<Trait> ritualList) {
        this.ritualList = ritualList;
    }

    public List<Boon> getBoonList() {
        return boonList;
    }

    public void setBoonList(List<Boon> boonList) {
        this.boonList = boonList;
    }

    public List<Trait> getBoonTraitList() {
        return boonTraitList;
    }

    public void setBoonTraitList(List<Trait> boonTraitList) {
        this.boonTraitList = boonTraitList;
        // TODO - should then generate the Boons for setBoonList?
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Clan: ").append(clan != null ? clan : "").append("\r\n");
        sb.append("Sect: ").append(sect != null ? sect : "").append("\r\n");
        sb.append("Generation: ").append(generation).append("\r\n");
        sb.append("Title: ").append(title != null ? title : "").append("\r\n");
        sb.append("Coterie: ").append(coterie != null ? coterie : "").append("\r\n");
        sb.append("Path: ").append(path != null ? path : "").append("\r\n");
        sb.append("Sire: ").append(sire != null ? sire : "").append("\r\n");
        sb.append("Aura: ").append(aura != null ? aura : "").append("\r\n");
        sb.append("Aura Bonus: ").append(auraBonus != null ? auraBonus : "").append("\r\n");
        sb.append("Blood: ").append(blood).append("\r\n");
        sb.append("Conscience: ").append(conscience).append("\r\n");
        sb.append("Self Control: ").append(selfControl).append("\r\n");
        sb.append("Courage: ").append(courage).append("\r\n");
        sb.append("Path Traits: ").append(pathTraits).append("\r\n");
        sb.append("Temp Blood: ").append(tempBlood).append("\r\n");
        sb.append("Temp Conscience: ").append(tempConscience).append("\r\n");
        sb.append("Temp Self Control: ").append(tempSelfControl).append("\r\n");
        sb.append("Temp Courage: ").append(tempCourage).append("\r\n");
        sb.append("Temp Path Traits: ").append(tempPathTraits).append("\r\n");

        if (statusList != null && !statusList.isEmpty()) {
            sb.append("Status:\r\n");
            for (Trait trait : statusList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (bondList != null && !bondList.isEmpty()) {
            sb.append("Bonds:\r\n");
            for (Trait trait : bondList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (miscellaneousList != null && !miscellaneousList.isEmpty()) {
            sb.append("Miscellaneous:\r\n");
            for (Trait trait : miscellaneousList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (disciplineList != null && !disciplineList.isEmpty()) {
            sb.append("Disciplines:\r\n");
            for (Trait trait : disciplineList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (ritualList != null && !ritualList.isEmpty()) {
            sb.append("Rituals:\r\n");
            for (Trait trait : ritualList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (boonList != null && !boonList.isEmpty()) {
            sb.append("Boons:\r\n");
            for (Boon boon : boonList) {
                sb.append(boon.toDetailedString());
            }
        }

        if (boonTraitList != null && !boonTraitList.isEmpty()) {
            sb.append("Boon Traits:\r\n");
            for (Trait trait : boonTraitList) {
                sb.append(trait.toDetailedString());
            }
        }

        return sb.toString();
    }
}
