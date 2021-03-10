package com.partycipate.models;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

public class SurveyElement {

    private int id;
    private int survey_id;
    private int position;
    private String type;
    private String question;
    private boolean may_skip;
    private




    private SurveyElement(Builder builder){
        this.id = builder.id;

    }

    //custom constructor for optional parameters
    public static class Builder{

        private int id = 0;
        private int survey_id = 0;
        private int position = 0;
        private String type = "multiple-choice";

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder survey_id(int survey_id){
            this.survey_id=survey_id;
            return this;
        }
        public Builder position(int position){
            this.position=position;
            return this;
        }
        public Builder type(String type){
            this.type=type;
            return this;
        }
        public SurveyElement build(){
            return new SurveyElement(this);
        }
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
