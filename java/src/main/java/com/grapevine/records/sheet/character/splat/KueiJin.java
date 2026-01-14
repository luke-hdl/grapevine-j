package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class KueiJin extends Character {
    protected String dharma;
    protected String direction;
    protected String balance;
    protected String station;
    protected String poArchetype;
    protected int hun;
    protected int po;
    protected int yinChi;
    protected int yangChi;
    protected int demonChi;
    protected int dharmaTraits;
    protected int tempHun;
    protected int tempPo;
    protected int tempYinChi;
    protected int tempYangChi;
    protected int tempDemonChi;
    protected int tempDharmaTraits;
    protected List<Trait> statusList;
    protected List<Trait> guanxiList;
    protected List<Trait> disciplineList;
    protected List<Trait> riteList;

    public KueiJin() {
    }

    public String getDharma() {
        return dharma;
    }

    public void setDharma(String dharma) {
        this.dharma = dharma;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getPoArchetype() {
        return poArchetype;
    }

    public void setPoArchetype(String poArchetype) {
        this.poArchetype = poArchetype;
    }

    public int getHun() {
        return hun;
    }

    public void setHun(int hun) {
        this.hun = hun;
    }

    public int getPo() {
        return po;
    }

    public void setPo(int po) {
        this.po = po;
    }

    public int getYinChi() {
        return yinChi;
    }

    public void setYinChi(int yinChi) {
        this.yinChi = yinChi;
    }

    public int getYangChi() {
        return yangChi;
    }

    public void setYangChi(int yangChi) {
        this.yangChi = yangChi;
    }

    public int getDemonChi() {
        return demonChi;
    }

    public void setDemonChi(int demonChi) {
        this.demonChi = demonChi;
    }

    public int getDharmaTraits() {
        return dharmaTraits;
    }

    public void setDharmaTraits(int dharmaTraits) {
        this.dharmaTraits = dharmaTraits;
    }

    public int getTempHun() {
        return tempHun;
    }

    public void setTempHun(int tempHun) {
        this.tempHun = tempHun;
    }

    public int getTempPo() {
        return tempPo;
    }

    public void setTempPo(int tempPo) {
        this.tempPo = tempPo;
    }

    public int getTempYinChi() {
        return tempYinChi;
    }

    public void setTempYinChi(int tempYinChi) {
        this.tempYinChi = tempYinChi;
    }

    public int getTempYangChi() {
        return tempYangChi;
    }

    public void setTempYangChi(int tempYangChi) {
        this.tempYangChi = tempYangChi;
    }

    public int getTempDemonChi() {
        return tempDemonChi;
    }

    public void setTempDemonChi(int tempDemonChi) {
        this.tempDemonChi = tempDemonChi;
    }

    public int getTempDharmaTraits() {
        return tempDharmaTraits;
    }

    public void setTempDharmaTraits(int tempDharmaTraits) {
        this.tempDharmaTraits = tempDharmaTraits;
    }

    public List<Trait> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Trait> statusList) {
        this.statusList = statusList;
    }

    public List<Trait> getGuanxiList() {
        return guanxiList;
    }

    public void setGuanxiList(List<Trait> guanxiList) {
        this.guanxiList = guanxiList;
    }

    public List<Trait> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Trait> disciplineList) {
        this.disciplineList = disciplineList;
    }

    public List<Trait> getRiteList() {
        return riteList;
    }

    public void setRiteList(List<Trait> riteList) {
        this.riteList = riteList;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Dharma: ").append(dharma != null ? dharma : "").append("\r\n");
        sb.append("Direction: ").append(direction != null ? direction : "").append("\r\n");
        sb.append("Balance: ").append(balance != null ? balance : "").append("\r\n");
        sb.append("Station: ").append(station != null ? station : "").append("\r\n");
        sb.append("Po Archetype: ").append(poArchetype != null ? poArchetype : "").append("\r\n");
        sb.append("Hun: ").append(hun).append("\r\n");
        sb.append("Po: ").append(po).append("\r\n");
        sb.append("Yin Chi: ").append(yinChi).append("\r\n");
        sb.append("Yang Chi: ").append(yangChi).append("\r\n");
        sb.append("Demon Chi: ").append(demonChi).append("\r\n");
        sb.append("Dharma Traits: ").append(dharmaTraits).append("\r\n");
        sb.append("Temp Hun: ").append(tempHun).append("\r\n");
        sb.append("Temp Po: ").append(tempPo).append("\r\n");
        sb.append("Temp Yin Chi: ").append(tempYinChi).append("\r\n");
        sb.append("Temp Yang Chi: ").append(tempYangChi).append("\r\n");
        sb.append("Temp Demon Chi: ").append(tempDemonChi).append("\r\n");
        sb.append("Temp Dharma Traits: ").append(tempDharmaTraits).append("\r\n");

        if (statusList != null && !statusList.isEmpty()) {
            sb.append("Status:\r\n");
            for (Trait trait : statusList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (guanxiList != null && !guanxiList.isEmpty()) {
            sb.append("Guanxi:\r\n");
            for (Trait trait : guanxiList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (disciplineList != null && !disciplineList.isEmpty()) {
            sb.append("Disciplines:\r\n");
            for (Trait trait : disciplineList) {
                sb.append(trait.toDetailedString());
            }
        }

        if (riteList != null && !riteList.isEmpty()) {
            sb.append("Rites:\r\n");
            for (Trait trait : riteList) {
                sb.append(trait.toDetailedString());
            }
        }

        return sb.toString();
    }

}
