package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMCAnswer {
    private int answerPossibility_id;

    public SendMCAnswer(@JsonProperty("answer_possibility_id") int answerPossibility_id
                        ){
        this.answerPossibility_id=answerPossibility_id;
    }

    public int getAnswerPossibility_id() {
        return answerPossibility_id;
    }

    public void setAnswerPossibility_id(int answerPossibility_id) {
        this.answerPossibility_id = answerPossibility_id;
    }
}
