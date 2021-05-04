package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendRangeContentAnswer {
    private int range_answer_possibility_id;

    public SendRangeContentAnswer(@JsonProperty("range_answer_possibility_id") int range_answerPossibility_id
                        ){
        this.range_answer_possibility_id=range_answerPossibility_id;
    }

    public int getRange_answer_possibility_id() {
        return range_answer_possibility_id;
    }

    public void setRange_answer_possibility_id(int range_answer_possibility_id) {
        this.range_answer_possibility_id = range_answer_possibility_id;
    }
}
