package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.partycipate.Partycipate.model.AnswerPossibility;

import java.util.Set;

public class SendElement {
    private boolean may_skip;
    private int position;
    private String question;
    private String type;

    private Set<SendAnswerPossibility> answer_possibilities;


    public SendElement(@JsonProperty("may_skip") boolean may_skip,
                       @JsonProperty("position") int position,
                       @JsonProperty("question") String question,
                       @JsonProperty("type") String type,
                       @JsonProperty("answer_possibilities") Set<SendAnswerPossibility> answer_possibilities
                       ) {
        this.may_skip = may_skip;
        this.position = position;
        this.question = question;
        this.type = type;

        this.answer_possibilities=answer_possibilities;
    }

    public Set<SendAnswerPossibility> getAnswer_possibilities() {
        return answer_possibilities;
    }

    public void setAnswer_possibilities(Set<SendAnswerPossibility> answer_possibilities) {
        this.answer_possibilities = answer_possibilities;
    }

    public boolean isMay_skip() {
        return may_skip;
    }

    public void setMay_skip(boolean may_skip) {
        this.may_skip = may_skip;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
