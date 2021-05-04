package com.partycipate.Partycipate.dto;

import java.util.Date;

public class AnswerCount {
    private Date datetime;
    private int count;

    public AnswerCount(){}

    public AnswerCount(Date datetime, int count) {
        this.datetime = datetime;
        this.count = count;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
