package com.partycipate.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Survey {

    private int id;
    private String name;
    private ArrayList<SurveyElement> content;

    private Survey(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    //custom constructor for optional parameters
    public static class Builder{

        private int id = 0;
        private String name = "Survey";
        private ArrayList<SurveyElement> content = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder name(String name){
            this.name=name;
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


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SurveyElement> getContent() {
        return content;
    }
    public void setContent(ArrayList<SurveyElement> content) {
        this.content = content;
    }
}
