package com.partycipate.models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Survey {

    @Id
    private int id;
    private String name;

    private Survey(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    //custom constructor for optional parameters
    public static class Builder{

        private int id = 0;
        private String name = "Survey";

        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Survey build(){
            return new Survey(this);
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
