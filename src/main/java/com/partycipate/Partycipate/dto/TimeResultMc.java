package com.partycipate.Partycipate.dto;

import java.util.ArrayList;
import java.util.Date;

public class TimeResultMc {

    private Date datetime;
    private ArrayList<ResultMc> resultMcs;

    public TimeResultMc(){}

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public ArrayList<ResultMc> getResultMcs() {
        return resultMcs;
    }

    public void setResultMcs(ArrayList<ResultMc> resultMcs) {
        this.resultMcs = resultMcs;
    }
}
