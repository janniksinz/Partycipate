package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class MCAnswerContent {
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
    @JoinColumn(name="answerPossibility_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AnswerPossibility answerPossibility;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Answer answer;
    
    public MCAnswerContent (){}
    public MCAnswerContent(int id,  Answer answer) {
        this.id = id;


        this.answer = answer;
    }
    private MCAnswerContent(Builder builder){
        this.id=builder.id;

        this.answer=builder.answer;
    }

    public static class Builder{
        private int id = 0;

        private int position = 0;
        private Answer answer = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }

        public Builder position(int position){
            this.position=position;
            return this;
        }
        public Builder answer(Answer answer){
            this.answer=answer;
            return this;
        }

        public MCAnswerContent build(){return new MCAnswerContent(this);}
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public AnswerPossibility getAnswerPossibility() {
        return answerPossibility;
    }

    public void setAnswerPossibility(AnswerPossibility answerPossibility) {
        this.answerPossibility = answerPossibility;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
