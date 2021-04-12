package com.partycipate.Partycipate.dto;

import java.util.HashSet;
import java.util.Set;

public class TimeResultMcList {

    private Set<TimeResultMc> datetime_result = new HashSet<>();
    private int elementId;

    public TimeResultMcList(Set<TimeResultMc> timeResultMcSet, int elementId) {
        this.timeResultMcSet = timeResultMcSet;
        this.elementId = elementId;
    }
    public TimeResultMcList(){}

    public Set<TimeResultMc> getTimeResultMcSet() {
        return timeResultMcSet;
    }

    public void setTimeResultMcSet(Set<TimeResultMc> timeResultMcSet) {
        this.timeResultMcSet = timeResultMcSet;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }
}
