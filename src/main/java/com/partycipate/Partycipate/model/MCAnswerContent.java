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
    private int content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Answer answer;

    public MCAnswerContent (){}
    public MCAnswerContent(int id, int content, Answer answer) {
        this.id = id;
        this.content = content;
        this.answer = answer;
    }
    private MCAnswerContent(Builder builder){
        this.id=builder.id;
        this.content=builder.content;
        this.answer=builder.answer;
    }

    public static class Builder{
        private int id = 0;
        private int content = 0;
        private Answer answer = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder content(int content){
            this.content=content;
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

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
