package com.tieba.model;

import java.util.Objects;

public class BarFollow {
    private int UID;
    private int barID;
    private String barName;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getBarID() {
        return barID;
    }

    public void setBarID(int barID) {
        this.barID = barID;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BarFollow{");
        sb.append("UID=").append(UID);
        sb.append(", barID=").append(barID);
        sb.append(", barName='").append(barName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        BarFollow barFollow = (BarFollow) o;
        return UID == barFollow.UID && barID == barFollow.barID && Objects.equals(barName, barFollow.barName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UID, barID, barName);
    }
}
