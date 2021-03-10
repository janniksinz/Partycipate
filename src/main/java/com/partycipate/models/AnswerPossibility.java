package com.partycipate.models;

import javax.persistence.Entity;

@Entity
public class AnswerPossibility{

    private int id;
    private int question_id;
    private String answer;

    //contructor
    private AnswerPossibility(Builder builder){
        this.id= builder.id;
        this.question_id= builder.question_id;


    }
    //custom constructor for optional parameters
    public static class Builder{

        private int id = 0;
        private int question_id = 0;
        private String answer = "";

        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder question_id(int question_id){
            this.question_id=question_id;
            return this;
        }
        public Builder answer(String answer){
            this.answer=answer;
            return this;
        }

        public AnswerPossibility build(){
            return new AnswerPossibility(this);
        }
    }





}
