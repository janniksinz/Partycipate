package com.partycipate.models;

import java.util.ArrayList;

public class MultipleChoiceAnswer {

    private int[] values;

    private MultipleChoiceAnswer(Builder builder){
        this.values= builder.values;

    }

    //custom constructor for optional parameters
    public static class Builder{

        private int[] values;

        public Builder values(int [] values){
            this.values = values;
            return this;
        }

        public MultipleChoiceAnswer build(){
            return new MultipleChoiceAnswer(this);
        }
    }





}
