package com.partycipate.Partycipate.model;

import com.partycipate.Partycipate.model.SurveyElement;
import javax.persistence.*;
import java.util.ArrayList;

@Entity(name = "Survey")
public class Survey {
    @Id
    @SequenceGenerator(
            name = "survey_sequence",
            sequenceName = "survey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @Column(nullable = false)
    private int client_id;

    private String creation_date;
    private String title;
    private String cookie;
    private ArrayList<SurveyElement> content;

    public Survey(){}

    private Survey(Builder builder) {
        this.id = builder.id;
        this.client_id = builder.client_id;
        this.creation_date = builder.creation_date;
        this.title = builder.title;
        this.cookie = builder.cookie;
        this.content = builder.content;
    }



    //custom constructor for optional parameters
    public static class Builder{

        private int id = 0;
        private int client_id = 0;
        private String creation_date = "";
        private String title = "Survey";
        private String cookie = "";
        private ArrayList<SurveyElement> content = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder cliend_id(int client_id){
            this.client_id=client_id;
            return this;
        }
        public Builder creation_date(String creation_date){
            this.creation_date=creation_date;
            return this;
        }
        public Builder title(String title){
            this.title =title;
            return this;
        }
        public Builder cookie(String cookie){
            this.cookie=cookie;
            return this;
        }
        public Builder content(ArrayList<SurveyElement> content){
            this.content=content;
            return this;
        }
        public Survey build(){
            return new Survey(this);
        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(ArrayList<SurveyElement> content) {
        this.content = content;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }


    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public String getCookie() {
        return cookie;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public ArrayList<SurveyElement> getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", creation_date='" + creation_date + '\'' +
                ", title='" + title + '\'' +
                ", cookie='" + cookie + '\'' +
                ", content=" + content +
                '}';
    }
}