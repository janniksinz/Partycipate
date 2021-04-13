package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class ResultMc {

    private ArrayList<Integer> results = new ArrayList();

    //how many answer possibilities (amount of answers with same survey_element_id)

    int count_participants;
    @JsonIgnore
    int element_id;

    public void setResults(ArrayList<Integer> arrayList){
        this.results = arrayList;
    }


    public ArrayList<Integer> getResults() {
        return results;
    }

    public int getElement_id() {
        return element_id;
    }

    public void setElement_id(int element_id) {
        this.element_id = element_id;
    }

    public int getCount_participants() {
        return count_participants;
    }

    public void setCount_participants(int count_participants) {
        this.count_participants = count_participants;
    }
}