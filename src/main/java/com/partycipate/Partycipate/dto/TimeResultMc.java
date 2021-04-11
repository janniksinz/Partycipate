package com.partycipate.Partycipate.dto;

import java.util.Date;

public class TimeResultMc {

    private Date datetime;
    private ResultMc resultMc;

    public TimeResultMc(){}

    public TimeResultMc(Date datetime, ResultMc resultMc) {
        this.datetime = datetime;
        this.resultMc = resultMc;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public ResultMc getResultMc() {
        return resultMc;
    }

    public void setResultMc(ResultMc resultMc) {
        this.resultMc = resultMc;
    }
}
