package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

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

    @OneToMany(mappedBy = "answer",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MCAnswerContent> mcAnswerContentSet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="surveyElement_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private SurveyElement surveyElement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "participant_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Participant participant;

    private java.util.Date date;

    //end of variables--------------------------------

    private Answer(Builder builder){
        this.id=builder.id;
        this.mcAnswerContentSet= builder.mcAnswerContent;
        this.participant=builder.participant;
        this.surveyElement=builder.surveyElement;
        this.date = builder.date;
    }
    public Answer(){}
    public Answer(int id, Set<MCAnswerContent> mcAnswerContent, SurveyElement surveyElement, Participant participant, Date date){
        this.id=id; this.surveyElement=surveyElement; this.participant=participant;
        this.mcAnswerContentSet=mcAnswerContent; this.date=date;

    }

    public static class Builder{
        private int id = 0;
        private Set<MCAnswerContent> mcAnswerContent = null;
        private SurveyElement surveyElement = null;
        private Participant participant = null;
        private java.util.Date date = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder mcAnswerContent(Set<MCAnswerContent> mcAnswerContent){
            this.mcAnswerContent=mcAnswerContent;
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

    public Set<MCAnswerContent> getMcAnswerContentSet() {
        return mcAnswerContentSet;
    }

    public void setMcAnswerContentSet(Set<MCAnswerContent> mcAnswerContentSet) {
        this.mcAnswerContentSet = mcAnswerContentSet;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }


    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", mcAnswerContentSet=" + mcAnswerContentSet +
                ", surveyElement=" + surveyElement.getId() +
                ", participant=" + participant.getId() +
                ", date=" + date +
                '}';
    }
}
