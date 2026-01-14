package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Mummy extends Character {
    protected String amenti;
    protected int sekhem;
    protected int balance;
    protected int memory;
    protected int integrity;
    protected int joy;
    protected int ba;
    protected int ka;
    protected int tempSekhem;
    protected int tempBalance;
    protected int tempMemory;
    protected int tempIntegrity;
    protected int tempJoy;
    protected int tempBa;
    protected int tempKa;
    protected List<Trait> humanityList;
    protected List<Trait> statusList;
    protected List<Trait> hekauList;
    protected List<Trait> spellList;
    protected List<Trait> ritualList;
    protected String inheritance;

    public Mummy() {
    }

    public String getAmenti() {
        return amenti;
    }

    public void setAmenti(String amenti) {
        this.amenti = amenti;
    }

    public int getSekhem() {
        return sekhem;
    }

    public void setSekhem(int sekhem) {
        this.sekhem = sekhem;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getIntegrity() {
        return integrity;
    }

    public void setIntegrity(int integrity) {
        this.integrity = integrity;
    }

    public int getJoy() {
        return joy;
    }

    public void setJoy(int joy) {
        this.joy = joy;
    }

    public int getBa() {
        return ba;
    }

    public void setBa(int ba) {
        this.ba = ba;
    }

    public int getKa() {
        return ka;
    }

    public void setKa(int ka) {
        this.ka = ka;
    }

    public int getTempSekhem() {
        return tempSekhem;
    }

    public void setTempSekhem(int tempSekhem) {
        this.tempSekhem = tempSekhem;
    }

    public int getTempBalance() {
        return tempBalance;
    }

    public void setTempBalance(int tempBalance) {
        this.tempBalance = tempBalance;
    }

    public int getTempMemory() {
        return tempMemory;
    }

    public void setTempMemory(int tempMemory) {
        this.tempMemory = tempMemory;
    }

    public int getTempIntegrity() {
        return tempIntegrity;
    }

    public void setTempIntegrity(int tempIntegrity) {
        this.tempIntegrity = tempIntegrity;
    }

    public int getTempJoy() {
        return tempJoy;
    }

    public void setTempJoy(int tempJoy) {
        this.tempJoy = tempJoy;
    }

    public int getTempBa() {
        return tempBa;
    }

    public void setTempBa(int tempBa) {
        this.tempBa = tempBa;
    }

    public int getTempKa() {
        return tempKa;
    }

    public void setTempKa(int tempKa) {
        this.tempKa = tempKa;
    }

    public List<Trait> getHumanityList() {
        return humanityList;
    }

    public void setHumanityList(List<Trait> humanityList) {
        this.humanityList = humanityList;
    }

    public List<Trait> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Trait> statusList) {
        this.statusList = statusList;
    }

    public List<Trait> getHekauList() {
        return hekauList;
    }

    public void setHekauList(List<Trait> hekauList) {
        this.hekauList = hekauList;
    }

    public List<Trait> getSpellList() {
        return spellList;
    }

    public void setSpellList(List<Trait> spellList) {
        this.spellList = spellList;
    }

    public List<Trait> getRitualList() {
        return ritualList;
    }

    public void setRitualList(List<Trait> ritualList) {
        this.ritualList = ritualList;
    }

    public String getInheritance() {
        return inheritance;
    }

    public void setInheritance(String inheritance) {
        this.inheritance = inheritance;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Amenti: ").append(amenti != null ? amenti : "").append("\r\n");
        sb.append("Sekhem: ").append(sekhem).append("\r\n");
        sb.append("Balance: ").append(balance).append("\r\n");
        sb.append("Memory: ").append(memory).append("\r\n");
        sb.append("Integrity: ").append(integrity).append("\r\n");
        sb.append("Joy: ").append(joy).append("\r\n");
        sb.append("Ba: ").append(ba).append("\r\n");
        sb.append("Ka: ").append(ka).append("\r\n");
        sb.append("Temp Sekhem: ").append(tempSekhem).append("\r\n");
        sb.append("Temp Balance: ").append(tempBalance).append("\r\n");
        sb.append("Temp Memory: ").append(tempMemory).append("\r\n");
        sb.append("Temp Integrity: ").append(tempIntegrity).append("\r\n");
        sb.append("Temp Joy: ").append(tempJoy).append("\r\n");
        sb.append("Temp Ba: ").append(tempBa).append("\r\n");
        sb.append("Temp Ka: ").append(tempKa).append("\r\n");
        
        if (humanityList != null && !humanityList.isEmpty()) {
            sb.append("Humanity:\r\n");
            for (Trait trait : humanityList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        if (statusList != null && !statusList.isEmpty()) {
            sb.append("Status:\r\n");
            for (Trait trait : statusList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        if (hekauList != null && !hekauList.isEmpty()) {
            sb.append("Hekau:\r\n");
            for (Trait trait : hekauList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        if (spellList != null && !spellList.isEmpty()) {
            sb.append("Spells:\r\n");
            for (Trait trait : spellList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        if (ritualList != null && !ritualList.isEmpty()) {
            sb.append("Rituals:\r\n");
            for (Trait trait : ritualList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        sb.append("Inheritance: ").append(inheritance != null ? inheritance : "").append("\r\n");

        return sb.toString();
    }

}
