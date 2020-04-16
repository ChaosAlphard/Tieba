package com.tieba.model;

import java.util.Objects;

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
        return this.getTime(reTime);
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

    private String getTime(String time) {
        return time.substring(0, time.indexOf("."));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TieReply{");
        sb.append("tieID=").append(tieID);
        sb.append(", floor=").append(floor);
        sb.append(", reply='").append(reply).append('\'');
        sb.append(", reUser='").append(reUser).append('\'');
        sb.append(", reUserID=").append(reUserID);
        sb.append(", reTime='").append(reTime).append('\'');
        sb.append(", visible=").append(visible);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        TieReply tieReply = (TieReply) o;
        return tieID == tieReply.tieID && floor == tieReply.floor && reUserID == tieReply.reUserID && visible == tieReply.visible && Objects.equals(reply, tieReply.reply) && Objects.equals(reUser, tieReply.reUser) && Objects.equals(reTime, tieReply.reTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tieID, floor, reply, reUser, reUserID, reTime, visible);
    }
}
