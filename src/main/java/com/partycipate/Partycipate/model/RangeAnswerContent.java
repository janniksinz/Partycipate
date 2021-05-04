package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class RangeAnswerContent {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="rangeAnswerPossibility_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private RangeAnswerPossibility rangeAnswerPossibility;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "range_answer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private RangeAnswer rangeAnswer;

    public RangeAnswerContent(){}
    public RangeAnswerContent(int id, RangeAnswer rangeAnswer) {
        this.id = id;
        this.rangeAnswer = rangeAnswer;
    }
    private RangeAnswerContent(Builder builder){
        this.id=builder.id;
        this.rangeAnswer=builder.rangeAnswer;
    }

    public static class Builder{
        private int id = 0;
        private RangeAnswerPossibility rangeAnswerPossibility = null;
        private RangeAnswer rangeAnswer = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }

        public Builder answerPossibility(RangeAnswerPossibility rangeAnswerPossibility){
            this.rangeAnswerPossibility=rangeAnswerPossibility;
            return this;
        }
        public Builder answer(RangeAnswer rangeAnswer){
            this.rangeAnswer=rangeAnswer;
            return this;
        }

        public RangeAnswerContent build(){return new RangeAnswerContent(this);}
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RangeAnswerPossibility getRangeAnswerPossibility() {
        return rangeAnswerPossibility;
    }

    public void setRangeAnswerPossibility(RangeAnswerPossibility rangeAnswerPossibility) {
        this.rangeAnswerPossibility = rangeAnswerPossibility;
    }

    public RangeAnswer getRangeAnswer() {
        return rangeAnswer;
    }

    public void setRangeAnswer(RangeAnswer rangeAnswer) {
        this.rangeAnswer = rangeAnswer;
    }
}
