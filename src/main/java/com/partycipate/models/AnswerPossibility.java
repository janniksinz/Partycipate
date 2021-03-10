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
        private int qestion_id = 0;
        private String answer = "";

        public Builder values(int [] values){
            this.values = values;
            return this;
        }

        public AnswerPossibility build(){
            return new AnswerPossibility(this);
        }
    }





}
