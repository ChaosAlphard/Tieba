package com.model;

import java.util.Objects;

public class TieFavorite {
    private int UID;
    private int tieID;
    private String tieTitle;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getTieID() {
        return tieID;
    }

    public void setTieID(int tieID) {
        this.tieID = tieID;
    }

    public String getTieTitle() {
        return tieTitle;
    }

    public void setTieTitle(String tieTitle) {
        this.tieTitle = tieTitle;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TieFavorite{");
        sb.append("UID=").append(UID);
        sb.append(", tieID=").append(tieID);
        sb.append(", tieTitle='").append(tieTitle).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        TieFavorite that = (TieFavorite) o;
        return UID == that.UID && tieID == that.tieID && Objects.equals(tieTitle, that.tieTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UID, tieID, tieTitle);
    }
}
