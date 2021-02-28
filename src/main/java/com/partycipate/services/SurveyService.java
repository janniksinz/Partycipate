package com.partycipate.services;

import com.partycipate.models.Survey;

import javax.annotation.Nullable;
import javax.inject.Singleton;

@Singleton
public class SurveyService {
    @Nullable
    private int id;
    private String name;

    public Survey createSurvey(String name){
        Survey survey;
        survey = new Survey.Builder().id(1).name("MyFirstSurvey").build();
        return survey;
    }

    public Survey getSurvey(int id){
        //get survey from db with id
        //obviously id and name need to be inserted from db data
        // like Survey.Builder().id(data.id).name(data.name).build();
        Survey survey = new Survey.Builder().id(1).name("Name from Database").build();
        return survey;
    }

}
