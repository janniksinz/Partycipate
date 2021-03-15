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
    private HashMap<String, String> content; // <1,0> <2,1>

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="surveyElement_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private SurveyElement surveyElement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "participant_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Participant participant;


    //end of variables--------------------------------

    private Answer(Builder builder){
        this.id=builder.id;
        this.content= builder.content;
        this.participant=builder.participant;
        this.surveyElement=builder.surveyElement;
    }
    public Answer(){}
    public Answer(int id, Object content, SurveyElement surveyElement, Participant participant){
        this.id=id; this.surveyElement=surveyElement; this.participant=participant;
        HashMap<String , String> hashMap = new HashMap<>();
        this.content=hashMap;
        //ToDo parse content(Object??) into the Hashmap here
        //Todo can you even store HashMaps in DB?
    }

    public static class Builder{
        private int id = 0;
        private HashMap<String , String > content = null;
        private SurveyElement surveyElement = null;
        private Participant participant = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder content(HashMap<String ,String > content){
            this.content=content;
            return this;
        }
        public Builder surveyElement(SurveyElement surveyElement){
            this.surveyElement=surveyElement;
            return this;
        }
        public Builder participant(Participant participant){
            this.participant=participant;
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

    public HashMap<String , String > getContent() {
        return content;
    }

    public void setContent(HashMap<String , String > content) {
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
