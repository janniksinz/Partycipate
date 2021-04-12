package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Set;

public class SendAnswer {
    private int id;
    private int participant_id;
    private int surveyElement_id;
    private Set<SendMCAnswer> sendMCAnswers;
    private java.util.Date date;

    //constructor
    public SendAnswer(@JsonProperty("participant_id") int participant_id,
                      @JsonProperty("survey_element_id") int surveyElement_id,
                      @JsonProperty ("answer") Set<SendMCAnswer> sendMCAnswers){
        this.participant_id=participant_id;
        this.surveyElement_id=surveyElement_id;
        this.sendMCAnswers=sendMCAnswers;
        this.date=new Date(System.currentTimeMillis());
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
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
