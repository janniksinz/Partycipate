package com.partycipate.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    private int id;
    private int survey_element_id;
    private int position;
    private String type;
    private String question;
    private String content;
    private Answer answer;

    private Answer(Builder builder){
        this.id = builder.id;
        this.survey_element_id= builder.survey_element_id;
        this.position = builder.position;
    }

    //custom constructor for optional parameters
    public static class Builder{

        private int id = 0;
        private int survey_element_id = 0;
        private int position = 0;
        private String type = "multiple-choice";
        private String question="";
        private String content="";
        private Answer answer;

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder survey_element_id(int survey_element_id){
            this.survey_element_id=survey_element_id;
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
        public Builder question(String question){
            this.question=question;
            return this;
        }
        public Builder content (String content){
            this.content=content;
            return this;
        }
        public Builder Answer(Answer answer){
            this.answer=answer;
            return this;
        }
        public Answer build(){
            return new Answer(this);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSurvey_element_id() {
        return survey_element_id;
    }

    public void setSurvey_element_id(int survey_element_id) {
        this.survey_element_id = survey_element_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

