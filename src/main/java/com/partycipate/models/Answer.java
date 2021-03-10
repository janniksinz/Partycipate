package com.partycipate.models;

import javax.persistence.Entity;

@Entity
public abstract class Answer {

    private int id;
    private int answerPossibility_id;
    private int participant_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

