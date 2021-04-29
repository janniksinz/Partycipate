package com.partycipate.Partycipate.dto;

public class SubmitSurvey {
    private int survey_id;
    private String participant_cookie;
    private String language;

    public SubmitSurvey(){}

    public SubmitSurvey(int survey_id, String cookie, String language) {
        this.survey_id = survey_id;
        this.participant_cookie = cookie;
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
