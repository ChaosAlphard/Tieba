package com.model;

import java.util.Objects;

public class Tie {
    private int tieID;
    private int barID;
    private String tieTitle;
    private String tieMain;
    private String tieUser;
    private int tieUserID;
    private String postTime;
    private String updateTime;
    private int visible;
    private int elite;

    public int getTieID() {
        return tieID;
    }

    public void setTieID(int tieID) {
        this.tieID = tieID;
    }

    public int getBarID() {
        return barID;
    }

    public void setBarID(int barID) {
        this.barID = barID;
    }

    public String getTieTitle() {
        return tieTitle;
    }

    public void setTieTitle(String tieTitle) {
        this.tieTitle = tieTitle;
    }

    public String getTieMain() {
        return tieMain;
    }

    public void setTieMain(String tieMain) {
        this.tieMain = tieMain;
    }

    public String getTieUser() {
        return tieUser;
    }

    public void setTieUser(String tieUser) {
        this.tieUser = tieUser;
    }

    public int getTieUserID() {
        return tieUserID;
    }

    public void setTieUserID(int tieUserID) {
        this.tieUserID = tieUserID;
    }

    public String getPostTime() {
        return this.getTime(postTime);
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getUpdateTime() {
        return this.getTime(updateTime);
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getElite() {
        return elite;
    }

    public void setElite(int elite) {
        this.elite = elite;
    }

    private String getTime(String time) {
        return time.substring(0, time.indexOf("."));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tie{");
        sb.append("tieID=").append(tieID);
        sb.append(", barID=").append(barID);
        sb.append(", tieTitle='").append(tieTitle).append('\'');
        sb.append(", tieMain='").append(tieMain).append('\'');
        sb.append(", tieUser='").append(tieUser).append('\'');
        sb.append(", tieUserID=").append(tieUserID);
        sb.append(", postTime='").append(postTime).append('\'');
        sb.append(", updateTime='").append(updateTime).append('\'');
        sb.append(", visible=").append(visible);
        sb.append(", elite=").append(elite);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Tie tie = (Tie) o;
        return tieID == tie.tieID && barID == tie.barID && tieUserID == tie.tieUserID && visible == tie.visible && elite == tie.elite && Objects.equals(tieTitle, tie.tieTitle) && Objects.equals(tieMain, tie.tieMain) && Objects.equals(tieUser, tie.tieUser) && Objects.equals(postTime, tie.postTime) && Objects.equals(updateTime, tie.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tieID, barID, tieTitle, tieMain, tieUser, tieUserID, postTime, updateTime, visible, elite);
    }
}