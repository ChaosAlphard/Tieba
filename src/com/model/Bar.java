package com.model;

import java.util.Objects;

public class Bar {
    private int barID;
    private String barName;
    private String barContent;
    private int visible;

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

    public String getBarContent() {
        return barContent;
    }

    public void setBarContent(String barContent) {
        this.barContent = barContent;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bar{");
        sb.append("barID=").append(barID);
        sb.append(", barName='").append(barName).append('\'');
        sb.append(", barContent='").append(barContent).append('\'');
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
        Bar bar = (Bar) o;
        return barID == bar.barID && visible == bar.visible && Objects.equals(barName, bar.barName) && Objects.equals(barContent, bar.barContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barID, barName, barContent, visible);
    }
}