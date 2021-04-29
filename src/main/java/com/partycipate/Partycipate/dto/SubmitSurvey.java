package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmitSurvey {
    private int survey_id;
    private String participant_cookie;
    private String language;

    public SubmitSurvey(){}

    public SubmitSurvey(@JsonProperty("survey_id") int survey_id,
                        @JsonProperty("participant_cookie") String participant_cookie,
                        @JsonProperty("language") String language) {
        this.survey_id = survey_id;
        this.participant_cookie = participant_cookie;
        this.language = language;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public String getParticipant_cookie() {
        return participant_cookie;
    }

    public void setParticipant_cookie(String participant_cookie) {
        this.participant_cookie = participant_cookie;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
