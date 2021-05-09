package com.partycipate.Partycipate.dto;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import javax.annotation.PostConstruct;
import java.util.*;

public class TimeResultMcList {

    private List<TimeResultMc> datetime_result = new ArrayList<>();
    private int element_id;


    public TimeResultMcList(List<TimeResultMc> datetime_result, int element_id) {
        this.datetime_result = datetime_result;
        this.element_id = element_id;
    }

    public TimeResultMcList(){}

    @PostConstruct
    public void init() {
        Collections.sort(datetime_result, AnnotationAwareOrderComparator.INSTANCE);
    }

    public List<TimeResultMc> getDatetime_result() {
        return datetime_result;
    }

    public void setDatetime_result(List<TimeResultMc> datetime_result) {
        this.datetime_result = datetime_result;
    }

    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }
}
