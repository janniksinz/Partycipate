package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class SendAnswer {
    private int id;
    private int participant_id;
    private int surveyElement_id;
    private Set<SendMCAnswer> sendMCAnswers;

    //constructor
    public SendAnswer(@JsonProperty("participant_id") int participant_id,
                      @JsonProperty("survey_element_id") int surveyElement_id,
                      @JsonProperty ("answer") Set<SendMCAnswer> sendMCAnswers){
        this.participant_id=participant_id;
        this.surveyElement_id=surveyElement_id;
        this.sendMCAnswers=sendMCAnswers;
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

    public Set<SendMCAnswer> getSendMCAnswers() {
        return sendMCAnswers;
    }

    public void setSendMCAnswers(Set<SendMCAnswer> sendMCAnswers) {
        this.sendMCAnswers = sendMCAnswers;
    }
}
