package com.partycipate.Partycipate.model;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @SequenceGenerator(
            name = "answer_sequence",
            sequenceName = "answer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    private int surveyElement_id;
    private int content;

    private Answer(Builder builder){
        this.id=builder.id;
        this.surveyElement_id = builder.surveyElement_id;
        this.content= builder.content;
    }
    public Answer(){}

    public static class Builder{
        private int id = 0;
        private int surveyElement_id = 0;
        private int content = 0;

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder surveyElement_id(int surveyElement_id){
            this.surveyElement_id=surveyElement_id;
            return this;
        }
        public Builder content(int content){
            this.content=content;
            return this;
        }

        public Answer build(){return new Answer(this);}
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurveyElement_id() {
        return surveyElement_id;
    }

    public void setSurveyElement_id(int surveyElement_id) {
        this.surveyElement_id = surveyElement_id;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
}
