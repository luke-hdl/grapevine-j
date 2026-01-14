package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Mage extends Character {
    protected String essence;
    protected String tradition;
    protected String faction;
    protected String cabal;
    protected String rank;
    protected int arete;
    protected int quintessence;
    protected int paradox;
    protected int tempArete;
    protected int tempQuintessence;
    protected int tempParadox;
    protected String foci;
    protected String biography;
    protected List<Trait> resonanceList;
    protected List<Trait> reputationList;
    protected List<Trait> sphereList;
    protected List<Trait> roteList;

    public Mage() {
    }

    public String getEssence() {
        return essence;
    }

    public void setEssence(String essence) {
        this.essence = essence;
    }

    public String getTradition() {
        return tradition;
    }

    public void setTradition(String tradition) {
        this.tradition = tradition;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getCabal() {
        return cabal;
    }

    public void setCabal(String cabal) {
        this.cabal = cabal;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getArete() {
        return arete;
    }

    public void setArete(int arete) {
        this.arete = arete;
    }

    public int getQuintessence() {
        return quintessence;
    }

    public void setQuintessence(int quintessence) {
        this.quintessence = quintessence;
    }

    public int getParadox() {
        return paradox;
    }

    public void setParadox(int paradox) {
        this.paradox = paradox;
    }

    public int getTempArete() {
        return tempArete;
    }

    public void setTempArete(int tempArete) {
        this.tempArete = tempArete;
    }

    public int getTempQuintessence() {
        return tempQuintessence;
    }

    public void setTempQuintessence(int tempQuintessence) {
        this.tempQuintessence = tempQuintessence;
    }

    public int getTempParadox() {
        return tempParadox;
    }

    public void setTempParadox(int tempParadox) {
        this.tempParadox = tempParadox;
    }

    public String getFoci() {
        return foci;
    }

    public void setFoci(String foci) {
        this.foci = foci;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Trait> getResonanceList() {
        return resonanceList;
    }

    public void setResonanceList(List<Trait> resonanceList) {
        this.resonanceList = resonanceList;
    }

    public List<Trait> getReputationList() {
        return reputationList;
    }

    public void setReputationList(List<Trait> reputationList) {
        this.reputationList = reputationList;
    }

    public List<Trait> getSphereList() {
        return sphereList;
    }

    public void setSphereList(List<Trait> sphereList) {
        this.sphereList = sphereList;
    }

    public List<Trait> getRoteList() {
        return roteList;
    }

    public void setRoteList(List<Trait> roteList) {
        this.roteList = roteList;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Essence: ").append(essence != null ? essence : "").append("\r\n");
        sb.append("Tradition: ").append(tradition != null ? tradition : "").append("\r\n");
        sb.append("Faction: ").append(faction != null ? faction : "").append("\r\n");
        sb.append("Cabal: ").append(cabal != null ? cabal : "").append("\r\n");
        sb.append("Rank: ").append(rank != null ? rank : "").append("\r\n");
        sb.append("Arete: ").append(arete).append("\r\n");
        sb.append("Quintessence: ").append(quintessence).append("\r\n");
        sb.append("Paradox: ").append(paradox).append("\r\n");
        sb.append("Temp Arete: ").append(tempArete).append("\r\n");
        sb.append("Temp Quintessence: ").append(tempQuintessence).append("\r\n");
        sb.append("Temp Paradox: ").append(tempParadox).append("\r\n");
        sb.append("Foci: ").append(foci != null ? foci : "").append("\r\n");

        if (resonanceList != null && !resonanceList.isEmpty()) {
            sb.append("Resonance:\r\n");
            for (Trait trait : resonanceList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (reputationList != null && !reputationList.isEmpty()) {
            sb.append("Reputation:\r\n");
            for (Trait trait : reputationList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (sphereList != null && !sphereList.isEmpty()) {
            sb.append("Spheres:\r\n");
            for (Trait trait : sphereList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (roteList != null && !roteList.isEmpty()) {
            sb.append("Rotes:\r\n");
            for (Trait trait : roteList) {
                sb.append(trait.toDetailedString());
            }
        }

        return sb.toString();
    }

}
