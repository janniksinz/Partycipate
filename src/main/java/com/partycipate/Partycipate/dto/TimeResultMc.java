package com.partycipate.Partycipate.dto;

import java.util.Date;

public class TimeResultMc {

    private Date datetime;
    private ResultMc result;

    public TimeResultMc(){}

    public TimeResultMc(Date datetime, ResultMc result) {
        this.datetime = datetime;
        this.result = result;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public ResultMc getResult() {
        return result;
    }

    public void setResult(ResultMc result) {
        this.result = result;
    }
}
