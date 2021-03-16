package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.partycipate.Partycipate.model.AnswerPossibility;

import java.util.Set;

public class SendElement {
    private boolean may_skip;
    private int position;
    private String question;
    private String type;
    private int survey_id;
    private Set<AnswerPossibility> answerPossibilitySet;


    public SendElement(@JsonProperty("may_skip") boolean may_skip,
                       @JsonProperty("position") int position,
                       @JsonProperty("question") String question,
                       @JsonProperty("type") String type,
                       @JsonProperty("survey_id") int survey_id,
                       @JsonProperty("answerPossibility") Set<AnswerPossibility> answerPossibilitySet
                       ){
        this.may_skip=may_skip; this.position=position; this.question=question;
        this.type=type; this.survey_id=survey_id;
        this.answerPossibilitySet=answerPossibilitySet;
    }

    public Set<AnswerPossibility> getAnswerPossibilitySet() {
        return answerPossibilitySet;
    }

    public void setAnswerPossibilitySet(Set<AnswerPossibility> answerPossibilitySet) {
        this.answerPossibilitySet = answerPossibilitySet;
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

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }
}
