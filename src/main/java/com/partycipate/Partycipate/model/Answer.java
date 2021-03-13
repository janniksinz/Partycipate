package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    private int content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="surveyElement_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private SurveyElement surveyElement;

    private Answer(Builder builder){
        this.id=builder.id;

        this.content= builder.content;
    }
    public Answer(){}

    public static class Builder{
        private int id = 0;

        private int content = 0;

        public Builder id(int id){
            this.id=id;
            return this;
        }


        public Builder content(int content){
            this.content=content;
            return this;
        }

        public Answer build(){return new Answer(this);}
    }

    public SurveyElement getSurveyElement() {
        return surveyElement;
    }

    public void setSurveyElement(SurveyElement surveyElement) {
        this.surveyElement = surveyElement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", content=" + content +
                ", surveyElement=" + surveyElement +
                '}';
    }
}
