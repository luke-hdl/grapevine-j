package com.grapevine.records.sheet.character.splat;

public class Werewolf extends Fera {
    protected String tribe;
    protected String camp;

    public Werewolf() {
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }


    public String getSpecies() {
        return null;
    }

    public void setSpecies(String species) {
        //no-op
    }

    @Override
    public String toDetailedString() {
        return super.toDetailedString() +
                "Tribe: " + (tribe != null ? tribe : "") + "\r\n" +
                "Camp: " + (camp != null ? camp : "") + "\r\n";
    }

}
