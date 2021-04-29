package com.partycipate.Partycipate.dto;

public class SubmitSurvey {
    private int survey_id;
    private String cookie;
    private String language;

    public SubmitSurvey(){}

    public SubmitSurvey(int survey_id, String cookie, String language) {
        this.survey_id = survey_id;
        this.cookie = cookie;
        this.language = language;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
