package com.partycipate.Partycipate.dto;

import java.util.HashSet;
import java.util.Set;

public class TimeResultMcList {

    private Set<TimeResultMc> datetime_result = new HashSet<>();
    private int elementId;

    public TimeResultMcList(Set<TimeResultMc> datetime_result, int elementId) {
        this.datetime_result = datetime_result;
        this.elementId = elementId;
    }
    public TimeResultMcList(){}

    public Set<TimeResultMc> getDatetime_result() {
        return datetime_result;
    }

    public void setDatetime_result(Set<TimeResultMc> datetime_result) {
        this.datetime_result = datetime_result;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }
}
