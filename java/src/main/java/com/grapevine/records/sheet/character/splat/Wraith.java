package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Wraith extends Character {
    protected int ethnos;
    protected String guild;
    protected String faction;
    protected String legion;
    protected String rank;
    protected int pathos;
    protected int corpus;
    protected int willpower;
    protected int tempPathos;
    protected int tempCorpus;
    protected int tempWillpower;
    protected List<Trait> statusList;
    protected List<Trait> arcanoiList;
    protected String passions;
    protected String fetters;
    protected String life;
    protected String death;
    protected String haunt;
    protected String regret;
    protected String shadowArchetype;
    protected String shadowPlayer;
    protected int angst;
    protected int tempAngst;
    protected String darkPassions;
    protected List<Trait> thornList;

    public Wraith() {
    }

    public int getEthnos() {
        return ethnos;
    }

    public void setEthnos(int ethnos) {
        this.ethnos = ethnos;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getLegion() {
        return legion;
    }

    public void setLegion(String legion) {
        this.legion = legion;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getPathos() {
        return pathos;
    }

    public void setPathos(int pathos) {
        this.pathos = pathos;
    }

    public int getCorpus() {
        return corpus;
    }

    public void setCorpus(int corpus) {
        this.corpus = corpus;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getTempPathos() {
        return tempPathos;
    }

    public void setTempPathos(int tempPathos) {
        this.tempPathos = tempPathos;
    }

    public int getTempCorpus() {
        return tempCorpus;
    }

    public void setTempCorpus(int tempCorpus) {
        this.tempCorpus = tempCorpus;
    }

    public int getTempWillpower() {
        return tempWillpower;
    }

    public void setTempWillpower(int tempWillpower) {
        this.tempWillpower = tempWillpower;
    }

    public List<Trait> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Trait> statusList) {
        this.statusList = statusList;
    }

    public List<Trait> getArcanoiList() {
        return arcanoiList;
    }

    public void setArcanoiList(List<Trait> arcanoiList) {
        this.arcanoiList = arcanoiList;
    }

    public String getPassions() {
        return passions;
    }

    public void setPassions(String passions) {
        this.passions = passions;
    }

    public String getFetters() {
        return fetters;
    }

    public void setFetters(String fetters) {
        this.fetters = fetters;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getHaunt() {
        return haunt;
    }

    public void setHaunt(String haunt) {
        this.haunt = haunt;
    }

    public String getRegret() {
        return regret;
    }

    public void setRegret(String regret) {
        this.regret = regret;
    }

    public String getShadowArchetype() {
        return shadowArchetype;
    }

    public void setShadowArchetype(String shadowArchetype) {
        this.shadowArchetype = shadowArchetype;
    }

    public String getShadowPlayer() {
        return shadowPlayer;
    }

    public void setShadowPlayer(String shadowPlayer) {
        this.shadowPlayer = shadowPlayer;
    }

    public int getAngst() {
        return angst;
    }

    public void setAngst(int angst) {
        this.angst = angst;
    }

    public int getTempAngst() {
        return tempAngst;
    }

    public void setTempAngst(int tempAngst) {
        this.tempAngst = tempAngst;
    }

    public String getDarkPassions() {
        return darkPassions;
    }

    public void setDarkPassions(String darkPassions) {
        this.darkPassions = darkPassions;
    }

    public List<Trait> getThornList() {
        return thornList;
    }

    public void setThornList(List<Trait> thornList) {
        this.thornList = thornList;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Ethnos: ").append(ethnos).append("\r\n");
        sb.append("Guild: ").append(guild != null ? guild : "").append("\r\n");
        sb.append("Faction: ").append(faction != null ? faction : "").append("\r\n");
        sb.append("Legion: ").append(legion != null ? legion : "").append("\r\n");
        sb.append("Rank: ").append(rank != null ? rank : "").append("\r\n");
        sb.append("Pathos: ").append(pathos).append("\r\n");
        sb.append("Corpus: ").append(corpus).append("\r\n");
        sb.append("Temp Pathos: ").append(tempPathos).append("\r\n");
        sb.append("Temp Corpus: ").append(tempCorpus).append("\r\n");

        if (statusList != null && !statusList.isEmpty()) {
            sb.append("Status:\r\n");
            for (Trait trait : statusList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (arcanoiList != null && !arcanoiList.isEmpty()) {
            sb.append("Arcanoi:\r\n");
            for (Trait trait : arcanoiList) {
                sb.append(trait.toDetailedString());
            }
        }

        sb.append("Passions: ").append(passions != null ? passions : "").append("\r\n");
        sb.append("Fetters: ").append(fetters != null ? fetters : "").append("\r\n");
        sb.append("Life: ").append(life != null ? life : "").append("\r\n");
        sb.append("Death: ").append(death != null ? death : "").append("\r\n");
        sb.append("Haunt: ").append(haunt != null ? haunt : "").append("\r\n");
        sb.append("Regret: ").append(regret != null ? regret : "").append("\r\n");
        sb.append("Shadow Archetype: ").append(shadowArchetype != null ? shadowArchetype : "").append("\r\n");
        sb.append("Shadow Player: ").append(shadowPlayer != null ? shadowPlayer : "").append("\r\n");
        sb.append("Angst: ").append(angst).append("\r\n");
        sb.append("Temp Angst: ").append(tempAngst).append("\r\n");
        sb.append("Dark Passions: ").append(darkPassions != null ? darkPassions : "").append("\r\n");

        if (thornList != null && !thornList.isEmpty()) {
            sb.append("Thorns:\r\n");
            for (Trait trait : thornList) {
                sb.append(trait.toDetailedString());
            }
        }

        return sb.toString();
    }

}
