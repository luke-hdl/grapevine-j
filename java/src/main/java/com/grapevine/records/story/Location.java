package com.grapevine.records.story;

import com.grapevine.records.sheet.Trait;

import java.util.Date;
import java.util.List;

public class Location {
    protected String name;
    protected String locType;
    protected int level;
    protected String owner;
    protected String where;
    protected String appearance;
    protected int secTraits;
    protected int secRetests;
    protected String access;
    protected String security;
    protected int gauntlet;
    protected String affinity;
    protected String totem;
    protected String umbra;
    protected List<Trait> linkList;
    protected String notes;
    protected Date lastModified;

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public int getSecTraits() {
        return secTraits;
    }

    public void setSecTraits(int secTraits) {
        this.secTraits = secTraits;
    }

    public int getSecRetests() {
        return secRetests;
    }

    public void setSecRetests(int secRetests) {
        this.secRetests = secRetests;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public int getGauntlet() {
        return gauntlet;
    }

    public void setGauntlet(int gauntlet) {
        this.gauntlet = gauntlet;
    }

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public String getTotem() {
        return totem;
    }

    public void setTotem(String totem) {
        this.totem = totem;
    }

    public String getUmbra() {
        return umbra;
    }

    public void setUmbra(String umbra) {
        this.umbra = umbra;
    }

    public List<Trait> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Trait> linkList) {
        this.linkList = linkList;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name != null ? name : "").append("\r\n");
        sb.append("Location Type: ").append(locType != null ? locType : "").append("\r\n");
        sb.append("Level: ").append(level).append("\r\n");
        sb.append("Owner: ").append(owner != null ? owner : "").append("\r\n");
        sb.append("Where: ").append(where != null ? where : "").append("\r\n");
        sb.append("Appearance: ").append(appearance != null ? appearance : "").append("\r\n");
        sb.append("Security Traits: ").append(secTraits).append("\r\n");
        sb.append("Security Retests: ").append(secRetests).append("\r\n");
        sb.append("Access: ").append(access != null ? access : "").append("\r\n");
        sb.append("Security: ").append(security != null ? security : "").append("\r\n");
        sb.append("Gauntlet: ").append(gauntlet).append("\r\n");
        sb.append("Affinity: ").append(affinity != null ? affinity : "").append("\r\n");
        sb.append("Totem: ").append(totem != null ? totem : "").append("\r\n");
        sb.append("Umbra: ").append(umbra != null ? umbra : "").append("\r\n");
        
        if (linkList != null && !linkList.isEmpty()) {
            sb.append("Links:\r\n");
            for (Trait trait : linkList) {
                sb.append(trait.toDetailedString());
            }
        }
        
        sb.append("Notes: ").append(notes != null ? notes : "").append("\r\n");
        sb.append("Last Modified: ").append(lastModified != null ? lastModified.toString() : "").append("\r\n");
        return sb.toString();
    }
}
