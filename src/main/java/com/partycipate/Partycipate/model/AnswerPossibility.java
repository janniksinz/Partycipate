package com.partycipate.Partycipate.model;

import javax.persistence.*;

@Entity
public class AnswerPossibility {
    @Id
    @SequenceGenerator(
            name = "answerPossibility_sequence",
            sequenceName = "answerPossibility_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answerPossibility_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    private int surveyElement_id;
    private String answer;

    private AnswerPossibility(Builder builder){
        this.id=builder.id;
        this.surveyElement_id= builder.surveyElement_id;
        this.answer= builder.answer;
    }

    public AnswerPossibility() {}

    public static class Builder{
        private int id = 0;
        private int surveyElement_id = 0;
        private String answer = "";

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder surveyElement_id(int surveyElement_id){
            this.surveyElement_id=surveyElement_id;
            return this;
        }
        public Builder answer(String answer){
            this.answer=answer;
            return this;
        }

        public AnswerPossibility build() {return new AnswerPossibility(this);}
    }

    @Override
    public String toString() {
        return "AnswerPossibility{" +
                "id=" + id +
                ", surveyElement_id=" + surveyElement_id +
                ", answer='" + answer + '\'' +
                '}';
    }
}
