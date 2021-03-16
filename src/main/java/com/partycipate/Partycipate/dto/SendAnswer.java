package com.partycipate.Partycipate.dto;

import com.partycipate.Partycipate.model.Answer;

public class SendAnswer {
    int id;
    String content;
    int participant_id;
    int surveyElement_id;

    //constructor
    public SendAnswer(Answer answer){
        this.id=answer.getId();
        this.participant_id=answer.getParticipant().getId();
        this.surveyElement_id=answer.getSurveyElement().getId();
    }
}
