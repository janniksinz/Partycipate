package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Survey;

public class SurveyService {

    public static Survey createSurvey(String title){
        Survey survey = new Survey.Builder().title(title).build();
        //insert the data in the db with methods from the db_service
        return survey;
    }

    public static Survey getSurvey(int id){
        //get survey from db with id
        //obviously id and name need to be inserted from db.
        // data needs to be fetched with the db_service
        // like Survey.Builder().id(data.id).name(data.name).build();
        Survey survey = new Survey.Builder().id(id).title("Name from Database").build();

        return survey;
    }

}
