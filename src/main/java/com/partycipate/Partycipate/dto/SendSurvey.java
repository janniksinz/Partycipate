package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class SendSurvey {
    String creation_date;
    String title;
    Set<SendElement> elements;

    public SendSurvey(
                      @JsonProperty("creation_date")String creation_date,
                      @JsonProperty ("title") String title,
                      @JsonProperty ("elements") Set<SendElement> elements){

        this.creation_date=creation_date;
        this.title=title;
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

    public Set<SendElement> getElements() {
        return elements;
    }

    public void setElements(Set<SendElement> elements) {
        this.elements = elements;
    }
}
