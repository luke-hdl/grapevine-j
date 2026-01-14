package com.grapevine.records.sheet.character;

import com.grapevine.records.sheet.experience.Experience;
import com.grapevine.records.sheet.Trait;

import java.util.Date;
import java.util.List;

public class Character {
    protected String name;
    protected String nature;
    protected String demeanor;
    protected List<Trait> physicalList;
    protected List<Trait> socialList;
    protected List<Trait> mentalList;
    protected List<Trait> physicalNegList;
    protected List<Trait> socialNegList;
    protected List<Trait> mentalNegList;
    protected int physicalMax;
    protected int socialMax;
    protected int mentalMax;
    protected List<Trait> abilityList;
    protected List<Trait> influenceList;
    protected List<Trait> backgroundList;
    protected List<Trait> equipmentList;
    protected List<Trait> hangoutList;
    protected String notes;
    protected String player;
    protected String status;
    protected String id;
    protected Date startDate;
    protected String narrator;
    protected boolean isNPC;
    protected Experience experience;
    protected Date lastModified;
    protected String biography;

    protected List<Trait> healthList;
    protected List<Trait> meritList;
    protected List<Trait> flawList;
    protected List<Trait> derangementList;
    protected int willpower;
    protected int tempWillpower;

    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDemeanor() {
        return demeanor;
    }

    public void setDemeanor(String demeanor) {
        this.demeanor = demeanor;
    }

    public List<Trait> getPhysicalList() {
        return physicalList;
    }

    public void setPhysicalList(List<Trait> physicalList) {
        this.physicalList = physicalList;
    }

    public List<Trait> getSocialList() {
        return socialList;
    }

    public void setSocialList(List<Trait> socialList) {
        this.socialList = socialList;
    }

    public List<Trait> getMentalList() {
        return mentalList;
    }

    public void setMentalList(List<Trait> mentalList) {
        this.mentalList = mentalList;
    }

    public List<Trait> getPhysicalNegList() {
        return physicalNegList;
    }

    public void setPhysicalNegList(List<Trait> physicalNegList) {
        this.physicalNegList = physicalNegList;
    }

    public List<Trait> getSocialNegList() {
        return socialNegList;
    }

    public void setSocialNegList(List<Trait> socialNegList) {
        this.socialNegList = socialNegList;
    }

    public List<Trait> getMentalNegList() {
        return mentalNegList;
    }

    public void setMentalNegList(List<Trait> mentalNegList) {
        this.mentalNegList = mentalNegList;
    }

    public int getPhysicalMax() {
        return physicalMax;
    }

    public void setPhysicalMax(int physicalMax) {
        this.physicalMax = physicalMax;
    }

    public int getSocialMax() {
        return socialMax;
    }

    public void setSocialMax(int socialMax) {
        this.socialMax = socialMax;
    }

    public int getMentalMax() {
        return mentalMax;
    }

    public void setMentalMax(int mentalMax) {
        this.mentalMax = mentalMax;
    }

