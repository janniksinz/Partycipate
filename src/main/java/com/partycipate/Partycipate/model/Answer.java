package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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

    private HashMap<Integer, Integer> content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="surveyElement_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private SurveyElement surveyElement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "participant_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Participant participant;

    //end of variables

    private Answer(Builder builder){
        this.id=builder.id;

        this.content= builder.content;
    }
    public Answer(){}

    public static class Builder{
        private int id = 0;

        private HashMap<Integer, Integer> content = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }


        public Builder content(HashMap<Integer,Integer> content){
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

    public HashMap<Integer, Integer> getContent() {
        return content;
    }

    public void setContent(HashMap<Integer, Integer> content) {
        this.content = content;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
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
