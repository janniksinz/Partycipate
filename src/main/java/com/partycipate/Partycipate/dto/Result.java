package com.partycipate.Partycipate.dto;

import java.util.ArrayList;

public class Result {
    ArrayList<Integer> results = new ArrayList();

    //how many answer possibilities (amount of answers with same survey_element_id)
    int size;
    int count_participants;

    public void setResults(ArrayList<Integer> arrayList){
        this.results = arrayList;
    }
}