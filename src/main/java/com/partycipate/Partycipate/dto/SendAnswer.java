package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.partycipate.Partycipate.model.MCAnswerContent;

import java.util.Set;

public class SendAnswer {
    private int id;
    private int participant_id;
    private int surveyElement_id;
    private Set<MCAnswerContent> answers;

    //constructor
    public SendAnswer(@JsonProperty("participant_id") int participant_id,
                      @JsonProperty("survey_element_id") int surveyElement_id,
                      @JsonProperty ("answer") Set<MCAnswerContent> answers){
        this.participant_id=participant_id;
        this.surveyElement_id=surveyElement_id;
        this.answers=answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }

    public int getSurveyElement_id() {
        return surveyElement_id;
    }

    public void setSurveyElement_id(int surveyElement_id) {
        this.surveyElement_id = surveyElement_id;
    }

    public Set<MCAnswerContent> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<MCAnswerContent> answers) {
        this.answers = answers;
    }
}
