package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendRangeAnswerPossibility {

    private int position;
    private int answer;

    public SendRangeAnswerPossibility(@JsonProperty("position") int position,
                                      @JsonProperty("answer") int answer){
        this.answer=answer;
        this.position=position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
