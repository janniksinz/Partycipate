package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.partycipate.Partycipate.model.SurveyElement;

import java.util.Set;

public class SendSurvey {
    String creation_date;
    String title;
    int user_id;
    Set<SendElement> elements;

    public SendSurvey(
                      @JsonProperty("creation_date")String creation_date,
                      @JsonProperty ("title") String title,
                      @JsonProperty ("user_id") int user_id,
                      @JsonProperty ("elements") Set<SendElement> elements){


        this.creation_date=creation_date;
        this.title=title;
        this.user_id=user_id;
        this.elements=elements;
    }





    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Set<SendElement> getElements() {
        return elements;
    }

    public void setElements(Set<SendElement> elements) {
        this.elements = elements;
    }
}