    public List<Trait> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<Trait> abilityList) {
        this.abilityList = abilityList;
    }

    public List<Trait> getInfluenceList() {
        return influenceList;
    }

    public void setInfluenceList(List<Trait> influenceList) {
        this.influenceList = influenceList;
    }

    public List<Trait> getBackgroundList() {
        return backgroundList;
    }

    public void setBackgroundList(List<Trait> backgroundList) {
        this.backgroundList = backgroundList;
    }

    public List<Trait> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Trait> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Trait> getHangoutList() {
        return hangoutList;
    }

    public void setHangoutList(List<Trait> hangoutList) {
        this.hangoutList = hangoutList;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public boolean isIsNPC() {
        return isNPC;
    }

    public void setIsNPC(boolean isNPC) {
        this.isNPC = isNPC;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public String getBiography() {
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isNPC() {
        return isNPC;
    }

    public void setNPC(boolean NPC) {
        isNPC = NPC;
    }

    public List<Trait> getHealthList() {
        return healthList;
    }

    public void setHealthList(List<Trait> healthList) {
        this.healthList = healthList;
    }

    public List<Trait> getMeritList() {
        return meritList;
    }

    public void setMeritList(List<Trait> meritList) {
        this.meritList = meritList;
    }

    public List<Trait> getFlawList() {
        return flawList;
    }

    public void setFlawList(List<Trait> flawList) {
        this.flawList = flawList;
    }

    public List<Trait> getDerangementList() {
        return derangementList;
    }

    public void setDerangementList(List<Trait> derangementList) {
        this.derangementList = derangementList;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getTempWillpower() {
        return tempWillpower;
    }

    public void setTempWillpower(int tempWillpower) {
        this.tempWillpower = tempWillpower;
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name != null ? name : "").append("\r\n");
        sb.append("Nature: ").append(nature != null ? nature : "").append("\r\n");
        sb.append("Demeanor: ").append(demeanor != null ? demeanor : "").append("\r\n");
        sb.append("Physical Max: ").append(physicalMax).append("\r\n");
        sb.append("Social Max: ").append(socialMax).append("\r\n");
        sb.append("Mental Max: ").append(mentalMax).append("\r\n");
        sb.append("Temp Willpower: ").append(tempWillpower).append("\r\n");
        sb.append("Willpower: ").append(willpower).append("\r\n");

        if (physicalList != null && !physicalList.isEmpty()) {
            sb.append("Physical Traits:\r\n");
            for (Trait trait : physicalList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (socialList != null && !socialList.isEmpty()) {
            sb.append("Social Traits:\r\n");
            for (Trait trait : socialList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (mentalList != null && !mentalList.isEmpty()) {
            sb.append("Mental Traits:\r\n");
            for (Trait trait : mentalList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (physicalNegList != null && !physicalNegList.isEmpty()) {
            sb.append("Physical Negative Traits:\r\n");
            for (Trait trait : physicalNegList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (socialNegList != null && !socialNegList.isEmpty()) {
            sb.append("Social Negative Traits:\r\n");
            for (Trait trait : socialNegList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (mentalNegList != null && !mentalNegList.isEmpty()) {
            sb.append("Mental Negative Traits:\r\n");
            for (Trait trait : mentalNegList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (abilityList != null && !abilityList.isEmpty()) {
            sb.append("Abilities:\r\n");
            for (Trait trait : abilityList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (influenceList != null && !influenceList.isEmpty()) {
            sb.append("Influences:\r\n");
            for (Trait trait : influenceList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (backgroundList != null && !backgroundList.isEmpty()) {
            sb.append("Backgrounds:\r\n");
            for (Trait trait : backgroundList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (healthList != null && !healthList.isEmpty()) {
            sb.append("Health:\r\n");
            for (Trait trait : healthList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (equipmentList != null && !equipmentList.isEmpty()) {
            sb.append("Equipment:\r\n");
            for (Trait trait : equipmentList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (hangoutList != null && !hangoutList.isEmpty()) {
            sb.append("Hangouts:\r\n");
            for (Trait trait : hangoutList) {
                sb.append(trait.toDetailedString());
            }
        }


        if (meritList != null && !meritList.isEmpty()) {
            sb.append("Merits:\r\n");
            for (Trait trait : meritList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (flawList != null && !flawList.isEmpty()) {
            sb.append("Flaws:\r\n");
            for (Trait trait : flawList) {
                sb.append(trait.toDetailedString());
            }
        }

        sb.append("Notes: ").append(notes != null ? notes : "").append("\r\n");
        sb.append("Player: ").append(player != null ? player : "").append("\r\n");
        sb.append("Status: ").append(status != null ? status : "").append("\r\n");
        sb.append("ID: ").append(id != null ? id : "").append("\r\n");
        sb.append("Start Date: ").append(startDate != null ? startDate.toString() : "").append("\r\n");
        sb.append("Narrator: ").append(narrator != null ? narrator : "").append("\r\n");
        sb.append("Is NPC: ").append(isNPC).append("\r\n");
        sb.append("Biography: ").append(biography != null ? biography : "").append("\r\n");

        if (experience != null) {
            sb.append("Experience: ").append(experience).append("\r\n");
        }

        sb.append("Last Modified: ").append(lastModified != null ? lastModified.toString() : "").append("\r\n");

        return sb.toString();
    }
}
