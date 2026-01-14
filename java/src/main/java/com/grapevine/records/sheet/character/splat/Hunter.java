package com.grapevine.records.sheet.character.splat;

import java.util.List;

import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;

public class Hunter extends Character {
    protected String creed;
    protected String camp;
    protected String handle;
    protected int conviction;
    protected int mercy;
    protected int vision;
    protected int zeal;
    protected int tempConviction;
    protected int tempMercy;
    protected int tempVision;
    protected int tempZeal;
    protected List<Trait> edgeList;

    public Hunter() {
    }

    public String getCreed() {
        return creed;
    }

    public void setCreed(String creed) {
        this.creed = creed;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getConviction() {
        return conviction;
    }

    public void setConviction(int conviction) {
        this.conviction = conviction;
    }

    public int getMercy() {
        return mercy;
    }

    public void setMercy(int mercy) {
        this.mercy = mercy;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getZeal() {
        return zeal;
    }

    public void setZeal(int zeal) {
        this.zeal = zeal;
    }

    public int getTempConviction() {
        return tempConviction;
    }

    public void setTempConviction(int tempConviction) {
        this.tempConviction = tempConviction;
    }

    public int getTempMercy() {
        return tempMercy;
    }

    public void setTempMercy(int tempMercy) {
        this.tempMercy = tempMercy;
    }

    public int getTempVision() {
        return tempVision;
    }

    public void setTempVision(int tempVision) {
        this.tempVision = tempVision;
    }

    public int getTempZeal() {
        return tempZeal;
    }

    public void setTempZeal(int tempZeal) {
        this.tempZeal = tempZeal;
    }

    public List<Trait> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Trait> edgeList) {
        this.edgeList = edgeList;
    }

    @Override
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toDetailedString());
        sb.append("Creed: ").append(creed != null ? creed : "").append("\r\n");
        sb.append("Camp: ").append(camp != null ? camp : "").append("\r\n");
        sb.append("Handle: ").append(handle != null ? handle : "").append("\r\n");
        sb.append("Conviction: ").append(conviction).append("\r\n");
        sb.append("Mercy: ").append(mercy).append("\r\n");
        sb.append("Vision: ").append(vision).append("\r\n");
        sb.append("Zeal: ").append(zeal).append("\r\n");
        sb.append("Temp Conviction: ").append(tempConviction).append("\r\n");
        sb.append("Temp Mercy: ").append(tempMercy).append("\r\n");
        sb.append("Temp Vision: ").append(tempVision).append("\r\n");
        sb.append("Temp Zeal: ").append(tempZeal).append("\r\n");

        if (edgeList != null && !edgeList.isEmpty()) {
            sb.append("Edges:\r\n");
            for (Trait trait : edgeList) {
                sb.append(trait.toDetailedString());
            }
        }

        return sb.toString();
    }

}
