package com.partycipate.Partycipate.dto;

import java.util.HashSet;
import java.util.Set;

public class TimeResultMcList {

    private Set<TimeResultMc> datetime_result = new HashSet<>();
    private int element_id;

    public TimeResultMcList(Set<TimeResultMc> datetime_result, int element_id) {
        this.datetime_result = datetime_result;
        this.element_id = element_id;
    }
    public TimeResultMcList(){}

    public Set<TimeResultMc> getDatetime_result() {
        return datetime_result;
    }

    public void setDatetime_result(Set<TimeResultMc> datetime_result) {
        this.datetime_result = datetime_result;
    }

    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }
}
