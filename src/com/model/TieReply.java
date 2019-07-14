package com.model;

public class TieReply {
    private int tieID;
    private int floor;
    private String reply;
    private String reUser;
    private int reUserID;
    private String reTime;
    private int visible;

    public int getTieID() {
        return tieID;
    }

    public void setTieID(int tieID) {
        this.tieID = tieID;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReUser() {
        return reUser;
    }

    public void setReUser(String reUser) {
        this.reUser = reUser;
    }

    public int getReUserID() {
        return reUserID;
    }

    public void setReUserID(int reUserID) {
        this.reUserID = reUserID;
    }

    public String getReTime() {
        return reTime;
    }

    public void setReTime(String reTime) {
        this.reTime = reTime;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
