package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendAnswerPossibility {

    private int position;
    private String answer;

    public SendAnswerPossibility(@JsonProperty("position") int position,
                                 @JsonProperty("answer") String answer){
        this.answer=answer;
        this.position=position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
