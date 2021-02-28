package com.partycipate.services;

import com.partycipate.models.Survey;

import javax.inject.Singleton;

@Singleton
public class SurveyService {

    public static Survey createSurvey(String name){
        Survey survey;
        survey = new Survey.Builder().id(1).name("MyFirstSurvey").build();
        return survey;
    }

    public static Survey getSurvey(int id){
        //get survey from db with id
        //obviously id and name need to be inserted from db data
        // like Survey.Builder().id(data.id).name(data.name).build();
        Survey survey = new Survey.Builder().id(id).name("Name from Database").build();
        return survey;
    }

}
