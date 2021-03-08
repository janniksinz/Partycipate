package com.partycipate.models;

import java.util.ArrayList;

public class MultipleChoiceAnswer {

    private int[] values;

    private MultipleChoiceAnswer(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    //custom constructor for optional parameters
    public static class Builder{

        private int id = 0;
        private int survey_id = 0;
        private int position = 0;
        private String type = "multiple-choice";

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder survey_id(int survey_id){
            this.survey_id=survey_id;
            return this;
        }
        public Builder position(int position){
            this.position=position;
            return this;
        }
        public Builder type(String type){
            this.type=type;
            return this;
        }
        public MultipleChoiceAnswer build(){
            return new MultipleChoiceAnswer(this);
        }
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
