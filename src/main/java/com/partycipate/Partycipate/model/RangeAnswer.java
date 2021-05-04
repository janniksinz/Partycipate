package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class RangeAnswer {
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
    private Set<RangeAnswerContent> rangeAnswerContents;

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

    private RangeAnswer(Builder builder){
        this.id=builder.id;
        this.rangeAnswerContents= builder.rangeAnswerContents;
        this.participant=builder.participant;
        this.surveyElement=builder.surveyElement;
        this.date = builder.date;
    }
    public RangeAnswer(){}
    public RangeAnswer(int id, Set<RangeAnswerContent> rangeAnswerContents, SurveyElement surveyElement, Participant participant, Date date){
        this.id=id; this.surveyElement=surveyElement; this.participant=participant;
        this.rangeAnswerContents=rangeAnswerContents; this.date=date;

    }

    public static class Builder{
        private int id = 0;
        private Set<RangeAnswerContent> rangeAnswerContents = null;
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

        public Builder rangeAnswerContent(Set<RangeAnswerContent> rangeAnswerContents){
            this.rangeAnswerContents=rangeAnswerContents;
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

        public RangeAnswer build(){return new RangeAnswer(this);}
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

    public Set<RangeAnswerContent> getRangeAnswerContents() {
        return rangeAnswerContents;
    }

    public void setRangeAnswerContents(Set<RangeAnswerContent> rangeAnswerContents) {
        this.rangeAnswerContents = rangeAnswerContents;
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
                ", mcAnswerContentSet=" + rangeAnswerContents +
                ", surveyElement=" + surveyElement.getId() +
                ", participant=" + participant.getId() +
                ", date=" + date +
                '}';
    }
}
